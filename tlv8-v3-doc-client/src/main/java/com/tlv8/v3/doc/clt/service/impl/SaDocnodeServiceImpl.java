package com.tlv8.v3.doc.clt.service.impl;

import org.springframework.stereotype.Service;

import com.tlv8.v3.doc.clt.mapper.SaDocnodeMapper;
import com.tlv8.v3.doc.clt.pojo.SaDocnode;
import com.tlv8.v3.doc.clt.service.SaDocnodeService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
/**
 * Created by TLv8 IDE on 2023/04/19.
 */
@Service
public class SaDocnodeServiceImpl implements SaDocnodeService {
    @Autowired
    private SaDocnodeMapper saDocnodeMapper;
    
    public int deleteByPrimaryKey(String id){
    	return saDocnodeMapper.deleteByPrimaryKey(id);
    }
	
	public int insert(SaDocnode row){
		return saDocnodeMapper.insert(row);
	}
	
	public SaDocnode selectByPrimaryKey(String id){
		return saDocnodeMapper.selectByPrimaryKey(id);
	}
	
	public List<SaDocnode> selectAll(){
		return saDocnodeMapper.selectAll();
	}
	
	public int updateByPrimaryKey(SaDocnode row){
		return saDocnodeMapper.updateByPrimaryKey(row);
	}
	
	public List<SaDocnode> selectByDocpath(String docpath){
		return saDocnodeMapper.selectByDocpath(docpath);
	}
	
	public List<SaDocnode> selectByDocDisplayPath(String docpath){
		return saDocnodeMapper.selectByDocDisplayPath(docpath);
	}
	
	public SaDocnode selectByFileID(String fileID) {
		return saDocnodeMapper.selectByFileID(fileID);
	}

}
