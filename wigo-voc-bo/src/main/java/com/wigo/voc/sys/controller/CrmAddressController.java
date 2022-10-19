package com.wigo.voc.sys.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wigo.voc.common.model.EzMap;
import com.wigo.voc.common.model.EzPaginationInfo;
import com.wigo.voc.common.model.address.EzAddressSo;
import com.wigo.voc.common.model.address.EzAdressResultVo;
import com.wigo.voc.common.util.Utilities;
import com.wigo.voc.sys.service.CrmAddressService;

/**
 * 
* <pre>
* com.wigo.crm.common.ExampleController
*	- ExampleController.java
* </pre>
*
* @ClassName	: ExampleController 
* @Description	: ExampleController 컨트롤러 
* @author 		: 김성태
* @date 		: 2022. 1. 8.
* @Version 		: 1.0 
* @Company 		: Copyright ⓒ wigo.ai. All Right Reserved
 */

@Controller
@RequestMapping(value = { "address","{menuCd}/address" })
public class CrmAddressController {
	@Autowired
	CrmAddressService service;

	@GetMapping(value = { "", "index" })
	public String init(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);
		return Utilities.getProperty("tiles.crm") + "sys/addressList";
	}
	@GetMapping(value = { "addressPop" })
	public String addressPop(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);
		return Utilities.getProperty("tiles.crm.blank") + "sys/addressPop";
	}
	@PostMapping(value = { "getList" })
	public @ResponseBody Object getList(@RequestBody EzMap param) throws Exception {
//		EzMap param = new EzMap(rparam);
		EzPaginationInfo page = param.getPaginationInfo();
		EzAddressSo so = new EzAddressSo(page);
		so.setKeyword(  param.getString("keyword"));
		EzAdressResultVo result= service.getList(so);
		if(result == null)
			return null;
		page.setTotalRecordCount(Utilities.parseInt(result.getCommon().getTotalCount()));
		return Utilities.getGridData(result.getJuso(),page);
	}
	
	
	
}
