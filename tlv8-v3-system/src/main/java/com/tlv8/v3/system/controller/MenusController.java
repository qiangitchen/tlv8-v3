package com.tlv8.v3.system.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import com.tlv8.v3.common.domain.AjaxResult;
import com.tlv8.v3.common.utils.FileAndString;
import com.tlv8.v3.system.bean.ContextBean;
import com.tlv8.v3.system.pojo.SaMenu;
import com.tlv8.v3.system.pojo.SaMenuTree;
import com.tlv8.v3.system.service.ISaMenuService;
import com.tlv8.v3.system.service.ISaMenuTreeService;

@Controller
@RequestMapping("/system")
public class MenusController {

	@Autowired
	ISaMenuTreeService saMenuTreeService;

	@Autowired
	ISaMenuService saMenuService;

	@Autowired
	UserController userController;

	@RequestMapping("/Menu/loadMenuTree")
	@ResponseBody
	public Object loadMenuTree() {
		JSONArray jsona = new JSONArray();
		List<SaMenuTree> roots = saMenuTreeService.selectRootList();
		for (SaMenuTree me : roots) {
			JSONObject json = (JSONObject) JSON.toJSON(me);
			json.put("id", me.getSid());
			json.put("key", me.getSid());
			JSONArray child = loadMenuChild(me.getSid());
			if (!child.isEmpty()) {
				json.put("children", child);
			}
			jsona.add(json);
		}
		return AjaxResult.success(jsona);
	}

	private JSONArray loadMenuChild(String pid) {
		JSONArray jsona = new JSONArray();
		List<SaMenuTree> roots = saMenuTreeService.selectByPID(pid);
		for (SaMenuTree me : roots) {
			JSONObject json = (JSONObject) JSON.toJSON(me);
			json.put("id", me.getSid());
			json.put("key", me.getSid());
			JSONArray child = loadMenuChild(me.getSid());
			if (!child.isEmpty()) {
				json.put("children", child);
			}
			jsona.add(json);
		}
		return jsona;
	}

	@RequestMapping("/Menu/loadMenuData")
	@ResponseBody
	public Object loadMenuData(@RequestBody Map<String, String> param) {
		String id = param.get("id");
		return AjaxResult.success(saMenuTreeService.selectByPrimaryKey(id));
	}

	@RequestMapping("/User/getUserMenusArray")
	@ResponseBody
	public Object getUserMenusArray() {
		ContextBean context = userController.getContext();
		List<SaMenu> list = saMenuService.selectList(context.getCurrentPersonID(), context.getCurrentPersonFullID());
		if (list.size() < 1 && "PSN01".equals(context.getCurrentPersonID())) {
			Resource resource = new ClassPathResource("menuList.json");
			try {
				String menuList = FileAndString.FileToString(resource.getFile());
				return AjaxResult.success(JSON.parseArray(menuList));
			} catch (IOException e) {
				e.printStackTrace();
				return AjaxResult.error(e.getMessage());
			}
		}
		return AjaxResult.success(list);
	}

}
