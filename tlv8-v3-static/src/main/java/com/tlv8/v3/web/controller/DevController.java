package com.tlv8.v3.web.controller;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tlv8.v3.common.base.Sys;
import com.tlv8.v3.common.redis.RedisCache;
import com.tlv8.v3.common.utils.IPUtils;
import com.tlv8.v3.system.action.Login;
import com.tlv8.v3.system.action.WriteLoginLog;
import com.tlv8.v3.system.bean.ContextBean;
import com.tlv8.v3.system.controller.UserController;
import com.tlv8.v3.system.help.Configuration;
import com.tlv8.v3.system.online.InitOnlineInfoAction;
import com.tlv8.v3.system.pojo.SysLogin;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/dev")
public class DevController {
	@Autowired
	private UserController userController;
	@Autowired
	protected RedisCache redisCache;
	@Autowired
	private Login login;
	@Autowired
	private InitOnlineInfoAction initOnline;
	@Autowired
	private WriteLoginLog writeLoginLog;

	@ResponseBody
	@RequestMapping("/devLogin")
	public void devLogin(HttpServletRequest request, String username, String password, HttpServletResponse response)
			throws IOException {
		ContextBean contextBean = new ContextBean();
		String serverURL = Configuration.getUIServerURL(null);
		contextBean.setUIServerURL(serverURL);
		String token = "";
		boolean success;
		try {
			SysLogin sysLogin = login.MD5doLogin(username, password);
			HashMap<String, String> params = userController.getSysParams(request, sysLogin);
			contextBean.initLoginContext(request, params);
			contextBean.setUsername(username);
			contextBean.setPassword(password);
			StpUtil.login(contextBean.getPersonID());
			token = StpUtil.getTokenValue();
			redisCache.setCacheObject(token, contextBean);
			success = true;
			initOnline.execute(contextBean, token);
			writeLoginLog.write(contextBean.getCurrentUserID(), username, IPUtils.getRemoteAddr(request), password,
					request);
		} catch (Exception e) {
			success = false;
			Sys.printErr(e.getMessage());
		}
		if (!success) {
			userController.clearHttpClient();
			contextBean.initLogoutContext(request);
			response.sendRedirect("/portal/login.html");
		} else {
			response.sendRedirect("/portal/index.html");
		}
	}
}
