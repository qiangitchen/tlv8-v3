package com.tlv8.v3.opm;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tlv8.v3.common.base.Data;
import com.tlv8.v3.common.jgrid.BasegetGridAction;

/**
 * 获取人员信息（列表形式）
 * 
 * @author ChenQian
 */
@Controller
@Scope("prototype")
public class GetPersonInfo extends BasegetGridAction {

	public Data getData() {
		return this.data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	@ResponseBody
	@RequestMapping(value = "/getPersonInfo", produces = "application/json;charset=UTF-8")
	public Object execute() throws Exception {
		data = new Data();
		String r = "true";
		String m = "success";
		String f = "";
		try {
			int page = (getPage() == 1) ? 0 : getPage();
			int row = getRows();
			r = createGrid(row, page);
			f = "true";
		} catch (Exception e) {
			m = "操作失败：" + e.getMessage();
			f = "false";
			e.printStackTrace();
		}
		data.setData(r);
		data.setFlag(f);
		data.setMessage(m);
		data.setPage(page);
		data.setAllpage(allpage);
		data.setGridid(gridid);
		return success(data);
	}

}
