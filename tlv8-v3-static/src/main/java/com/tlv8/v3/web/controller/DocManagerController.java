package com.tlv8.v3.web.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 文档管理
 * 
 * @author 陈乾
 *
 */
@Controller
@RequestMapping("/docManage")
public class DocManagerController {

	@RequestMapping("/docSetting")
	public void docSetting(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("/SA/doc/docSetting/mainActivity.html");
	}

	@RequestMapping("/docCenter")
	public void docCenter(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("/SA/doc/docCenter/mainActivity.html");
	}
}
