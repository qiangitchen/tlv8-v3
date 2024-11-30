package com.tlv8.v3.doc.controller.handlers;

import java.io.Writer;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tlv8.v3.doc.generator.pojo.DocDocument;
import com.tlv8.v3.doc.generator.service.DocDocumentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/DocServer/repository")
public class FileInfoHandler {
	@Autowired
	DocDocumentService docDocumentService;

	public String getPathPattern() {
		return "/fileinfo/*/*";
	}

	@RequestMapping("/file/fileinfo/{fileID}/**")
	@SuppressWarnings("deprecation")
	public void handleRequest(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse,
			@PathVariable("fileID") String fileID) throws Exception {
		try {
			DocDocument doc = docDocumentService.getDocumentByDocID(fileID);
			Document document = DocumentHelper.parseText("<?xml version=\"1.0\" encoding=\"UTF-8\"?><root></root>");
			if (doc != null) {
				Element root = document.getRootElement();
				Element dom = root.addElement("document");
				dom.setAttributeValue("fileId", doc.getFDocID());
				dom.setAttributeValue("name", doc.getFDocName());
				Element ver = root.addElement("version");
				ver.setAttributeValue("documentId", doc.getFDocID());
				ver.setAttributeValue("documentName", doc.getFDocName());
				Element parts = root.addElement("parts");
				Element part = parts.addElement("part");
				part.setAttributeValue("typeId", doc.getFExtName());
				part.setAttributeValue("mimeType", doc.getFDocType());
				part.setAttributeValue("size", String.valueOf(doc.getFDocSize()));
				part.setAttributeValue("dataChangedInVersion", "last");
			}
			// System.out.println(document.asXML());
			paramHttpServletResponse.setContentType("text/xml");
			Writer writer = paramHttpServletResponse.getWriter();
			writer.write(document.asXML());
			writer.close();
		} catch (Exception e) {
			paramHttpServletResponse.sendError(400, e.toString());
		}
	}

}
