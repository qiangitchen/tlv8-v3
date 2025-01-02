package com.tlv8.v3.flw.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.tlv8.v3.common.action.ActionSupport;
import com.tlv8.v3.common.base.Data;
import com.tlv8.v3.flw.service.SaTaskService;

/**
 * 
 */
@Controller
@Scope("prototype")
public class GetProcessByBillIDAction extends ActionSupport {
	private static final Logger logger = LoggerFactory.getLogger(GetProcessByBillIDAction.class);
	private String sdata1;
	private Data data = new Data();

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	@Autowired
	SaTaskService saTaskService;

	/**
	 * 根据业务ID获取流程信息
	 */
	@ResponseBody
	@RequestMapping("/getProcessByBillIDAction")
	public Object execute() throws Exception {
		try {
			data.setFlag("true");
			data.setData(JSON.toJSONString(saTaskService.selectProcessByBillID(sdata1)));
		} catch (Exception e) {
			data.setFlag("false");
			data.setMessage(e.getMessage());
			logger.error(e.toString());
		}
		return success(data);
	}

	public void setSdata1(String sdata1) {
		this.sdata1 = sdata1;
	}

	public String getSdata1() {
		return sdata1;
	}
}
