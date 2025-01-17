package com.tlv8.v3.doc.controller.handlers;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tlv8.v3.doc.controller.utils.MimeUtils;
import com.tlv8.v3.doc.core.io.FileDownloader;
import com.tlv8.v3.doc.generator.pojo.DocDocPath;
import com.tlv8.v3.doc.generator.pojo.DocDocument;
import com.tlv8.v3.doc.generator.service.DocDocPathService;
import com.tlv8.v3.doc.generator.service.DocDocumentService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/DocServer/repository")
public class FileViewHandler {
	@Autowired
	DocDocPathService docDocPathService;
	@Autowired
	DocDocumentService docDocumentService;

	@ResponseBody
	@RequestMapping("/file/view/{fileID}/{fVersion}/*")
	public void handleRequest(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse,
			@PathVariable("fileID") String fileID, @PathVariable("fVersion") String fVersion,
			@RequestHeader("User-Agent") String userAgent) throws Exception {
		if (fVersion == null) {
			fVersion = "last";
		}
		DocDocPath dpath = null;
		if ("last".equals(fVersion)) {
			dpath = docDocPathService.getDocDocPathByFileID(fileID);
		} else {
			dpath = docDocPathService.getDocDocPathByFileIDVersion(fileID, Long.parseLong(fVersion));
		}
		DocDocument doc = docDocumentService.getDocumentByDocID(fileID);
		// 文件大小
		int filesize = FileDownloader.getFileSize(fileID, dpath.getFFilePath());
		// 文件创建时间
		paramHttpServletResponse.setDateHeader("Last-Modified", doc.getFAddTime().getTime());
		// 文件流
		InputStream inputStream = FileDownloader.download(fileID, dpath.getFFilePath());
		paramHttpServletResponse.setContentLength(filesize);
		String DocType = doc.getFDocType();
		try {
			DocType = MimeUtils.guessMimeTypeFromExtension(doc.getFExtName().replace(".", ""));
			if (DocType == null) {
				DocType = "application/octet-stream";
			}
		} catch (Exception e) {
		}
		if ("text/plain".equals(DocType)) {
			// 文本类型
			paramHttpServletResponse.setCharacterEncoding("gb2312");
		}
		try {
			// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
			paramHttpServletResponse.setContentType(DocType);
			String formFileName = doc.getFDocName();
			// 针对IE或者以IE为内核的浏览器：
			if (userAgent != null && (userAgent.contains("MSIE") || userAgent.contains("Trident"))) {
				formFileName = java.net.URLEncoder.encode(formFileName, "UTF-8");
			} else {
				// 非IE浏览器的处理：
				formFileName = new String(formFileName.getBytes("UTF-8"), "ISO-8859-1");
			}
			// 2.设置文件头：最后一个参数是设置下载文件名
			paramHttpServletResponse.setHeader("Content-Disposition", "inline; filename=" + formFileName);

			// 3.通过response获取ServletOutputStream对象(out)
			ServletOutputStream out = paramHttpServletResponse.getOutputStream();
			byte[] arrayOfByte = new byte[2048];
			try {
				int i;
				while ((i = inputStream.read(arrayOfByte)) != -1)
					out.write(arrayOfByte, 0, i);
			} catch (Exception we) {
			} finally {
				try {
					out.close(); // 关闭输出流
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				inputStream.close(); // 关闭输入流
			}
		}
	}

}
