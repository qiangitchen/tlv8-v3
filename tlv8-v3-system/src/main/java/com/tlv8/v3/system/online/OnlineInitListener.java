package com.tlv8.v3.system.online;

import com.tlv8.v3.common.base.Sys;
import com.tlv8.v3.common.mac.License;
import com.tlv8.v3.system.service.ISaOnlineinfoService;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;

public class OnlineInitListener implements ServletContextListener {
	@Autowired
	ISaOnlineinfoService onlineinfosvr;

	public OnlineInitListener(ISaOnlineinfoService onlineinfosvr) {
		this.onlineinfosvr = onlineinfosvr;
	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent event) {
		System.out.println("..............................................");
		Sys.printMsg("初始化Online... ...");
		try {
			String machineCode = License.getMachineCode();
			onlineinfosvr.deleteDataByMachineCode(machineCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Sys.printMsg("初始化完成.");
		System.out.println("..............................................");
	}
}
