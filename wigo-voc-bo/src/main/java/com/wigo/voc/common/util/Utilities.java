package com.wigo.voc.common.util;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wigo.voc.common.model.EzMap;
import com.wigo.voc.common.model.EzPropertyServiceImpl;
import com.wigo.voc.sys.model.CrmLoginUserVo;
import com.wigo.voc.sys.model.CrmMenuBaseVo;
import com.wigo.voc.sys.service.CrmCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 
 * <pre>
 * com.wigo.crm.common.util - Utilities.java
 * </pre>
 *
 * @ClassName : Utilities
 * @Description : 유틸리티
 * @author : 김성태
 * @date : 2021. 1. 5.
 * @Version : 1.0
 * @Company : Copyright ⓒ wigo.ai. All Right Reserved
 */

@Component
public class Utilities extends BaseUtilities {
	private static CrmCommonService commonService;
	private static EzPropertyServiceImpl propertiesService;
	@Autowired
	CrmCommonService commService;
	@Resource(name = "propertiesService") // 인텔리제이 버그같음. 패키지명 수정 후 발생
	EzPropertyServiceImpl ezProperties;

	@PostConstruct
	public void init() throws Exception {
		super.init();
		commonService = this.commService;
		propertiesService = this.ezProperties;
	}

	public static String getProperty(String name) {
		return propertiesService.getString(name, "");
	}

	public static int getPropertyInt(String name) {
		return propertiesService.getInt(name, 0);
	}

	public static long getPropertyLong(String name) {
		return propertiesService.getLong(name, 0);
	}

	public static double getPropertyDouble(String name) {
		return propertiesService.getDouble(name, 0);
	}

	public static double getPropertyFloat(String name) {
		return propertiesService.getFloat(name, 0);
	}

	public static boolean getPropertyBoolean(String name) {
		return propertiesService.getBoolean(name, false);
	}

	public static CrmLoginUserVo getLoginUser() {
		HttpSession session = getSession();
		if (session == null)
			return null;
		return (CrmLoginUserVo) session.getAttribute(Constants._LOGIN_SESSION_NAME);
	}

	public static String getLoginUserCd() {
		CrmLoginUserVo loginUser = getLoginUser();
		if (loginUser == null)
			return null;
		return loginUser.getUserCd();

	}

	public static String getLoginUserId() {
		// return "2020000001";
		CrmLoginUserVo loginUser = getLoginUser();
		if (loginUser == null)
			return null;
		return loginUser.getLoginId();

	}

	public static boolean isLogin() {

		return Utilities.isNotEmpty(getLoginUserCd());
	}

	public static String getAutoSeq(String prefix) throws Exception {
		EzMap param = new EzMap();
		param.setString("prefix", prefix);
		return getAutoSeq(param);
	}

//	public static String getAutoSeq(String prefix, String suffix) throws Exception {
//		EzMap param = new EzMap();
//		param.setString("prefix", prefix);
//		param.setString("suffix", suffix);
//		return getAutoSeq(param);
//	}

	public static String getAutoSeq(Map<String, Object> param) throws Exception {
		return commonService.getAutoSeq(param);
	}

	public static String getFileCd() throws Exception {
		EzMap param = new EzMap();
		param.setString("prefix", "FL");
		return getAutoSeq(param);
	}

	public static CrmMenuBaseVo getCurrentMenu() {
		HttpServletRequest request = getRequest();
		if (request == null)
			return null;

		return (CrmMenuBaseVo) request.getAttribute("currentMenu");
	}

	public static String getCurrentMenuCd() {
		CrmMenuBaseVo menu = getCurrentMenu();
		if (menu == null)
			return null;
		return menu.getMenuCd();
	}
}
