package com.tlv8.v3.system.service;

import java.util.List;

import com.tlv8.v3.system.pojo.SaOprolemanage;

/**
 * Created by TLv8 IDE on 2024/11/23.
 */
public interface SaOprolemanageService {

	int deleteByPrimaryKey(String id);
	
	int insert(SaOprolemanage row);
	
	SaOprolemanage selectByPrimaryKey(String id);
	
	List<SaOprolemanage> selectAll();
	
	int updateByPrimaryKey(SaOprolemanage row);
}