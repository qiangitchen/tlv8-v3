package com.tlv8.v3.doc.config;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.tlv8.v3.doc.core.config.ServerConfigInit;
import com.tlv8.v3.doc.lucene.LuceneService;

/**
 * 项目启动时执行的配置
 * 
 * @author 陈乾
 *
 */
@Component
public class DocInitializingConfig implements InitializingBean {
	protected final Logger infolog = LoggerFactory.getLogger(getClass());

	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			//File curfile = new File(System.getProperty("user.dir"));
			//ServerConfigInit.DOC_HOME = curfile.getParentFile().getParentFile().getParentFile().getCanonicalPath();
			File curfile = new File("");
			ServerConfigInit.DOC_HOME = curfile.getCanonicalPath();
			ServerConfigInit.init();// 初始化文件存储位置信息
			LuceneService.start();// 启动索引服务
		} catch (Throwable e) {
			infolog.error("启动文档服务异常!" + e);
			e.printStackTrace();
		}
	}
}
