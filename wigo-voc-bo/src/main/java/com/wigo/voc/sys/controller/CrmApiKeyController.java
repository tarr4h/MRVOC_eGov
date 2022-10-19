package com.wigo.voc.sys.controller;

import java.util.List;
import java.util.Map;

import com.wigo.voc.common.jwt.JwtUtility;
import com.wigo.voc.common.model.EzMap;
import com.wigo.voc.common.model.EzPaginationInfo;
import com.wigo.voc.common.util.Utilities;
import com.wigo.voc.sys.model.CrmComnCdBaseVo;
import com.wigo.voc.sys.service.CrmComnCdService;
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
@RequestMapping(value = { "apikey" })
public class CrmApiKeyController {
	@Autowired
    CrmComnCdService service;

	@GetMapping(value = { "", "index" })
	public String init(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);
		return Utilities.getProperty("tiles.crm") + "sys/apikeyList";
	}
	
	@PostMapping(value = { "getList" })
	public @ResponseBody Object getList(@RequestBody EzMap param) throws Exception {
//		EzMap param = new EzMap(rparam);
		EzPaginationInfo page = param.getPaginationInfo();
		List<EzMap> list = service.getList(param);
		page.setTotalRecordCount(service.getListCount(param));
		return Utilities.getGridData(list,page);
	}
	@PostMapping(value = { "getApikey" })
	public @ResponseBody Object getApikey(@RequestBody CrmComnCdBaseVo param) throws Exception {
		String token = JwtUtility.createToken("SYSTEM", param.getComnCd());
		EzMap ret = new EzMap();
		ret.setString("token", token);
		return ret;
	}
	
	@GetMapping(value = { "get" })
	public @ResponseBody Object get(@RequestParam Map<String, Object> rparam) throws Exception {
		EzMap param = new EzMap(rparam);
		return service.get(param);
	}
	@PostMapping(value = { "save" })
	public @ResponseBody Object save(@RequestBody CrmComnCdBaseVo vo) throws Exception {
		return service.save(vo);
	}	
	
	@PostMapping(value = { "saveList" })
	public @ResponseBody Object saveList(@RequestBody List<CrmComnCdBaseVo> list) throws Exception {
		return service.saveList(list);
	}	
	@PostMapping(value = { "deleteList" })
	public @ResponseBody Object deleteList(@RequestBody List<CrmComnCdBaseVo> list) throws Exception {
		return service.deleteList(list);
	}
	
	
	
}
