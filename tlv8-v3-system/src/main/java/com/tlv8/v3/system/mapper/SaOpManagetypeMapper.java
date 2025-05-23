package com.tlv8.v3.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.tlv8.v3.system.pojo.SaOpManagetype;

@DS("system")
@Mapper
public interface SaOpManagetypeMapper {
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
	 * @param saoporg
	 * @return
	 */
	int insertData(SaOpManagetype obj);

	/**
	 * 更新数据
	 * 
	 * @param saoporg
	 * @return
	 */
	int updateData(SaOpManagetype obj);

	/**
	 * 删除对象对应的数据
	 * 
	 * @param saoporg
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
