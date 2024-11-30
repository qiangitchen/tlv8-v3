package com.tlv8.v3.doc.generator.pojo;

public class SqlParams {
	private String param;

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param == null ? "" : param.trim();
	}
}
