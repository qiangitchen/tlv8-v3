package com.tlv8.v3.common.mac;

import com.tlv8.v3.common.mac.utils.CryptUtils;

public class License {
	public static String getMachineCode() {
		return CryptUtils.getMachineCode();
	}
}
