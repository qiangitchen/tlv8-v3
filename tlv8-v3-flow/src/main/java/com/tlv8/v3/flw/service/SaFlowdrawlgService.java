package com.tlv8.v3.flw.service;

import java.util.List;

import com.tlv8.v3.flw.pojo.SaFlowdrawlg;

/**
 * Created by TLv8 IDE on 2023/04/11.
 */
public interface SaFlowdrawlgService {

	int deleteByPrimaryKey(String id);
	
	int insert(SaFlowdrawlg row);
	
	SaFlowdrawlg selectByPrimaryKey(String id);
	
	List<SaFlowdrawlg> selectAll();
	
	int updateByPrimaryKey(SaFlowdrawlg row);
	
	SaFlowdrawlg selectBySprocessid(String sprocessid);
	
	List<SaFlowdrawlg> seachProcessByPath(String path);
	
	List<SaFlowdrawlg> selectByFolderID(String sfolderid);
}