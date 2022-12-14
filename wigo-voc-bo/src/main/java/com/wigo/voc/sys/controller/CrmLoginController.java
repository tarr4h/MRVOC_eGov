package com.wigo.voc.sys.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wigo.voc.sys.model.CrmLoginUserVo;
import com.wigo.voc.sys.service.CrmLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = { "login", "logout", "{menuCd}/login", "{menuCd}/logout" })
public class CrmLoginController {

	@Autowired
    CrmLoginService service;
	@Value("${spring.sso.logout-url}")
	private String logoutUrl;

	//
	@GetMapping(value = { "index", "" })
	public String loginForm(@RequestParam Map<String, Object> param, ModelMap model, HttpServletResponse res)
			throws Exception {
		return service.goSso(res);
//		String saveId = Utilities.getCookieValue("saveId");
//		if (Utilities.isNotEmpty(saveId))
//			model.addAttribute("loginId", Utilities.decrypt(saveId));
//		return "login";
	}

	//
	@PostMapping(value = { "/login" })
	public @ResponseBody Object login(@RequestBody CrmLoginUserVo param) throws Exception {
		return service.updatelogin(param);
	}

	@PostMapping(value = { "/logout" })
	public @ResponseBody Object logout() throws Exception {

		return service.updatelogout();
	}

	@GetMapping(value = { "/logout" })
	public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		service.updatelogout();
		response.sendRedirect(logoutUrl);
		return "logout";
	}

	@GetMapping(value = { "/oauth" })
	public void oauth(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		service.processSso(param, request, response);
	}

}
