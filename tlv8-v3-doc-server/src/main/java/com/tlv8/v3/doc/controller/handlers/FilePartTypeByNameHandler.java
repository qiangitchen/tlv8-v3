package com.tlv8.v3.doc.controller.handlers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/DocServer/repository")
public class FilePartTypeByNameHandler {

	@RequestMapping("/file/partTypeByName/**")
	public void handleRequest(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
			throws Exception {
		Map<String, String> paramMap = getParams(paramHttpServletRequest);
		String str = paramMap.get("1");
		if (paramHttpServletRequest.getMethod().equals("GET")) {
			ServletOutputStream localServletOutputStream = paramHttpServletResponse.getOutputStream();
			if ("content".equals(str)) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
				String retstr = "<ns:partType name=\"content\" mimeTypes=\"\" daisyHtml=\"false\" deprecated=\"false\" updateCount=\"1\" id=\"1\" lastModified=\""
						+ format.format(new Date())
						+ "\" lastModifier=\"2\" xmlns:ns=\"http://outerx.org/daisy/1.0\"><ns:labels/><ns:descriptions/></ns:partType>";
				localServletOutputStream.write(retstr.getBytes());
			}
		} else {
			paramHttpServletResponse.sendError(405);
		}
	}

	private Map<String, String> getParams(HttpServletRequest request) {
		Map<String, String> rmap = new HashMap<String, String>();
		String pathinfo = request.getPathInfo();
		pathinfo = pathinfo.replace("/repository/file/partTypeByName/", "");
		if (pathinfo.indexOf("?") > 0) {
			pathinfo = pathinfo.substring(0, pathinfo.indexOf("?"));
		}
		rmap.put("1", pathinfo.split("/")[0]);
		Map<String, String[]> pmap = request.getParameterMap();
		for (String k : pmap.keySet()) {
			rmap.put(k, pmap.get(k)[0]);
		}
		return rmap;
	}

}
