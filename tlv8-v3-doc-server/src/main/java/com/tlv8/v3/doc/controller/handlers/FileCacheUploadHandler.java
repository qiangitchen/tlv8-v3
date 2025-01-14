package com.tlv8.v3.doc.controller.handlers;

import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.tlv8.v3.doc.controller.data.FileUploadData;
import com.tlv8.v3.doc.controller.impl.DoupDoc;
import com.tlv8.v3.doc.core.io.FileUploader;
import com.tlv8.v3.doc.core.io.centent.FileIOContent;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/DocServer/repository")
public class FileCacheUploadHandler {
	protected Logger requestErrorLogger = LoggerFactory.getLogger(getClass());

	@Autowired
	FileUploadData fileUploadData;
	@Autowired
	DoupDoc doupDoc;

	@RequestMapping("/file/cache/upload")
	public void handleRequest(MultipartFile file, HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		if (paramHttpServletRequest.getMethod().equals("POST")) {
			StringBuilder localStringBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>");
			try {
				FileIOContent rdoc = FileUploader.fileUpload(file, doupDoc);// 保存文件
				fileUploadData.newDocSave(rdoc);// 保存数据
				localStringBuilder
						.append(String.format("<file mediatype=\"%s\" file-name=\"%s\" fileSize=\"%s\"></file>",
								rdoc.getFileType(), rdoc.getFileID(), rdoc.getFileSize() + ""));
			} catch (Exception localException) {
				requestErrorLogger.error(localException.toString());
				localStringBuilder.append("<flag>false</flag>");
				localStringBuilder.append("<message>");
				localStringBuilder.append("upload fileCache  failure");
				localStringBuilder.append(localException.getMessage());
				localStringBuilder.append("</message>");
				localException.printStackTrace();
			}
			localStringBuilder.append("</root>");
			PrintWriter localPrintWriter = paramHttpServletResponse.getWriter();
			localPrintWriter.write(localStringBuilder.toString());
			localPrintWriter.close();
		} else {
			paramHttpServletResponse.sendError(405);
		}
	}

}
