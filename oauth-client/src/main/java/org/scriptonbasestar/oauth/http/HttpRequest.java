package org.scriptonbasestar.oauth.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.scriptonbasestar.oauth.core.exception.OAuthNetworkException;
import org.scriptonbasestar.oauth.core.exception.OAuthNetworkRemoteException;
import org.scriptonbasestar.oauth.core.type.OAuthHttpVerb;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author archmagece
 * @CreatedAt 2016-10-25 16
 */
@Slf4j
public final class HttpRequest {

	private CloseableHttpClient httpclient;
	private final String url;
	private ParamLister paramLister = new ParamLister();

	private HttpRequest(String url){
		httpclient = HttpClients.createDefault();
		this.url = url;
	}

	public static HttpRequest create(String url){
		return new HttpRequest(url);
	}

	public HttpRequest add(String key, String value){
		paramLister.add(key, value);
		return this;
	}

	public HttpRequest add(Param... params){
		paramLister.add(params);
		return this;
	}

	public HttpRequest add(Collection<Param> params){
		paramLister.add(params);
		return this;
	}

	public String run(OAuthHttpVerb oAuthHttpVerb) {
		try {
			switch (oAuthHttpVerb){
				case POST:
					return postContent();
				case GET:
				default:
					return getContent();
			}
		} catch (IOException e) {
//			e.printStackTrace();
			throw new OAuthNetworkException("extends IOException - 네트워크 오류");
		}
	}

	private String postContent() throws IOException {
		log.debug("postContent()");
		try {
			List<NameValuePair> formParams = new ArrayList<>();
			for(Param param : paramLister.paramSet()){
				log.debug("param : " + param);
				formParams.add(new BasicNameValuePair(param.getKey(), param.getValue()[0]));
			}
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);

			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(urlEncodedFormEntity);

			log.debug("Executing request " + httpPost.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httpPost);

			return httpResponseToString(response);
		} finally {
			httpclient.close();
		}
	}

	private String getContent() throws IOException {
		log.debug("getContent()");
		try {
			HttpGet httpget = new HttpGet(ParamUtil.generateOAuthQuery(url, paramLister.paramSet()));
			log.debug("connect url  " + httpget.getURI().toURL());

			log.debug("Executing request " + httpget.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httpget);

			return httpResponseToString(response);
		} finally {
			httpclient.close();
		}
	}

	private String httpResponseToString(CloseableHttpResponse response) throws IOException {
		try {
			log.debug("----------------------------------------");
			log.debug(response.getStatusLine().toString());

			// Get hold of the response entity
			HttpEntity entity = response.getEntity();

			// If the response does not enclose an entity, there is no need
			// to bother about connection release
			if (entity == null) {
				throw new OAuthNetworkRemoteException("entity가 오지 않았습니다. remote에 문제가 있을 가능성");
			}

			InputStream inStream = entity.getContent();
			try {
//						inStream.read();
				BufferedInputStream bufInStream = new BufferedInputStream(inStream);
				StringBuffer sb = new StringBuffer();
				byte[] b = new byte[4096];
				for (int n; (n = bufInStream.read(b)) != -1;) {
					sb.append(new String(b, 0, n));
				}
				return sb.toString();
			} catch (IOException ex) {
				// In case of an IOException the connection will be released
				// back to the connection manager automatically
//				throw ex;
				throw new OAuthNetworkRemoteException("network stream 오류. 데이터를 받아오는 중 문제 발생");
			} finally {
				// Closing the input stream will trigger connection release
				inStream.close();
			}
		} finally {
			response.close();
		}
	}

}
