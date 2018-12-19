package org.scriptonbasestar.oauth.client.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.scriptonbasestar.oauth.client.exception.OAuthNetworkException;
import org.scriptonbasestar.oauth.client.exception.OAuthNetworkRemoteException;
import org.scriptonbasestar.oauth.client.type.OAuthHttpVerb;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

/**
 * @author archmagece
 * @since 2016-10-25 16
 */
@Slf4j
public final class HttpRequest {

	private final CloseableHttpClient httpclient;
	private final String url;
	private final ParamList paramList;

	private HttpRequest(String url, ParamList paramList) {
		this.httpclient = HttpClients.createDefault();
		this.url = url;
		this.paramList = paramList;
	}

	private HttpRequest(String url, ParamList paramList, Collection<Header> headers) {
		this.httpclient = HttpClients.custom().setDefaultHeaders(headers).build();
		this.url = url;
		this.paramList = paramList;
	}

	public static HttpRequest create(String url) {
		return new HttpRequest(url, new ParamList());
	}

	public static HttpRequest create(String url, ParamList paramList) {
		return new HttpRequest(url, paramList);
	}

	public static HttpRequest create(String url, ParamList paramList, Collection<Header> headers) {
		return new HttpRequest(url, paramList, headers);
	}

	public static HttpRequest create(String url, Collection<Header> headers) {
		return new HttpRequest(url, new ParamList(), headers);
	}

	public String run(OAuthHttpVerb httpVerb) {
		try {
			switch (httpVerb) {
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
		UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(ParamUtil.generateNameValueList(paramList),
																			 Consts.UTF_8);
		log.debug("post to :" + url);

		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(urlEncodedFormEntity);

		log.debug("Executing request " + httpPost.getRequestLine());
		try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
			return httpResponseToString(response);
		} finally {
			httpclient.close();
		}
	}

	private String getContent() throws IOException {
		log.debug("getContent()");
		HttpGet httpget = new HttpGet(ParamUtil.generateOAuthQuery(url, paramList));
		log.trace("get to :" + httpget.getURI().toURL());

		log.debug("Executing request " + httpget.getRequestLine());
		try (CloseableHttpResponse response = httpclient.execute(httpget)) {
			return httpResponseToString(response);
		} finally {
			httpclient.close();
		}
	}

	private String httpResponseToString(CloseableHttpResponse response) throws IOException {
		log.debug(response.getStatusLine().toString());
		try {
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				throw new OAuthNetworkRemoteException("network connection exception. Remote 서버에서 응답이 없습니다.");
			}

			try (InputStream inStream = entity.getContent()) {
				BufferedInputStream bufInStream = new BufferedInputStream(inStream);
				StringBuilder sb = new StringBuilder();
				byte[] b = new byte[4096];
				for (int n; (n = bufInStream.read(b)) != -1; ) {
					sb.append(new String(b, 0, n));
				}
				return sb.toString();
			} catch (IOException e) {
				throw new OAuthNetworkRemoteException("network stream exception. 데이터를 받아오는 중 문제 발생", e);
			}
		} finally {
			response.close();
		}
	}

}
