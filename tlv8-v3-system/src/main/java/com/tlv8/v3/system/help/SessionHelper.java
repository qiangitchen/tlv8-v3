package com.tlv8.v3.system.help;

import jakarta.servlet.http.HttpServletRequest;

public class SessionHelper {

	public static String getPadId(HttpServletRequest req) {
		return req.getSession().getAttribute("tlv8_key_padid") != null
				? req.getSession().getAttribute("tlv8_key_padid").toString()
				: "";
	}

	public static void setPadId(HttpServletRequest req, String padid) {
		req.getSession().setAttribute("tlv8_key_padid", padid);
	}

}
