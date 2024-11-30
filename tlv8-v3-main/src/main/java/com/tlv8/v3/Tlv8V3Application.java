package com.tlv8.v3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import cn.dev33.satoken.SaManager;

@SpringBootApplication
@ImportResource("classpath:ureport-console-context.xml")
public class Tlv8V3Application {
	private static final Logger logger = LoggerFactory.getLogger(Tlv8V3Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Tlv8V3Application.class, args);
		logger.info("启动成功：Sa-Token配置如下：" + SaManager.getConfig());
	}

}
