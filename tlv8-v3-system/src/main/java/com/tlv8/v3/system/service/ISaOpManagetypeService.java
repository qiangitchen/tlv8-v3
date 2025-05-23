package com.tlv8.v3.system.service;

import java.util.List;

import com.tlv8.v3.system.pojo.SaOpManagetype;

public interface ISaOpManagetypeService {
	/**
	 * 根据主键获取数据对象
	 * 
	 * @param sid
	 * @return
	 */
	SaOpManagetype selectByPrimaryKey(String sid);
	
	/**
	 * 插入数据到数据库
	 * 
	 * @param obj
	 * @return
	 */
	int insertData(SaOpManagetype obj);

	/**
	 * 更新数据
	 * 
	 * @param obj
	 * @return
	 */
	int updateData(SaOpManagetype obj);

	/**
	 * 删除对象对应的数据
	 * 
	 * @param obj
	 * @return
	 */
	int deleteData(SaOpManagetype obj);

	/**
	 * 删除指定主键的数据
	 * 
	 * @param sid
	 * @return
	 */
	int deleteDataByPrimaryKey(String sid);
	
	List<SaOpManagetype> selectAll();

}
