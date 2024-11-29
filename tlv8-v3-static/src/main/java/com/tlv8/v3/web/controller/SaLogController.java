package com.tlv8.v3.web.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/SA/log")
public class SaLogController {

	@RequestMapping("/sysLog")
	public void sysLog(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("/SA/system/log/mainActivity.html");
	}
}
