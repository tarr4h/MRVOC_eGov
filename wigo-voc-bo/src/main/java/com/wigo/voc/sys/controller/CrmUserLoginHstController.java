package com.wigo.voc.sys.controller;

import java.util.List;
import java.util.Map;

import com.wigo.voc.common.model.EzMap;
import com.wigo.voc.common.model.EzPaginationInfo;
import com.wigo.voc.common.util.Utilities;
import com.wigo.voc.sys.service.CrmUserLoginHstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
* <pre>
* com.wigo.crm.common.CrmUserLoginHstController
*	- CrmMenuController.java
* </pre>
*
* @ClassName	: CrmUserLoginHstController 
* @Description	: CrmUserLoginHstController 컨트롤러 
* @author 		: 김성태
* @date 		: 2022. 1. 8.
* @Version 		: 1.0 
* @Company 		: Copyright ⓒ wigo.ai. All Right Reserved
 */

@Controller
@RequestMapping(value = {"userLoginHist"})
public class CrmUserLoginHstController {
	@Autowired
    CrmUserLoginHstService service;

	@GetMapping(value = { "", "index" })
	public String init(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);
		return Utilities.getProperty("tiles.crm") + "sys/userLoginHist";
	}
	@PostMapping(value = { "getList" })
	public @ResponseBody Object getList(@RequestBody EzMap param) throws Exception {
		EzPaginationInfo page = param.getPaginationInfo();
		List<EzMap> list = service.getList(param);
		page.setTotalRecordCount(service.getListCount(param));
		return Utilities.getGridData(list,page);
	} 
}
