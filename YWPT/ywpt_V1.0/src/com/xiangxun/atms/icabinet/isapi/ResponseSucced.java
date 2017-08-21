package com.xiangxun.atms.icabinet.isapi;

public interface ResponseSucced<T> {
	void succed(T result, boolean responseStatusOK);	
}
