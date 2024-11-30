package com.tlv8.v3.doc.controller.connector;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class ShttpServletRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request = null;

	public ShttpServletRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	@Override
	public String getPathInfo() {
		String baseContextPath = request.getRequestURI().replace(request.getContextPath(), "");
		return baseContextPath;
	}

}
