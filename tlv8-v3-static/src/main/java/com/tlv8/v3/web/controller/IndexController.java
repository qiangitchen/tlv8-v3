package com.tlv8.v3.web.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tlv8.v3.system.bean.ContextBean;
import com.tlv8.v3.system.utils.ContextUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 首页跳转
 * 
 * @author 陈乾
 *
 */
@Controller
public class IndexController {

	@RequestMapping("/")
	public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ContextBean contextbean = ContextUtils.getContext();
		if (contextbean != null && contextbean.isLogin()) {
			response.sendRedirect("portal/login.html");
		} else {
			response.sendRedirect("portal/index.html");
		}
	}

}
