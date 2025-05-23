package com.tlv8.v3.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.tlv8.v3.system.pojo.SaOpRole;
import com.tlv8.v3.system.pojo.SysParams;

@DS("system")
@Mapper
public interface SysParamsMapper {
	/**
	 * 获取组织信息
	 * 
	 * @param orgid
	 * @return
	 */
	SysParams getOrgInfo(String orgid);

	/**
	 * 获取机构信息
	 * 
	 * @param orgfid
	 * @return
	 */
	SysParams getOgnInfo(String orgfid);

	/**
	 * 获取部门信息
	 * 
	 * @param orgfid
	 * @return
	 */
	SysParams getDeptInfo(String orgfid);

	/**
	 * 获取岗位信息
	 * 
	 * @param orgfid
	 * @return
	 */
	SysParams getPositionInfo(String orgfid);

	/**
	 * 获取人员信息
	 * 
	 * @param personid
	 * @return
	 */
	SysParams getPersonInfo(String personid);

	/**
	 * 获取用户信息
	 * 
	 * @param orgid
	 * @param personid
	 * @return
	 */
	SysParams getUserInfo(Map<String, String> map);

	/**
	 * 获取用户的角色列表
	 * 
	 * @param personfid
	 * @return
	 */
	List<SaOpRole> getRoles(String personfid);
}
