package com.tlv8.v3.oa.service;

import java.util.List;

import com.tlv8.v3.oa.pojo.OaNewsColumn;

/**
 * Created by TLv8 IDE on 2024/03/21.
 */
public interface OaNewsColumnService {

	int deleteByPrimaryKey(String id);
	
	int insert(OaNewsColumn row);
	
	OaNewsColumn selectByPrimaryKey(String id);
	
	List<OaNewsColumn> selectAll();
	
	int updateByPrimaryKey(OaNewsColumn row);
}
