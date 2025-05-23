package com.tlv8.v3.flw.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson2.JSON;
import com.tlv8.v3.common.base.Data;
import com.tlv8.v3.common.db.DBUtils;
import com.tlv8.v3.common.utils.AesEncryptUtil;
import com.tlv8.v3.system.utils.ContextUtils;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class OptionController {
	private static final Logger logger = LoggerFactory.getLogger(OptionController.class);

	/**
	 * 获取当前登录人指定环节意见
	 * 
	 * @param request
	 * @param taskID
	 * @param sdata1
	 * @return
	 */
	@RequestMapping("loadOptionByTaskID")
	@ResponseBody
	public Object loadOptionByTaskID(HttpServletRequest request, String taskID, String sdata1) {
		SQL sql = new SQL().SELECT("FAGREETEXT").FROM("OA_FLOWRECORD");
		sql.WHERE("FTASKID=? and FBILLID=? and FCREATEPERID=?");
		Data data = new Data();
		try {
			List<Object> params = new ArrayList<Object>();
			params.add(taskID);
			params.add(sdata1);
			params.add(ContextUtils.getContext().getCurrentPersonID());
			List<Map<String, String>> res = DBUtils.selectStringList("oa", sql.toString(), params, true);
			if (res.size() > 0) {
				data.setData(AesEncryptUtil.encrypt(res.get(0).get("FAGREETEXT")));
			} else {
				data.setData("");
			}
			data.setFlag("true");
		} catch (Exception e) {
			data.setFlag("false");
			logger.error(e.toString());
		}
		return data;
	}

	/**
	 * 获取当前登录人的常用意见
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("loadMyComOption")
	@ResponseBody
	public Object loadMyComOption(HttpServletRequest request) {
		SQL sql = new SQL().SELECT("FCONCLUSIONNAME").FROM("OA_FLOWCONCLUSION");
		sql.WHERE("fcreatorid=?");
		sql.ORDER_BY("forder asc");
		Data data = new Data();
		try {
			List<Object> params = new ArrayList<Object>();
			params.add(ContextUtils.getContext().getCurrentPersonID());
			List<Map<String, String>> res = DBUtils.selectStringList("oa", sql.toString(), params, true);
			data.setData(AesEncryptUtil.encrypt(JSON.toJSONString(res)));
			data.setFlag("true");
		} catch (Exception e) {
			data.setFlag("false");
			logger.error(e.toString());
		}
		return data;
	}
}
