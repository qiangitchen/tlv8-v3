package com.tlv8.v3.flw.controller;

import java.net.URLDecoder;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson2.JSONObject;
import com.tlv8.v3.common.action.ActionSupport;
import com.tlv8.v3.common.utils.AesEncryptUtil;
import com.tlv8.v3.system.base.BaseController;
import com.tlv8.v3.system.bean.ContextBean;
import com.tlv8.v3.flw.base.FlowFile;

/**
 * 流程图加载和保存-编辑控制
 * 
 * @author ChenQian
 */
@Controller
@Scope("prototype")
@SuppressWarnings({ "rawtypes" })
public class FlowDrawControler extends ActionSupport {
	private static final Logger logger = LoggerFactory.getLogger(FlowDrawControler.class);
	private String sprocessid;// SPROCESSID
	private String sprocessname;// SPROCESSNAME
	private String sdrawlg;// SDRAWLG
	private String sprocessacty;// SPROCESSACTY

	@Autowired
	private BaseController baseController;
	@Autowired
	private FlowFile flowFile;

	/**
	 * 获取流程图
	 */
	@ResponseBody
	@PostMapping("/getFlowDrawAction")
	public Object loadFlowDraw() {
		try {
			Map m = flowFile.getFlowDraw(getSprocessid());// 获取流程图
			sdrawlg = (String) m.get("SDRAWLG");
			sprocessacty = (String) m.get("SPROCESSACTY");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		JSONObject json = new JSONObject();
		json.put("sprocessid", sprocessid);
		json.put("sprocessname", sprocessname);
		return json;
	}

	/**
	 * 保存流程图
	 */
	@ResponseBody
	@PostMapping("/saveFlowDrawLGAction")
	public Object saveFlowDrawLG() {
		try {
			ContextBean context = baseController.getContext();
			flowFile.saveFlowDraw(getSprocessid(), getSprocessname(), getSdrawlg(), getSprocessacty(), context);// 保存流程图
		} catch (Exception e) {
			logger.error(e.toString());
		}
		JSONObject json = new JSONObject();
		json.put("sprocessid", sprocessid);
		json.put("sprocessname", sprocessname);
		return json;
	}

	public void setSprocessid(String sprocessid) {
		try {
			this.sprocessid = URLDecoder.decode(sprocessid, "UTF-8");
		} catch (Exception e) {
		}
	}

	public String getSprocessid() {
		return sprocessid;
	}

	public void setSprocessname(String sprocessname) {
		try {
			this.sprocessname = URLDecoder.decode(sprocessname, "UTF-8");
		} catch (Exception e) {
		}
	}

	public String getSprocessname() {
		return sprocessname == null ? "" : sprocessname;
	}

	public void setSdrawlg(String sdrawlg) {
		this.sdrawlg = AesEncryptUtil.desEncrypt(sdrawlg);
		this.sdrawlg = getDoubleDecode(this.sdrawlg);
	}

	public String getSdrawlg() {
		return sdrawlg;
	}

	public String getSprocessacty() {
		return sprocessacty == null ? "" : sprocessacty;
	}

	public void setSprocessacty(String sprocessacty) {
		this.sprocessacty = AesEncryptUtil.desEncrypt(sprocessacty);
		this.sprocessacty = getDoubleDecode(this.sprocessacty);
	}
}
