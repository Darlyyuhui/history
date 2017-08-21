package com.xiangxun.atms.icabinet.isapi;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.nio.charset.CodingErrorAction;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Node;

/**
 * 同步客户端
 * 
 * @author Administrator
 *
 */
public class HttpClient implements AutoCloseable {
	private static final int MAXRETRYTIMES = 10;// 出错后的最大重试次数
	private static HttpClient instance = null;
	private static final Logger logger = Logger.getLogger(HttpClient.class);
	private String userName, passwd;
	private boolean isBasicAuthentication = true;
	private HttpClientContext localContext;
	private CloseableHttpClient hclient;

	private HttpClient(String userName, String passwd, boolean isBasicAuthentication) throws IOReactorException, NoSuchAlgorithmException {
		this.userName = userName;
		this.passwd = passwd;
		this.isBasicAuthentication = isBasicAuthentication;

		SSLContext sslcontext = SSLContext.getDefault();
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.INSTANCE).register("https", new SSLConnectionSocketFactory(sslcontext)).build();

		CookieStore cookieStore = new BasicCookieStore();
		localContext = HttpClientContext.create();
		localContext.setCookieStore(cookieStore);

		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		connectionManager.setMaxTotal(200);

		ConnectionConfig connectionConfig = ConnectionConfig.custom().setMalformedInputAction(CodingErrorAction.IGNORE).setUnmappableInputAction(CodingErrorAction.IGNORE).setCharset(Consts.UTF_8).build();
		connectionManager.setDefaultConnectionConfig(connectionConfig);
		RequestConfig defaultRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).setExpectContinueEnabled(true).build();
		hclient = HttpClients.custom().setConnectionManager(connectionManager).setRetryHandler(createRequestRetryHandler()).setDefaultRequestConfig(defaultRequestConfig).build();
	}

	private static HttpRequestRetryHandler createRequestRetryHandler() {
		HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {

			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
				if (executionCount >= MAXRETRYTIMES) {
					// Do not retry if over max retry count
					return false;
				}
				if (exception instanceof InterruptedIOException) {
					// Timeout
					return false;
				}
				if (exception instanceof UnknownHostException) {
					// Unknown host
					return false;
				}
				if (exception instanceof ConnectTimeoutException) {
					// Connection refused
					return false;
				}
				if (exception instanceof SSLException) {
					// SSL handshake exception
					return false;
				}
				if (exception instanceof NoHttpResponseException) {
					// host lost this request,must be retry
					return true;
				}
				HttpClientContext clientContext = HttpClientContext.adapt(context);
				HttpRequest request = clientContext.getRequest();
				boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
				if (idempotent) {
					// Retry if the request is considered idempotent
					return true;
				}
				return false;
			}

		};
		return myRetryHandler;
	}

	public static HttpClient create(String userName, String passwd, boolean isBasicAuthentication) throws IOReactorException, NoSuchAlgorithmException {
		if (instance == null) {
			instance = new HttpClient(userName, passwd, isBasicAuthentication);
		}
		return instance;
	}

	/**
	 * 执行get操作
	 * 
	 * @param url
	 * @param status 执行后的状态
	 * @return null.没有获取到数据
	 */
	public String get(String url, StatusInfo status) {
		HttpGet httpget = new HttpGet(url);
		setBaseAuthentication(httpget);

		try (CloseableHttpResponse resp = hclient.execute(httpget, localContext)) {
			if (resp != null && resp.getStatusLine().getStatusCode() == 200) {
				if (status != null)
					status.setStatusCode(StatusCode.Completed);
				return EntityUtils.toString(resp.getEntity());
			}
		} catch (ParseException e) {
			logger.error("get exception:" + e);
			if (status != null)
				status.setStatusCode(StatusCode.Exception);
		} catch (IOException ex) {
			if (status != null)
				status.setStatusCode(StatusCode.UnReachable);
		}
		return null;
	}

	/**
	 * 执行put操作
	 * 
	 * @param url
	 * @param xml
	 * @return
	 */
	public boolean put(String url, String xml, StatusInfo statusInfo) {
		HttpPut httpput = new HttpPut(url);
		setBaseAuthentication(httpput);
		httpput.setHeader("Content-Type", "text/xml");
		StringEntity entity = new StringEntity(xml, Charsets.UTF_8);
		httpput.setEntity(entity);

		try (CloseableHttpResponse resp = hclient.execute(httpput, localContext)) {
			if (resp != null && resp.getStatusLine().getStatusCode() == 200) {
				if (statusInfo != null)
					statusInfo.setStatusCode(StatusCode.Completed);
				String xmlResp = EntityUtils.toString(resp.getEntity());
				List<Node> statusNodes = XmlUtils.getNodesByTag(xmlResp, "statusCode");
				if (statusNodes.size() > 0) {
					String status = statusNodes.get(0).getTextContent();
					if (StringUtils.equalsIgnoreCase(status, "1")) {
						return true;
					}
				}
			}
		} catch (IOException e) {
			logger.error("put error:" + e.getMessage());
			if (statusInfo != null)
				statusInfo.setStatusCode(StatusCode.UnReachable);
		}

		return false;
	}

	/**
	 * 设置基本认证信息
	 * 
	 * @param request
	 */
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
		hclient.close();
	}
	
}
