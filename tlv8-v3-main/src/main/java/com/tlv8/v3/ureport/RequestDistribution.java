package com.tlv8.v3.ureport;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.bstek.ureport.console.RequestHolder;
import com.bstek.ureport.console.ServletAction;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 报表服务请求分发
 * 
 * @author 陈乾
 * @since 2024-11-30
 */
@RestController
public class RequestDistribution {
	public static final String URL = "/ureport";
	private Map<String, ServletAction> actionMap = new HashMap<String, ServletAction>();

	@Autowired
	WebApplicationContext applicationContext;

	@PostConstruct
	void init() {
		Collection<ServletAction> handlers = applicationContext.getBeansOfType(ServletAction.class).values();
		for (ServletAction handler : handlers) {
			String url = handler.url();
			if (actionMap.containsKey(url)) {
				throw new RuntimeException("Handler [" + url + "] already exist.");
			}
			actionMap.put(url, handler);
		}
	}

	@RequestMapping(URL + "/**")
	public void ureport(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getContextPath() + URL;
		String uri = req.getRequestURI();
		String targetUrl = uri.substring(path.length());
		if (targetUrl.length() < 1) {
			outContent(resp, "Welcome to use ureport,please specify target url.");
			return;
		}
		int slashPos = targetUrl.indexOf("/", 1);
		if (slashPos > -1) {
			targetUrl = targetUrl.substring(0, slashPos);
		}
		ServletAction targetHandler = actionMap.get(targetUrl);
		if (targetHandler == null) {
			outContent(resp, "Handler [" + targetUrl + "] not exist.");
			return;
		}
		RequestHolder.setRequest(req);
		try {
			targetHandler.execute(req, resp);
		} catch (Exception ex) {
			resp.setCharacterEncoding("UTF-8");
			PrintWriter pw = resp.getWriter();
			Throwable e = buildRootException(ex);
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			String errorMsg = e.getMessage();
			if (StringUtils.isBlank(errorMsg)) {
				errorMsg = e.getClass().getName();
			}
			pw.write(errorMsg);
			pw.close();
			throw new ServletException(ex);
		} finally {
			RequestHolder.clean();
		}
	}

	private Throwable buildRootException(Throwable throwable) {
		if (throwable.getCause() == null) {
			return throwable;
		}
		return buildRootException(throwable.getCause());
	}

	private void outContent(HttpServletResponse resp, String msg) throws IOException {
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		pw.write("<html>");
		pw.write("<header><title>UReport Console</title></header>");
		pw.write("<body>");
		pw.write(msg);
		pw.write("</body>");
		pw.write("</html>");
		pw.flush();
		pw.close();
	}
}
