package com.tlv8.v3.oa.service;

import java.util.List;

import com.tlv8.v3.oa.pojo.OaNewsCount;

/**
 * Created by TLv8 IDE on 2024/03/21.
 */
public interface OaNewsCountService {

	int deleteByPrimaryKey(String id);
	
	int insert(OaNewsCount row);
	
	OaNewsCount selectByPrimaryKey(String id);
	
	List<OaNewsCount> selectAll();
	
	int updateByPrimaryKey(OaNewsCount row);
}
