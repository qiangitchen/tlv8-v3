package com.tlv8.v3.doc.clt.service;

import java.util.List;

import com.tlv8.v3.doc.clt.pojo.SaDocnode;

/**
 * Created by TLv8 IDE on 2023/04/19.
 */
public interface SaDocnodeService {

	int deleteByPrimaryKey(String id);
	
	int insert(SaDocnode row);
	
	SaDocnode selectByPrimaryKey(String id);
	
	List<SaDocnode> selectAll();
	
	int updateByPrimaryKey(SaDocnode row);
	
	List<SaDocnode> selectByDocpath(String docpath);
	
	List<SaDocnode> selectByDocDisplayPath(String docpath);
	
	SaDocnode selectByFileID(String fileID);
}
