package com.wigo.voc.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wigo.voc.sys.dao.CrmCommonDao;

@Service("commonService")
public class CrmCommonService {
	@Autowired
	CrmCommonDao commonDao;

	/**
	 * <pre>
	 * 자동채번
	 * </pre>
	 */
	public String getAutoSeq(Object param) {
		return commonDao.getAutoSeq(param);
	}

}
