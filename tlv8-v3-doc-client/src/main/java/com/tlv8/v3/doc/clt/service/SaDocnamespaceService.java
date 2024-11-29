package com.tlv8.v3.doc.clt.service;

import java.util.List;

import com.tlv8.v3.doc.clt.pojo.SaDocnamespace;

/**
 * Created by TLv8 IDE on 2023/04/19.
 */
public interface SaDocnamespaceService {

	int deleteByPrimaryKey(String id);
	
	int insert(SaDocnamespace row);
	
	SaDocnamespace selectByPrimaryKey(String id);
	
	List<SaDocnamespace> selectAll();
	
	int updateByPrimaryKey(SaDocnamespace row);
}
