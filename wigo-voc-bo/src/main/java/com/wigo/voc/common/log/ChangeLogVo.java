package com.wigo.voc.common.log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wigo.voc.common.util.Utilities;
import com.wigo.voc.sys.model.CrmChngHstVo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeLogVo extends CrmChngHstVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5373659850987208709L;
	private String userCd;

	private boolean inLogMode = false;
	private ChangeLogElemement callStack;
	private ChangeLogElemement element = null;
	private boolean hasChange = false;

	public ChangeLogElemement addStack(String excution, Object[] params) {
		ChangeLogElemement elem = new ChangeLogElemement(excution, params, element);
		if (callStack == null)
			callStack = elem;

		element = elem;
		return elem;
	}

	public void setResult(Object result) {
		if (element == null)
			return;
		element.setResults(result);
		element = element.getParent();
	}

	public void makeParam() {
		if (callStack == null)
			return;
		try {
			Date date = Utilities.parseDate(getChngCallDt());
			if (date == null)
				return;
			long end = new Date().getTime() - date.getTime();
			setChngExecMsec((int) end);
			setChngCallIpAddr(Utilities.getPeerIp());
			setChngRsltTxn(Utilities.getJsonString(callStack.getResults()));
			setChngParamTxn(Utilities.getJsonString(callStack.getParameters()));
			List<ChangeLogElemement> list = new ArrayList<ChangeLogElemement>();
			if(callStack !=null)
				callStack.addStacks(list);
			setChngCallTxn(Utilities.getJsonString(list));
		} catch (Exception e) {

		}
	}

}
