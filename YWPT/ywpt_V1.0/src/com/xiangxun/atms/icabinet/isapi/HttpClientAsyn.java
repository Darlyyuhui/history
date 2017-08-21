package com.xiangxun.atms.icabinet.isapi;

import java.io.IOException;
import java.nio.charset.CodingErrorAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * 异步客户端
 * 
 * @author Administrator
 *
 */
public class HttpClientAsyn implements AutoCloseable {
	private static final Logger logger = Logger.getLogger(HttpClientAsyn.class);	
	private CloseableHttpAsyncClient httpclient;
	private String userName, passwd;
	private boolean isBasicAuthentication = true;
	private Object locker = new Object();
	private AtomicInteger count = new AtomicInteger(0);

	private HttpClientAsyn(String userName, String passwd, boolean isBasicAuthentication) throws IOReactorException {
		this.userName = userName;
		this.passwd = passwd;
		this.isBasicAuthentication = isBasicAuthentication;
		Configure conf = new Configure();
		IOReactorConfig ioReactorConfig = IOReactorConfig.custom()//
				.setIoThreadCount(1)//
				.setConnectTimeout(conf.getConnectionTimeout())//
				.setSoTimeout(conf.getSocketTimeout())//
				.setTcpNoDelay(true) //
				.setSoKeepAlive(false)//
				.setSoReuseAddress(false)//
				.build();
		ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);

		// Create connection configuration
		ConnectionConfig connectionConfig = ConnectionConfig.custom()//
				.setMalformedInputAction(CodingErrorAction.IGNORE) //
				.setUnmappableInputAction(CodingErrorAction.IGNORE)//
				.setCharset(Consts.UTF_8)//
				.build();

		PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(ioReactor);
		connManager.setDefaultConnectionConfig(connectionConfig);
		connManager.setMaxTotal(conf.getPoolMax());// 连接池最大连接数
		connManager.setDefaultMaxPerRoute(conf.getPerRouteMax());// 并发到每个设备的连接数

		// Create global request configuration
		Builder requestConfigBuilder = RequestConfig.custom()// .setCookieSpec(CookieSpecs.DEFAULT)
				.setSocketTimeout(conf.getSocketTimeout())//
				.setConnectTimeout(conf.getConnectionTimeout())//
				.setConnectionRequestTimeout(conf.getRequestTimeout())//
				.setExpectContinueEnabled(true);//

		// Create an HttpClient with the given dependencies and configuration.
		HttpAsyncClientBuilder builder = HttpAsyncClients.custom().setConnectionManager(connManager);
		builder.setDefaultRequestConfig(requestConfigBuilder.build())//
				.setConnectionReuseStrategy(NoConnectionReuseStrategy.INSTANCE);
		httpclient = builder.build();
		httpclient.start();
	}

	public static HttpClientAsyn create(String userName, String passwd, boolean isBasicAuthentication) throws IOReactorException {
		HttpClientAsyn instance = new HttpClientAsyn(userName, passwd, isBasicAuthentication);
		return instance;
	}

	private void inc() {
		synchronized (locker) {
			count.incrementAndGet();
		}
	}

	public void dec() {
		synchronized (locker) {
			count.decrementAndGet();
		}
	}

	public void get(String url, ResponseSucced<HttpResponse> completedCallback, ResponseFailed failedCallBack, Object tags) {
		inc();
		HttpGet httpget = new HttpGet(url);
		setBaseAuthentication(httpget);
		httpclient.execute(httpget, new ResponseCallback("get", url, completedCallback, failedCallBack, tags));
	}

	public void put(String url, String xml, ResponseSucced<HttpResponse> completedCallback, ResponseFailed failedCallBack, Object tags) {
		inc();
		HttpPut httpput = new HttpPut(url);
		setBaseAuthentication(httpput);
		httpput.setHeader("Content-Type", "text/xml");
		StringEntity entity = new StringEntity(xml, Charsets.UTF_8);
		httpput.setEntity(entity);
		httpclient.execute(httpput, new ResponseCallback("put", url, completedCallback, failedCallBack, tags));
	}

	// 设置基本认证
	private void setBaseAuthentication(HttpRequestBase request) {
		if (!isBasicAuthentication)
			return;
		if (StringUtils.isNotEmpty(userName)) {
			String auth = Base64.encodeBase64String((userName + ":" + passwd).getBytes());
			request.setHeader("Authorization", "Basic " + auth);
		}
	}

	@Override
	public void close() throws Exception {
		close(true);
	}

	/**
	 * 关闭连接
	 * 
	 * @param waitCompleted true.等待所有的请求都完成后才关闭;false.立即关闭（部分未完成的请求将丢失）
	 * @throws Exception
	 */
	public void close(boolean waitCompleted) throws Exception {
		while (waitCompleted && count.get() != 0) { // 所有的访问都已经执行完，才能结束
			TimeUnit.MILLISECONDS.sleep(300);
		}
		httpclient.close();
		logger.info("HttpClientAsyn已经关闭");
	}

	/**
	 * 异步连接执行结果处理
	 * 
	 * @author Administrator
	 *
	 */
	class ResponseCallback implements FutureCallback<HttpResponse> {
		private ResponseSucced<HttpResponse> completedCallback;
		private ResponseFailed failedBack;
		private String method, url;
		private Object tags;

		public ResponseCallback(String method, String url, ResponseSucced<HttpResponse> callback, ResponseFailed failedBack, Object tags) {
			this.method = method;
			this.url = url;
			this.completedCallback = callback;
			this.failedBack = failedBack;
			this.tags = tags;
		}

		@Override
		public void completed(HttpResponse result) {
			dec();
			logger.info(String.format("%s %s;StatusCode: %d;tags %s", method, url, result.getStatusLine().getStatusCode(), tags.toString()));
			if (completedCallback != null) {
				boolean responseStatusOK = result != null && result.getStatusLine().getStatusCode() == 200;
				completedCallback.succed(result, responseStatusOK);
			}
		}

		@Override
		public void failed(Exception ex) {
			dec();
			logger.error(String.format("%s %s;%s:%s", method, url, ex.getClass().getName(), ex.getMessage()));
			if (failedBack != null) {
				failedBack.failed(ex);
			}
		}

		@Override
		public void cancelled() {
			dec();
			logger.info("connection cancelled");
		}

	}

	public static String getString(HttpResponse resp, ResponseFailed failedCallback) {
		try {
			return EntityUtils.toString(resp.getEntity());
		} catch (IOException | ParseException ex) {
			if (failedCallback != null)
				failedCallback.failed(ex);
		}
		return null;
	}

	/**
	 * 获取返回状态
	 * 
	 * @param resp
	 * @return
	 */
	public static boolean responseOK(HttpResponse resp) {
		if (resp == null)
			return false;
		try {
			String xml = EntityUtils.toString(resp.getEntity());
			String statusCode = XmlUtils.findValueByTagName(xml, "ResponseStatus/statusCode");
			if (StringUtils.equalsIgnoreCase(statusCode, "1"))
				return true;
		} catch (IOException | ParseException ex) {

		}
		return false;
	}

}
