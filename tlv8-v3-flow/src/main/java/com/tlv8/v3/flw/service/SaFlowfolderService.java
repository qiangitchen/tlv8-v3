package com.tlv8.v3.flw.service;

import java.util.List;

import com.tlv8.v3.flw.pojo.SaFlowfolder;

/**
 * Created by TLv8 IDE on 2023/04/11.
 */
public interface SaFlowfolderService {

	int deleteByPrimaryKey(String id);
	
	int insert(SaFlowfolder row);
	
	SaFlowfolder selectByPrimaryKey(String id);
	
	List<SaFlowfolder> selectAll();
	
	int updateByPrimaryKey(SaFlowfolder row);
	
	List<SaFlowfolder> selectByParent(String sparent);
}
