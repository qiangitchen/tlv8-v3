package com.tlv8.v3.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tlv8.v3.common.domain.AjaxResult;
import com.tlv8.v3.common.utils.AesEncryptUtil;
import com.tlv8.v3.common.utils.IDUtils;
import com.tlv8.v3.system.bean.ContextBean;
import com.tlv8.v3.system.pojo.SaMenuTree;
import com.tlv8.v3.system.pojo.SaOpPermission;
import com.tlv8.v3.system.pojo.SaOpRole;
import com.tlv8.v3.system.service.ISaMenuTreeService;
import com.tlv8.v3.system.service.ISysParamsService;
import com.tlv8.v3.system.service.SaOpPermissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.tlv8.v3.common.base.Data;
import com.tlv8.v3.common.redis.RedisCache;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 角色
 */
@Controller
@RequestMapping("/system/Role")
public class RolesController {

	@Autowired
	private ISysParamsService sysParamsService;

	@Autowired
	private SaOpPermissionService saOpPermissionService;

	@Autowired
	ISaMenuTreeService saMenuTreeService;

	@Autowired
	private RedisCache redisCache;

	/**
	 * 获取当前登录人的角色
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getAllRolesAction")
	public Object getAllRoles(HttpServletRequest request) throws Exception {
		Data data = new Data();
		try {
			ContextBean context = redisCache.getCacheObject(StpUtil.getTokenValue());
			String personfid = context.getCurrentPersonFullID();
			List<SaOpRole> list = sysParamsService.getRoles(personfid);
			List<String> rolelist = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {
				rolelist.add(list.get(i).getScode());
			}
			String endata = JSON.toJSONString(rolelist);
			try {
				endata = AesEncryptUtil.encrypt(endata);
			} catch (Exception e) {
			}
			data.setData(endata);
			data.setFlag("true");
		} catch (Exception e) {
			data.setFlag("false");
			data.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * 根据角色id获取菜单权限列表
	 *
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getPermissionsByRoleID")
	public Object getPermissionsByRoleID(@RequestBody Map<String, String> param) {
		String roleid = param.get("roleid");
		return AjaxResult.success(saOpPermissionService.selectByRoleID(roleid));
	}

	/**
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/savePermissions")
	public Object savePermissions(@RequestBody Map<String, String> param) {
		try {
			String roleid = param.get("roleid");
			String menus = param.get("menus");
			String remids = param.get("remids");
			String[] menuArray = menus.split(",");
			String[] remidArray = remids.split(",");
			for (String menuid : menuArray) {
				SaMenuTree menu = saMenuTreeService.selectByPrimaryKey(menuid);
				if (menu == null) {
					continue;
				}
				saOpPermissionService.deleteByRoleMenu(roleid, menuid);
				SaOpPermission permission = new SaOpPermission();
				permission.setSid(IDUtils.getGUID());
				permission.setSmenuid(menuid);
				permission.setSpermissionroleid(roleid);
				permission.setSprocess(menu.getProcess());
				permission.setSactivity(menu.getActivity());
				permission.setSactivityfname(menu.getLabel());
				permission.setSdescription(menu.getLabel());
				permission.setSvalidstate(1);
				permission.setSsequence(1);
				permission.setVersion(0);
				saOpPermissionService.insertData(permission);
			}
			for (String menuid : remidArray) {
				saOpPermissionService.deleteByRoleMenu(roleid, menuid);
			}
			return AjaxResult.success("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("保存失败:" + e.getMessage());
		}
	}

}