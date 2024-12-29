package com.tlv8.v3.oa.service;

import java.util.List;

import com.tlv8.v3.oa.pojo.OaNewsRelease;

/**
 * Created by TLv8 IDE on 2024/03/25.
 */
public interface OaNewsReleaseService {

	int deleteByPrimaryKey(String id);
	
	int insert(OaNewsRelease row);
	
	OaNewsRelease selectByPrimaryKey(String id);
	
	List<OaNewsRelease> selectAll();
	
	int updateByPrimaryKey(OaNewsRelease row);
}
