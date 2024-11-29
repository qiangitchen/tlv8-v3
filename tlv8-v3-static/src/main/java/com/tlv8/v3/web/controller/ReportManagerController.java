package com.tlv8.v3.web.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 报表管理
 * 
 * @author 陈乾
 *
 */
@Controller
@RequestMapping("/reportManager")
public class ReportManagerController {

	@RequestMapping("/ureportDesigner")
	public void docSetting(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("/ureport/designer");
	}
}
