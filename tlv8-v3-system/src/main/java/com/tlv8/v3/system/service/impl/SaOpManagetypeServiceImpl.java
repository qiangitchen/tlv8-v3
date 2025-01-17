package com.tlv8.v3.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tlv8.v3.system.mapper.SaOpManagetypeMapper;
import com.tlv8.v3.system.pojo.SaOpManagetype;
import com.tlv8.v3.system.service.ISaOpManagetypeService;

@Service
public class SaOpManagetypeServiceImpl implements ISaOpManagetypeService {

	@Autowired
	SaOpManagetypeMapper saOpManagetypeMapper;

	@Override
	public SaOpManagetype selectByPrimaryKey(String sid) {
		return saOpManagetypeMapper.selectByPrimaryKey(sid);
	}

	@Override
	public int insertData(SaOpManagetype obj) {
		return saOpManagetypeMapper.insertData(obj);
	}

	@Override
	public int updateData(SaOpManagetype obj) {
		return saOpManagetypeMapper.updateData(obj);
	}

	@Override
	public int deleteData(SaOpManagetype obj) {
		return saOpManagetypeMapper.deleteData(obj);
	}

	@Override
	public int deleteDataByPrimaryKey(String sid) {
		return saOpManagetypeMapper.deleteDataByPrimaryKey(sid);
	}
	
	public List<SaOpManagetype> selectAll(){
		return saOpManagetypeMapper.selectAll();
	}

}
