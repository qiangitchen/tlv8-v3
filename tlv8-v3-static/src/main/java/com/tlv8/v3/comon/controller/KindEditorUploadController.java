package com.tlv8.v3.comon.controller;

import java.util.Arrays;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson2.JSONObject;
import com.tlv8.v3.common.utils.doc.DocSvrUtils;
import com.tlv8.v3.doc.clt.components.DocClient;
import com.tlv8.v3.doc.clt.obj.DocUploadRes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * kindeditor 上传文件（图片、flash、多媒体、文件）
 */
@RestController
@RequestMapping("/kindeditor")
public class KindEditorUploadController {
	static HashMap<String, String> extMap = new HashMap<String, String>();
	// 定义允许上传的文件扩展名
	static {
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,pdf,txt,zip,rar,gz,bz2");
	}

	@Autowired
	DocClient docClient;

	@RequestMapping("/kindEditorUpload")
	public Object uploadFile(@RequestParam(value = "imgFile", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		JSONObject json = new JSONObject();
		if (file == null) {
			json.put("error", 1);
			json.put("message", "请选择文件。");
		} else {
			String fileName = file.getOriginalFilename();
			long fileSize = file.getSize();
			// 最大文件大小
			long maxSize = 50000000;
			// 检查文件大小
			if (fileSize > maxSize) {
				json.put("error", 1);
				json.put("message", "上传文件大小超过限制。");
				return json;
			}
			String dirName = request.getParameter("dir");
			if (dirName == null) {
				dirName = "image";
			}
			if (!extMap.containsKey(dirName)) {
				json.put("error", 1);
				json.put("message", "目录名不正确。");
				return json;
			}
			// 检查扩展名
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if (!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)) {
				json.put("error", 1);
				json.put("message", "上传文件扩展名是不允许的扩展名。\\n只允许" + extMap.get(dirName) + "格式。");
				return json;
			}
			try {
				String docPath = "/root/kindeditor/" + dirName;
				DocUploadRes docres = docClient.uploadFile(file, docPath);
				json.put("url", DocSvrUtils.getViewUrl(docres.getFileID()));
				json.put("error", 0);
				json.put("info", "上传成功！");
			} catch (Exception e) {
				json.put("error", 1);
				json.put("info", "上传失败！");
				e.printStackTrace();
			}
		}
		return json;
	}

}
