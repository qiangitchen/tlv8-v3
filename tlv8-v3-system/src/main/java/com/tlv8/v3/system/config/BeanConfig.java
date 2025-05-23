package com.tlv8.v3.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tlv8.v3.system.action.GetSysParams;
import com.tlv8.v3.system.action.Login;
import com.tlv8.v3.system.action.SAPerson;
import com.tlv8.v3.system.action.WriteLoginLog;

/**
 * 配置动态类
 *
 * @author chenqian
 */
@Configuration
public class BeanConfig {

    @Bean
    public Login initLogin() {
        return new Login();
    }

    @Bean
    public GetSysParams initGetSysParams() {
        return new GetSysParams();
    }

    @Bean
    public SAPerson initSAPerson() {
        return new SAPerson();
    }

    @Bean
    public WriteLoginLog initWriteLoginLog() {
        return new WriteLoginLog();
    }

}
