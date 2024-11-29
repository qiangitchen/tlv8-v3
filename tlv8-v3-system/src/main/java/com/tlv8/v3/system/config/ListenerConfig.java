package com.tlv8.v3.system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tlv8.v3.system.online.OnlineInitListener;
import com.tlv8.v3.system.service.ISaOnlineinfoService;

@Configuration
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ListenerConfig {

	@Autowired
	ISaOnlineinfoService onlineinfosvr;

	@Bean
	public ServletListenerRegistrationBean onlineInitLlistenerRegistrationBean() {
		ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
		srb.setListener(new OnlineInitListener(onlineinfosvr));
		return srb;
	}
	
}
