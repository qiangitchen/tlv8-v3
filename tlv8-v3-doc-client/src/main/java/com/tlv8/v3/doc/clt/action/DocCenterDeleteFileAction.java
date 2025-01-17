package com.tlv8.v3.doc.clt.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tlv8.v3.common.base.Data;
import com.tlv8.v3.common.base.Sys;
import com.tlv8.v3.common.data.BaseDeleteAction;
import com.tlv8.v3.system.controller.UserController;
import com.tlv8.v3.doc.clt.doc.Doc;
import com.tlv8.v3.doc.clt.doc.Docs;

/**
 * 文档中心-删除文件接口
 * 
 * @author 陈乾
 *
 */
@Controller
@Scope("prototype")
public class DocCenterDeleteFileAction extends BaseDeleteAction {
	private Data data = new Data();

	public Data getdata() {
		return data;
	}

	public void setdata(Data data) {
		this.data = data;
	}

	@Autowired
	Docs docs;

	@ResponseBody
	@RequestMapping("/docCenterDeleteFileAction")
	public Object execute() throws Exception {
		String userid = new UserController().getContext().getCurrentPersonID();
		if ((userid == null) || ("".equals(userid))) {
			this.data.setFlag("timeout");
			Sys.packErrMsg("未登录或登录已超时，不允许操作!");
			return "success";
		}
		try {
			Doc doc = docs.queryDocById(rowid);
			doc.deleteFile();
		} catch (Exception localException1) {
		}
		String r = "";
		String m = "success";
		String f = "";
		try {
			r = deleteData();
			f = "true";
		} catch (Exception e) {
			m = "操作失败：" + e.toString();
			f = "false";
			e.printStackTrace();
		}
		data.setData(r);
		data.setFlag(f);
		data.setMessage(m);
		return data;
	}
}
