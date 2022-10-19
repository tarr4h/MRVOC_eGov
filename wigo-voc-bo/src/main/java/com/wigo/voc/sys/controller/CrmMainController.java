package com.wigo.voc.sys.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wigo.voc.common.util.SessionUtil;
import com.wigo.voc.common.util.Utilities;
import com.wigo.voc.sys.model.CrmUserWdgtVo;
import com.wigo.voc.sys.service.CrmMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = { "" })
public class CrmMainController {
	@Autowired
    CrmMenuService menuService;
//	@Autowired
//	EzJwtProvider jwtProvider;
	@GetMapping(value = {"", "index"})
	public String init(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param, ModelMap map) throws Exception {
		param.put("userCd", SessionUtil.getLoginUserCd());
		
		map.addAttribute("wdgtList", menuService.getWdgtList(param));
		return "main";
	}
	@PostMapping(value = {"addWdgt"})
	public @ResponseBody Object addWdgt(@RequestBody CrmUserWdgtVo vo) throws Exception {
		return menuService.insertWdgt(vo);
	}
	@PostMapping(value = {"removeWdgt"})
	public @ResponseBody Object removeWdgt(@RequestBody CrmUserWdgtVo vo) throws Exception {
		return menuService.deleteWdgt(vo);
	}
	
	@GetMapping(value = {"main"})
	public String main(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param, ModelMap map) throws Exception {
		return Utilities.getProperty("tiles.crm") + "main/index";
	}
	@GetMapping(value = {"sort"})
	public String sort(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param, ModelMap map) throws Exception {
		return Utilities.getProperty("tiles.crm.blank") + "main/sort";
	}
	@GetMapping(value = {"util/audioPlayer"})
	public String audioPlayer(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param, ModelMap map) throws Exception {
		map.addAllAttributes(param);
		return Utilities.getProperty("tiles.crm.blank") + "main/audioPlayer";
	}
	
	@GetMapping(value = {"sort/textDialog"})
	public String textDialog(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param, ModelMap map) throws Exception {
		map.addAllAttributes(param);
		return Utilities.getProperty("tiles.crm.blank") + "main/textDialog";
	}
//	@RequestMapping(value = {"createToken"})
//	public @ResponseBody Object createToken(@RequestBody EzMap param) 
//	{
//		String systemCd = param.getString("systemCd");
//		String userCd = Utilities.getLoginUserCd();
//		EzMap ret = new EzMap();
//		ret.setString("token", jwtProvider.createToken(userCd, systemCd));
//		return ret;
//	}
	
	
}
