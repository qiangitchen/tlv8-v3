package com.tlv8.v3.system.listener;

import com.tlv8.v3.system.bean.ContextBean;

public interface SessionListener {
	void sessionDestroyed(ContextBean centext);
}
