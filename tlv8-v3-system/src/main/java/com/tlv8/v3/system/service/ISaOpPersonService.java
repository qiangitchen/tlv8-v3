package com.tlv8.v3.system.service;

import java.util.List;

import com.tlv8.v3.system.pojo.SaOpPerson;

public interface ISaOpPersonService {
	/**
	 * 根据主键获取数据对象
	 *
	 * @param sid
	 * @return
	 */
	SaOpPerson selectByPrimaryKey(String sid);

	/**
	 * 根据编号查询人员信息
	 * 
	 * @param scode
	 * @return
	 */
	SaOpPerson selectByCode(String scode);

	/**
	 * 根据编号查询人员信息(编号忽略大小写)
	 * 
	 * @param scode
	 * @return
	 */
	List<SaOpPerson> selectByCodeIgnoreCase(String scode);
	
	/**
	 * 根据手机号查询人员信息
	 * 
	 * @param smobilephone
	 * @return
	 */
	List<SaOpPerson> selectByMobilephone(String smobilephone);

	/**
	 * 插入数据到数据库
	 *
	 * @param obj
	 * @return
	 */
	int insertData(SaOpPerson obj);

	/**
	 * 更新数据
	 *
	 * @param obj
	 * @return
	 */
	int updateData(SaOpPerson obj);

	/**
	 * 删除对象对应的数据
	 *
	 * @param obj
	 * @return
	 */
	int deleteData(SaOpPerson obj);

	/**
	 * 删除指定主键的数据
	 *
	 * @param sid
	 * @return
	 */
	int deleteDataByPrimaryKey(String sid);

	/**
	 * 逻辑删除指定主键的数据
	 *
	 * @param sid
	 * @return
	 */
	int logicDeleteDataByPrimaryKey(String sid);

	/**
	 * 逻辑恢复指定主键的数据
	 *
	 * @param sid
	 * @return
	 */
	int logicRecoveryByPrimaryKey(String sid);
}
