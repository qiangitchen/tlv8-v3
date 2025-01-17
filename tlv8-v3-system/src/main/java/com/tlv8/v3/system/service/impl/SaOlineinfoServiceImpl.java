package com.tlv8.v3.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tlv8.v3.system.mapper.SaOnlineinfoMapper;
import com.tlv8.v3.system.pojo.SaOnlineinfo;
import com.tlv8.v3.system.service.ISaOnlineinfoService;

@Service
public class SaOlineinfoServiceImpl implements ISaOnlineinfoService {

	@Autowired
	SaOnlineinfoMapper saOnlineinfoMapper;

	public List<SaOnlineinfo> selectList() {
		return saOnlineinfoMapper.selectList();
	}

	@Override
	public SaOnlineinfo selectByPrimaryKey(String sid) {
		return saOnlineinfoMapper.selectByPrimaryKey(sid);
	}

	@Override
	public SaOnlineinfo selectBySessionID(String sessionid) {
		return saOnlineinfoMapper.selectBySessionID(sessionid);
	}

	@Override
	public int insertData(SaOnlineinfo obj) {
		return saOnlineinfoMapper.insertData(obj);
	}

	@Override
	public int updateData(SaOnlineinfo obj) {
		return saOnlineinfoMapper.updateData(obj);
	}

	@Override
	public int deleteData(SaOnlineinfo obj) {
		return saOnlineinfoMapper.deleteData(obj);
	}

	@Override
	public int deleteDataByPrimaryKey(String sid) {
		return saOnlineinfoMapper.deleteDataByPrimaryKey(sid);
	}

	@Override
	public int deleteDataBySessionID(String sessionid) {
		return saOnlineinfoMapper.deleteDataBySessionID(sessionid);
	}

	public int deleteDataByMachineCode(String machineCode) {
		return saOnlineinfoMapper.deleteDataByMachineCode(machineCode);
	}

	@Override
	public long getCount() {
		return saOnlineinfoMapper.getCount();
	}

}
