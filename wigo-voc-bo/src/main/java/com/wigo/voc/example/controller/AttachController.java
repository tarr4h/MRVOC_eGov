package com.wigo.voc.example.controller;

import java.util.List;
import java.util.Map;

import com.wigo.voc.common.model.EzMap;
import com.wigo.voc.common.model.EzPaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wigo.voc.common.util.Utilities;
import com.wigo.voc.example.model.ExampleVo;
import com.wigo.voc.example.service.AttachService;

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
@RequestMapping(value = { "attach" })
public class AttachController {
	@Autowired
	AttachService service;

	@GetMapping(value = { "", "index" })
	public String init(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);
		return Utilities.getProperty("tiles.crm") + "example/attachList";
	}
	
	@PostMapping(value = { "getList" })
	public @ResponseBody Object getList(@RequestBody EzMap param) throws Exception {
//		EzMap param = new EzMap(rparam);
		EzPaginationInfo page = param.getPaginationInfo();
		List<EzMap> list = service.getList(param);
		page.setTotalRecordCount(service.getListCount(param));
		return Utilities.getGridData(list,page);
	}
	
	@GetMapping(value = { "get" })
	public @ResponseBody Object get(@RequestParam Map<String, Object> rparam) throws Exception {
		EzMap param = new EzMap(rparam);
		return service.get(param);
	}
	@PostMapping(value = { "save" })
	public @ResponseBody Object save(@RequestBody ExampleVo list) throws Exception {
		return service.save(list);
	}	
	
	@PostMapping(value = { "saveList" })
	public @ResponseBody Object saveList(@RequestBody List<ExampleVo> list) throws Exception {
		return service.saveList(list);
	}	
	

	
}
