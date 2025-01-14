package com.tlv8.v3.doc.controller.handlers;

import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tlv8.v3.doc.controller.impl.FileDeleter;
import com.tlv8.v3.doc.core.io.atr.FileAttribute;
import com.tlv8.v3.doc.core.utils.FileExtArray;
import com.tlv8.v3.doc.generator.pojo.DocDocPath;
import com.tlv8.v3.doc.generator.pojo.DocDocument;
import com.tlv8.v3.doc.generator.service.DocDocPathService;
import com.tlv8.v3.doc.generator.service.DocDocumentService;
import com.tlv8.v3.doc.lucene.LuceneService;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/DocServer/repository")
@SuppressWarnings({ "rawtypes" })
public class FileCacheCommitHandler {
	@Autowired
	DocDocPathService docDocPathService;
	@Autowired
	DocDocumentService docDocumentService;
	@Autowired
	FileDeleter fileDeleter;

	public String getPathPattern() {
		return "/file/cache/commit";
	}

	public void initHttpHeader(HttpServletResponse paramHttpServletResponse) {
		paramHttpServletResponse.setCharacterEncoding("utf-8");
	}

	@SuppressWarnings("unused")
	@RequestMapping("/file/cache/commit")
	public void handleRequest(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
			throws Exception {
		if (paramHttpServletRequest.getMethod().equals("POST")) {
			StringBuilder localStringBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>");
			ServletInputStream servletinputstream;
			if (("text/xml".equals(paramHttpServletRequest.getContentType()))
					|| ("application/xml".equals(paramHttpServletRequest.getContentType()))) {
				servletinputstream = paramHttpServletRequest.getInputStream();
			} else {
				paramHttpServletResponse.sendError(400, "The post need type of \"text/xml\".");
				return;
			}
			try {
				SAXReader saxreader = new SAXReader();
				Document localObject3 = saxreader.read(servletinputstream);
				List localList = localObject3.selectNodes("//item[kind!='dir']");
				Iterator iterator = localList.iterator();
				while (iterator.hasNext()) {
					Element element = (Element) iterator.next();
					String operation = element.elementText("operation-type");
					String docID = element.elementText("doc-id");
					String fileID = element.elementText("file-id");
					String docType = element.elementText("doc-type");
					String cachename = element.elementText("cache-name");
					String recacheName = element.elementText("revision-cache-name");
					String fileConten = element.elementText("comment-file-content");
					String docName = element.elementText("doc-name");
					String sKind = element.elementText("kind");
					HashMap ocalHashMap = new HashMap();
					if ("new".equals(operation)) {
						DocDocument docdocument = docDocumentService.getDocumentByDocID(cachename);
						docdocument.setFDocName(docName);
						docdocument.setFDocType(sKind);
						docdocument.setFExtName(FileExtArray.getExtName(docName));
						docDocumentService.updateDocument(docdocument);
						FileAttribute fatt = new FileAttribute();
						fatt.setAddTime(docdocument.getFAddTime());
						fatt.setFileExt(docdocument.getFExtName());
						fatt.setFileID(docdocument.getFDocID());
						DocDocPath dpath = docDocPathService.getDocDocPathByFileID(cachename);
						fatt.setFilePath(dpath.getFFilePath());
						fatt.setFileSize(dpath.getFFileSize());
						fatt.setVersion(dpath.getFVersion());
						fatt.setFileName(docName);
						LuceneService.addHandFile(fatt.getFileID(), fatt);
						localStringBuilder.append(String.format(
								"<item><doc-id>%s</doc-id><file-id>%s</file-id><doc-version-id>%s</doc-version-id></item>",
								docID, cachename, 1));
					} else {
						if ("edit".equals(operation)) {
							if (!"".equals(cachename)) {
								// localObject10 =
								// paramRepository.getDocument((String)localObject7,
								// false);
								// if
								// (((str5.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
								// || (str5.equals("application/msword"))) &&
								// (!"".equals(localObject8)))
								// {
								// localObject11 =
								// ((org.outerj.daisy.repository.Document)localObject10).getPart("comment");
								// localObject9 = new
								// String(((Part)localObject11).getData());
								// }
								// localObject5.selectSingleNode("comment-file-content").setText((String)localObject9);
								// synchCustomFields("edit",
								// (Element)localObject5, localHashMap);
								// localObject11 = updateDoc(paramRepository,
								// str2, (String)localObject7,
								// (String)localObject8, (String)localObject9,
								// str4, str5, localHashMap);
								// localStringBuilder.append(String.format("<item><doc-id>%s</doc-id><file-id>%s</file-id><doc-version-id>%s</doc-version-id></item>",
								// new Object[] { str1,
								// ((FileInfo)localObject11).id,
								// ((FileInfo)localObject11).liveVersionID }));
							}
						} else if ("editInfo".equals(operation)) {
							// localObject10 = paramRepository.getDocument(str2,
							// true);
							// localObject11 =
							// ((org.outerj.daisy.repository.Document)localObject10).getCustomField("sFlag");
							// synchCustomFields("editInfo",
							// (Element)localObject5, localHashMap);
							// localHashMap.put("sFlag", localObject11);
							// setCustomFields((org.outerj.daisy.repository.Document)localObject10,
							// localHashMap);
							// ((org.outerj.daisy.repository.Document)localObject10).setName(str4);
							// ((org.outerj.daisy.repository.Document)localObject10).save();
						} else if ("logicDelete".equals(operation)) {
							// logicDeleteDoc(paramRepository, str2);
						} else if ("delete".equals(operation)) {
							if (!"".equals(fileID)) {
								fileDeleter.delete(fileID);// 删除文件
								LuceneService.addMoveID(fileID);// 追加删除索引的ID
							}
						} else if ("logicReverse".equals(operation)) {
							// logicReverseDoc(paramRepository, str2);
						} else if ("flagCommit".equals(operation)) {
							// commitFlag(paramRepository, str2,
							// (String)localObject7);
						}
					}
				}
				List dirlist = localObject3.selectNodes("//item[kind='dir']");
				Iterator diriterat = dirlist.iterator();
				while (diriterat.hasNext()) {
					Element direlement = (Element) diriterat.next();
					String operation = direlement.elementText("operation-type");
					String docPath = direlement.elementText("doc-path");
					String docID = direlement.elementText("doc-id");
					// str3 = str2 + "/" + direlement.elementText("doc-id");
					// localObject7 = paramRepository.getQueryManager();
					// localObject8 =
					// ((QueryManager)localObject7).performQueryReturnKeys("select id,
					// name,#sDocPath where #sDocPath = '"
					// + str3 + "' or #sDocPath like '" + str3 + "/%'",
					// Locale.getDefault());
					// for (localHashMap : localObject8)
					// if ("logicDelete".equals(str1))
					// {
					// logicDeleteDoc(paramRepository,
					// localHashMap.getDocumentId());
					// }
					// else if ("delete".equals(str1))
					// {
					// deleteDoc(paramRepository, localHashMap.getDocumentId());
					// }
					// else
					// {
					// if (!"logicReverse".equals(str1))
					// continue;
					// logicReverseDoc(paramRepository,
					// localHashMap.getDocumentId());
					// }
				}
				localStringBuilder.append("<flag>true</flag>");
				localStringBuilder.append("<message>");
				localStringBuilder.append("commit fileCache success");
				localStringBuilder.append("</message>");
				localStringBuilder.append("</root>");
				Writer writer = paramHttpServletResponse.getWriter();
				writer.write(localStringBuilder.toString());
				writer.close();
			} catch (Exception localException) {
				paramHttpServletResponse.sendError(400, localException.toString());
			}
		} else {
			paramHttpServletResponse.sendError(405);
		}
	}
}
