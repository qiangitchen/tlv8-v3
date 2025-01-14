package com.tlv8.v3.doc.controller.handlers;

import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tlv8.v3.doc.controller.data.FileUploadData;
import com.tlv8.v3.doc.controller.impl.DoupDoc;
import com.tlv8.v3.doc.core.io.FileUploader;
import com.tlv8.v3.doc.core.io.centent.FileIOContent;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/DocServer/repository")
public class FileUploadHandler {
	@Autowired
	FileUploadData fileUploadData;
	@Autowired
	DoupDoc doupDoc;

	public String getPathPattern() {
		return "/file/upload";
	}

	public void initHttpHeader(HttpServletResponse paramHttpServletResponse) {
		paramHttpServletResponse.setCharacterEncoding("utf-8");
		paramHttpServletResponse.setContentType("text/xml");
	}

	@PostMapping("/file/upload")
	public void handleRequest(MultipartFile file, HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		if (paramHttpServletRequest.getMethod().equals("POST")) {
			try {
				FileIOContent rdoc = FileUploader.fileUpload(file, doupDoc);// 保存文件
				fileUploadData.newDocSave(rdoc);// 保存数据
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
				Date cdate = new Date();
				String retstr = "<ns:document id=\"" + rdoc.getFileID() + "\" lastModified=\"" + format.format(cdate)
						+ "\" lastModifier=\"2\" created=\"" + format.format(cdate)
						+ "\" owner=\"3\" private=\"false\" updateCount=\"1\" referenceLanguageId=\"-1\" variantLastModified=\""
						+ format.format(cdate)
						+ "\" variantLastModifier=\"2\" liveVersionId=\"1\" branchId=\"1\" languageId=\"1\" typeId=\"3\" lastVersionId=\"1\" retired=\"false\" newVersionState=\"publish\" newChangeType=\"major\" variantUpdateCount=\"1\" createdFromBranchId=\"-1\" createdFromLanguageId=\"-1\" createdFromVersionId=\"-1\" documentTypeChecksEnabled=\"true\" lastMajorChangeVersionId=\"-1\" liveMajorChangeVersionId=\"-1\" dataVersionId=\"-1\" name=\""
						+ rdoc.getFileName()
						+ "\" validateOnSave=\"true\" xmlns:ns=\"http://outerx.org/daisy/1.0\"><ns:newChangeComment xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/><ns:customFields/><ns:lockInfo hasLock=\"false\"/><ns:collectionIds/><ns:fields/><ns:parts><ns:part typeId=\"1\" size=\""
						+ rdoc.getFileSize() + "\" mimeType=\"" + rdoc.getFileType()
						+ "\" dataChangedInVersion=\"1\"/></ns:parts><ns:links/></ns:document>";
				Writer writer = paramHttpServletResponse.getWriter();
				writer.write(retstr);
				writer.close();
			} catch (Exception localException) {
				StringBuilder localObject1 = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>");
				localObject1.append("<flag>false</flag>");
				localObject1.append("<message>");
				localObject1.append("upload doc failure");
				localObject1.append(localException.getMessage());
				localObject1.append("</message>");
				localObject1.append("</root>");
				Writer localObject2 = paramHttpServletResponse.getWriter();
				localObject2.write(localObject1.toString());
				localObject2.close();
				localException.printStackTrace();
			}
		} else {
			paramHttpServletResponse.sendError(405);
		}
	}
}
