package com.tlv8.v3.opm;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tlv8.v3.common.action.ActionSupport;
import com.tlv8.v3.common.base.Data;
import com.tlv8.v3.system.service.ISaOpManagementService;

@Controller
@Scope("prototype")
public class DeleteManagement extends ActionSupport {
	private String checkedIDs;
	private Data data;

	@Autowired
	ISaOpManagementService saOpManagementService;

	/**
	 * 删除权限
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteManagement", produces = "application/json;charset=UTF-8")
	public Object execute() throws Exception {
		setData(new Data());
		try {
			String[] ids = checkedIDs.split(",");
			for (int i = 0; i < ids.length; i++) {
				saOpManagementService.deleteDataByPrimaryKey(ids[i]);
			}
			data.setFlag("true");
		} catch (Exception e) {
			data.setFlag("false");
			data.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return data;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public String getCheckedIDs() {
		return checkedIDs;
	}

	public void setCheckedIDs(String checkedIDs) {
		this.checkedIDs = checkedIDs;
		try {
			this.checkedIDs = URLDecoder.decode(checkedIDs, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
