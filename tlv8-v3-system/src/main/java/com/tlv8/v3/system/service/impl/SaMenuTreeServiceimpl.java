package com.tlv8.v3.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tlv8.v3.system.mapper.SaMenuTreeMapper;
import com.tlv8.v3.system.pojo.SaMenuTree;
import com.tlv8.v3.system.service.ISaMenuTreeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaMenuTreeServiceimpl implements ISaMenuTreeService {

	@Autowired
	private SaMenuTreeMapper saMenuTreeMapper;

	@Override
	public List<SaMenuTree> selectList() {
		return saMenuTreeMapper.selectList();
	}

	@Override
	public List<SaMenuTree> selectRootList() {
		return saMenuTreeMapper.selectRootList();
	}

	public List<SaMenuTree> selectByPath(String path) {
		return saMenuTreeMapper.selectByPath(path);
	}

	@Override
	public SaMenuTree selectByPrimaryKey(String sid) {
		return saMenuTreeMapper.selectByPrimaryKey(sid);
	}

	@Override
	public List<SaMenuTree> selectByPID(String pid) {
		return saMenuTreeMapper.selectByPID(pid);
	}

	@Override
	public int insertData(SaMenuTree obj) {
		return saMenuTreeMapper.insertData(obj);
	}

	@Override
	public int updateData(SaMenuTree obj) {
		return saMenuTreeMapper.updateData(obj);
	}

	@Override
	public int deleteData(SaMenuTree obj) {
		return saMenuTreeMapper.deleteData(obj);
	}

	@Override
	public int deleteDataByPrimaryKey(String sid) {
		return saMenuTreeMapper.deleteDataByPrimaryKey(sid);
	}

	public SaMenuTree selectByProcessActivity(String process, String activity) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("process", process);
		param.put("activity", activity);
		List<SaMenuTree> list = saMenuTreeMapper.selectByProcessActivity(param);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
