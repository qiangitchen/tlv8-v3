package com.tlv8.v3.doc.controller.svlet;

import java.io.IOException;
import java.io.Writer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LogServlet {

	@ResponseBody
	@RequestMapping("/DocServer")
	public void index(ServletRequest request, ServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		Writer writer = response.getWriter();
		writer.write(
				"<HTML>\n<HEAD>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"><TITLE>连接成功!");
		writer.write("</TITLE></HEAD>\n<BODY>\n<H2>文档服务已经成功启动.</H2>\n</BODY>\n</HTML>");
		writer.close();
	}

	@ResponseBody
	@RequestMapping("/DocServer/log")
	public String service(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
			throws ServletException, IOException {
		return "文档服务正在运行~.~";
	}
}
