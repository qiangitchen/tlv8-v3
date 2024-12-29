package com.tlv8.v3.oa.service;

import java.util.List;

import com.tlv8.v3.oa.pojo.OaNewsOpen;

/**
 * Created by TLv8 IDE on 2024/03/21.
 */
public interface OaNewsOpenService {

	int deleteByPrimaryKey(String id);
	
	int insert(OaNewsOpen row);
	
	OaNewsOpen selectByPrimaryKey(String id);
	
	List<OaNewsOpen> selectAll();
	
	int updateByPrimaryKey(OaNewsOpen row);
}
