package com.tlv8.v3.flw.task;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tlv8.v3.common.base.Data;
import com.tlv8.v3.common.base.Sys;
import com.tlv8.v3.flw.base.FlowActivity;
import com.tlv8.v3.flw.base.FlowProcess;
import com.tlv8.v3.flw.base.TaskData;
import com.tlv8.v3.flw.bean.FlowDataBean;
import com.tlv8.v3.flw.expression.BooleanExpression;

/**
 * 特送
 */
@Controller
@Scope("prototype")
public class GetSpecFlowoutInfo extends FlowDataBean {
	private static final Logger logger = LoggerFactory.getLogger(GetSpecFlowoutInfo.class);
	@Autowired
	TaskData taskData;
	private Data data = new Data();

	public Data getData() {
		return this.data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	@ResponseBody
	@RequestMapping("/GetSpecFlowoutInfoAction")
	public Object execute() throws Exception {
		Sys.printMsg("特送...");
		try {
			String processID = taskData.getCurrentProcessID(taskID);
			Sys.printMsg("processID:" + processID);
			flowID = taskData.getFlowID(taskID);
			Sys.printMsg("flowID:" + flowID);
			sdata1 = taskData.getCurrentActivitysData1(taskID);
			Sys.printMsg("sData1:" + sdata1);
			List<FlowActivity> aftAList = new ArrayList<FlowActivity>();
			aftAList = new FlowProcess(processID).getProcessActivitys();
			StringBuffer afterActList = new StringBuffer();
			afterActList.append("[");
			for (int i = 0; i < aftAList.size(); i++) {
				if (i > 0)
					afterActList.append(",");
				String exeGroup = aftAList.get(i).getExcutorGroup();
				if (exeGroup != null) {
					exeGroup = exeGroup.replaceAll("getProcessID\\(\\)", processID);
					exeGroup = exeGroup.replaceAll("getFlowID\\(\\)", flowID);
					exeGroup = exeGroup.replaceAll("getTaskID\\(\\)", taskID);
					exeGroup = exeGroup.replaceAll("getProcesssData1\\(\\)", sdata1);
				}
				String excutorGroup = "";
				if (aftAList.get(i).getExcutorGroup() == null || "".equals(aftAList.get(i).getExcutorGroup())) {
					excutorGroup = BooleanExpression.getScriptExpressionVal(
							BooleanExpression.resolutionExpression("getOrgUnitHasActivity(\"" + processID + "\",\""
									+ aftAList.get(i).getId() + "\",\"FALSE\",\"FALSE\")", request));
				} else {
					excutorGroup = BooleanExpression
							.getScriptExpressionVal(BooleanExpression.resolutionExpression(exeGroup, request));
				}
				afterActList.append("{id:\"" + aftAList.get(i).getId() + "\",name:\""
						+ aftAList.get(i).getActivityname() + "\",type:\"" + aftAList.get(i).getType()
						+ "\",excutorGroup:\"" + excutorGroup + "\",excutorIDs:\"" + aftAList.get(i).getExcutorIDs()
						+ "\",excutorNames:\"" + aftAList.get(i).getExcutorNames() + "\"}");
			}
			afterActList.append("]");
			// System.out.println(afterActList);

			data.setData("{activityListStr:'" + afterActList.toString() + "',flowID:'" + flowID + "',taskID:'" + taskID
					+ "',sData1:'" + sdata1 + "'}");
			data.setFlag("true");
		} catch (Exception e) {
			data.setFlag("true");
			data.setMessage(e.toString());
			logger.error(e.toString());
		}
		return success(data);
	}

}
