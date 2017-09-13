package org.scriptonbasestar.oauth.client.o20.client;

import com.google.gson.JsonParseException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.scriptonbasestar.oauth.client.core.model.State;
import org.scriptonbasestar.oauth.client.core.nobi.StateNobi;
import org.scriptonbasestar.oauth.client.http.HttpRequest;
import org.scriptonbasestar.oauth.client.o20.model.Token20;
import org.scriptonbasestar.tool.core.check.Check;
import org.scriptonbasestar.oauth.client.core.exception.OAuthParamException;
import org.scriptonbasestar.oauth.client.core.type.GrantType;
import org.scriptonbasestar.oauth.client.core.type.OAuthHttpVerb;
import org.scriptonbasestar.oauth.client.http.ParamList;
import org.scriptonbasestar.oauth.client.http.ParamUtil;
import org.scriptonbasestar.oauth.client.o20.OAuth20Constants;
import org.scriptonbasestar.oauth.client.o20.model.OAuth20AccessTokenConfig;
import org.scriptonbasestar.oauth.client.o20.model.OAuth20AuthorizeTokenConfig;
import org.scriptonbasestar.oauth.client.base.model.OAuthPersonalConfig;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author archmagece
 * @since 2016-10-24
 */
public class OAuth20Client {

	private final String serviceName;
	private final StateNobi stateNobi;
	private final OAuthPersonalConfig oAuth20PersonalConfig;
	private final OAuth20AuthorizeTokenConfig oAuth20AuthorizeTokenConfig;
	private final OAuth20AccessTokenConfig oAuth20AccessTokenConfig;
//	private final OAuth20ResourceConfig oAuth20ResourceConfig;

	public OAuth20Client(
			String serviceName,
			StateNobi stateNobi,
			OAuthPersonalConfig oAuth20PersonalConfig,
			OAuth20AuthorizeTokenConfig oAuth20SiteAuthConfig,
			OAuth20AccessTokenConfig oAuth20AccessTokenConfig
	) {
//	,OAuth20AccessTokenConfig oAuth20AccessTokenConfig, OAuth20ResourceConfig oAuth20ResourceConfig) {
		Check.notNull(oAuth20PersonalConfig, "oAuth20PersonalConfig should not be null");
		Check.notNull(oAuth20SiteAuthConfig, "oAuth20AuthorizeTokenConfig should not be null");
		Check.notNull(oAuth20AccessTokenConfig, "oAuth20AccessTokenConfig should not be null");
//		Check.notNull(oAuth20ResourceConfig, "oAuth20ResourceConfig should not be null");
		this.serviceName = serviceName;
		this.stateNobi = stateNobi;
		this.oAuth20PersonalConfig = oAuth20PersonalConfig;
		this.oAuth20AuthorizeTokenConfig = oAuth20SiteAuthConfig;
		this.oAuth20AccessTokenConfig = oAuth20AccessTokenConfig;
//		this.oAuth20ResourceConfig = oAuth20ResourceConfig;
	}

	public State generateState(){
		return stateNobi.getState(serviceName);
	}

	public String getAuthorizeUrl(State state) {
		Check.notNull(state, "state must not null");
		//TODO oob support 기능 현재 없음. -  callback essential : google
		Check.urlCustomPattern(oAuth20AuthorizeTokenConfig.getCallbackUrl(), "callbackUrl must not null. Check oauth service OOB support.");

		ParamList paramList = new ParamList();
		paramList.add(OAuth20Constants.CLIENT_ID.getValue(), oAuth20PersonalConfig.getApiKey());
//		if(oAuth20AuthorizeTokenConfig.getCallbackUrl()!=null){
			paramList.add(OAuth20Constants.REDIRECT_URI.getValue(), oAuth20AuthorizeTokenConfig.getCallbackUrl());
//		}

		if(oAuth20AuthorizeTokenConfig.getResponseType()!=null){
			paramList.add(OAuth20Constants.RESPONSE_TYPE.value, oAuth20AuthorizeTokenConfig.getResponseType().value);
		}
		if (oAuth20AuthorizeTokenConfig.getScope() != null) {
			paramList.add(OAuth20Constants.SCOPE.getValue(), oAuth20AuthorizeTokenConfig.getScope());
		}
		if (state != null) {
			paramList.add(OAuth20Constants.STATE.getValue(), state.getValue());
		}
		try {
			return ParamUtil.generateOAuthQuery(oAuth20AuthorizeTokenConfig.getAuthorizeUrl(), paramList.paramSet());
		} catch (UnsupportedEncodingException e) {
			throw new OAuthParamException("파라미터 생성 오류 "+e.getMessage());
		}
	}

	public Token20 getAccessToken(String verifier, State state) {
		Check.notNullOrEmptyString(verifier, "verifier must not null or empty string");
//		Check.notNull(state, "state must not null string");

		//TODO SignatureType, OAuthHttpVerb에 따라 header/query, get/post 상황별 처리..

		HttpRequest request = HttpRequest.create(oAuth20AccessTokenConfig.getAccessTokenUrl());

		ParamList paramList = new ParamList();

		paramList.add(OAuth20Constants.CLIENT_ID.getValue(), oAuth20PersonalConfig.getApiKey());
		//카카오만 secret을 안씀
		if (oAuth20PersonalConfig.getApiSecret() != null && !oAuth20PersonalConfig.getApiSecret().isEmpty()) {
			paramList.add(OAuth20Constants.CLIENT_SECRET.getValue(), oAuth20PersonalConfig.getApiSecret());
		}
		paramList.add(OAuth20Constants.CODE.value, verifier);

		//왜 필요할까... 리다이렉트 안해주면서 access token 요청시 썼던 주소 보내야함.
		paramList.add(OAuth20Constants.REDIRECT_URI.value, oAuth20AccessTokenConfig.getCallbackUrl());
		paramList.add(OAuth20Constants.GRANT_TYPE.value, oAuth20AccessTokenConfig.getGrantType().value);

		if (state != null) {
			paramList.add(OAuth20Constants.STATE.value, state.getValue());
		}
		request.add(paramList.paramSet());
		switch (oAuth20AccessTokenConfig.getAccessTokenVerb()) {
			case POST:
				return oAuth20AccessTokenConfig.getTokenFormatNobi().extract(request.run(OAuthHttpVerb.POST));
			case GET:
			default:
				return oAuth20AccessTokenConfig.getTokenFormatNobi().extract(request.run(OAuthHttpVerb.GET));
		}
	}

	public Token20 getAccessTokenBearer() {
		HttpRequest request = HttpRequest.create(oAuth20AccessTokenConfig.getAccessTokenUrl());

		ParamList paramList = new ParamList();

		paramList.add(OAuth20Constants.CLIENT_ID.getValue(), oAuth20PersonalConfig.getApiKey());
		//카카오만 secret을 안씀
		if (oAuth20PersonalConfig.getApiSecret() != null && !oAuth20PersonalConfig.getApiSecret().isEmpty()) {
			paramList.add(OAuth20Constants.CLIENT_SECRET.getValue(), oAuth20PersonalConfig.getApiSecret());
		}
//		paramList.add(OAuth20Constants.GRANT_TYPE.getValue(), GrantType.AUTHORIZATION_CODE.value);
		paramList.add(OAuth20Constants.GRANT_TYPE.getValue(), GrantType.CLIENT_CREDENTIALS.value);

//		if (oAuth20AccessTokenConfig.getScope() != null) {
//			paramList.add(OAuth20Constants.SCOPE.value, oAuth20AccessTokenConfig.getScope());
//		}
		request.add(paramList.paramSet());
		switch (oAuth20AccessTokenConfig.getAccessTokenVerb()) {
			case POST:
				return oAuth20AccessTokenConfig.getTokenFormatNobi().extract(request.run(OAuthHttpVerb.POST));
			case GET:
			default:
				return oAuth20AccessTokenConfig.getTokenFormatNobi().extract(request.run(OAuthHttpVerb.GET));
		}
	}

	public String getResource(String resourceUrl, String accessToken) {
		CloseableHttpClient httpclient1 = HttpClients.createDefault();
		try {
			HttpGet httpget1 = new HttpGet(resourceUrl);
			httpget1.addHeader("Authorization", "Bearer " + accessToken);
			System.out.println("Executing request " + httpget1.getRequestLine());
			ResponseHandler<String> responseHandler1 = new ResponseHandler<String>() {
				@Override
				public String handleResponse(
						final HttpResponse response) throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						String result = entity != null ? EntityUtils.toString(entity) : null;
						return result;
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}
			};

			String result = httpclient1.execute(httpget1, responseHandler1);
			return result;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient1.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

//	private Map<String, String> parseHeaders(HttpURLConnection conn) {
//		Map<String, String> headers = new HashMap<>();
//		for (String key : conn.getHeaderFields().keySet()) {
//			headers.put(key, conn.getHeaderFields().get(key).get(0));
//		}
//		return headers;
//	}
//
//	void addHeaders(HttpURLConnection conn, Map<String, String> headers) {
//		for (String key : headers.keySet()) {
//			conn.setRequestProperty(key, headers.get(key));
//		}
//	}



}
