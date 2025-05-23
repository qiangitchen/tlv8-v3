package com.tlv8.v3.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.tlv8.v3.system.pojo.SaOpRole;

@DS("system")
@Mapper
public interface SaOpRoleMapper {
	/**
	 * 根据主键获取数据对象
	 * 
	 * @param sid
	 * @return
	 */
	SaOpRole selectByPrimaryKey(String sid);

	/**
	 * 插入数据到数据库
	 * 
	 * @param saoporg
	 * @return
	 */
	int insertData(SaOpRole obj);

	/**
	 * 更新数据
	 * 
	 * @param saoporg
	 * @return
	 */
	int updateData(SaOpRole obj);

	/**
	 * 删除对象对应的数据
	 * 
	 * @param saoporg
	 * @return
	 */
	int deleteData(SaOpRole obj);

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
	 * @param state
	 * @return
	 */
	int logicRecoveryByPrimaryKey(String sid);
}
