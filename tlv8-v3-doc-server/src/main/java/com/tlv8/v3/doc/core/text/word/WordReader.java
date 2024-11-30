package com.tlv8.v3.doc.core.text.word;

import java.io.File;

import org.apache.poi.hslf.extractor.QuickButCruddyTextExtractor;

import com.tlv8.v3.doc.core.inter.IFileReader;
import com.tlv8.v3.doc.core.text.TextReader;

public class WordReader extends TextReader implements IFileReader {
	/*
	 * word文件读取
	 */

	public WordReader(File file, String extName) {
		super(file, extName);
	}

	@Override
	public String readAll() {
		String result = "";
		try {
			result = new QuickButCruddyTextExtractor(file.getAbsolutePath()).getTextAsString();
		}catch (Exception e){
		}
		return result;
	}

}
