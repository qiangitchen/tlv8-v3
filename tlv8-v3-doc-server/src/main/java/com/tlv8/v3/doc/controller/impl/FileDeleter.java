package com.tlv8.v3.doc.controller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tlv8.v3.doc.core.io.AbstractFileDeleter;
import com.tlv8.v3.doc.generator.pojo.DocDocPath;
import com.tlv8.v3.doc.generator.service.DocDocPathService;
import com.tlv8.v3.doc.generator.service.DocDocumentService;

@Component
@SuppressWarnings({ "rawtypes", "unchecked" })
public class FileDeleter extends AbstractFileDeleter {
	@Autowired
	DocDocPathService docDocPathService;
	@Autowired
	DocDocumentService docDocumentService;

	/*
	 * 删除所有版本
	 * 
	 * @see com.tlv8.v3.doc.core.inter.IFileDeleter#delete(java.lang.String)
	 */
	public List<Map> delete(String docID) {
		List<Map> rlist = new ArrayList<Map>();
		List<DocDocPath> pathList = docDocPathService.getDocDocPathListByFileID(docID);
		for (int i = 0; i < pathList.size(); i++) {
			DocDocPath path = pathList.get(i);
			Map rmap = new HashMap();
			deleteFile(docID, path.getFFilePath());// 删除文件
			rmap.put("file-id", path.getFFileID());
			rmap.put("doc-version", path.getFVersion());
			rlist.add(rmap);
		}
		// 删除数据
		docDocPathService.deleteDocDocPathByFileID(docID);
		docDocumentService.deleteDocumentByDocID(docID);
		return rlist;
	}

	/*
	 * 删除指定版本
	 * 
	 * @see com.tlv8.v3.doc.core.inter.IFileDeleter#deleteVersion(java.lang .String,
	 * long)
	 */
	public Map deleteVersion(String docID, long version) {
		Map rmap = new HashMap();
		List<DocDocPath> pathList = docDocPathService.getDocDocPathListByFileID(docID);
		rmap.put("liveVersionId", version);
		long lastVersionId = 1;
		DocDocPath path = null;
		for (int i = 0; i < pathList.size(); i++) {
			DocDocPath mpath = pathList.get(i);
			if (mpath.getFVersion() == version) {
				path = mpath;
			}
			if (lastVersionId < mpath.getFVersion()) {
				lastVersionId = mpath.getFVersion();
			}
		}
		rmap.put("lastVersionId", lastVersionId);
		// 逻辑上不能删除最终版本
		if (path != null && lastVersionId != version) {
			deleteFile(docID, path.getFFilePath());// 删除文件
			docDocPathService.deleteDocDocPath(path.getFID());// 删除数据
		}
		return rmap;
	}
}
