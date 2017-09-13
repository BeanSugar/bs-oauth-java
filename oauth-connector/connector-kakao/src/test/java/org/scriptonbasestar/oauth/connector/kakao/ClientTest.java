package org.scriptonbasestar.oauth.connector.kakao;

import com.google.gson.Gson;
import org.junit.Ignore;
import org.scriptonbasestar.oauth.client.base.model.OAuthPersonalConfig;
import org.scriptonbasestar.oauth.client.core.model.State;
import org.scriptonbasestar.oauth.client.core.nobi.DefaultStateNobi;
import org.scriptonbasestar.oauth.client.core.nobi.TokenNobi;
import org.scriptonbasestar.oauth.client.o20.client.OAuth20Client;
import org.scriptonbasestar.oauth.client.o20.model.OAuth20AccessTokenConfig;
import org.scriptonbasestar.oauth.client.o20.model.OAuth20AuthorizeTokenConfig;
import org.scriptonbasestar.oauth.client.o20.model.Token20;
import org.scriptonbasestar.oauth.client.o20.type.AccessTokenType;
import org.scriptonbasestar.oauth.client.o20.type.ResponseFormatType;
import org.junit.Before;
import org.junit.Test;
import org.scriptonbasestar.tool.core.prop.SBPropertiesUtil;

import java.util.Map;
import java.util.Properties;

/**
 * @author archmagece
 * @since 2017-03-02
 */
public class ClientTest {

	public static final String SERVICE_NAME = "facebook";
	private Properties propBaseCofig;
	private Properties propsUserConfig;

	private Gson gson = new Gson();
	OAuth20Client client = null;
	@Before
	public void before(){
		propBaseCofig = SBPropertiesUtil.propertiesMaker(
				ClientTest.class,
				"base-test.properties"
		);
		propsUserConfig = SBPropertiesUtil.propertiesMaker(
				ClientTest.class,
				"user-test.properties"
		);



		client = new OAuth20Client(
				SERVICE_NAME,
				new DefaultStateNobi(),
				new OAuthPersonalConfig(propBaseCofig.getProperty("kakao.apiKey"), null),
				OAuth20AuthorizeTokenConfig.builder()
						.authorizeUrl("https://kauth.kakao.com/oauth/authorize")
						.callbackUrl(propsUserConfig.getProperty("context.root.url")+ propBaseCofig.getProperty("kakao.redirectUrl"))

						//notnull
						//CODE 코드나옴 code=verifier accessToken 호출해야함
						//TOKEN 지원안함
						.responseType(ResponseFormatType.CODE)

//					기본 scope 자동적용
//					.scope("PROFILE, TALK_MESSAGE")

						.tokenFormatNobi(new TokenNobi() {
							@Override
							public Token20 extract(String socialResponse) {
								Map<String,Object> map = gson.fromJson(socialResponse, Map.class);
								return new Token20(map.get("access_token").toString(), ((Double)map.get("expires_in")).intValue(), AccessTokenType.valueOf(map.get("token_type").toString().toUpperCase()), map.get("refresh_token").toString());
							}
						})
//						.tokenFormatNobi((String socialResponse) -> {
//							Map<String,Object> map = gson.fromJson(socialResponse, Map.class);
//							return new Token20(map.get("access_token").toString(), ((Double)map.get("expires_in")).intValue(), AccessTokenType.valueOf(map.get("token_type").toString().toUpperCase()), map.get("refresh_token").toString());
//						})

//					.tokenFormatNobi(new TokenStreamOutNobi())
						.build(),
				OAuth20AccessTokenConfig.builder()
						.accessTokenUrl("https://kauth.kakao.com/oauth/token")
						.callbackUrl(propsUserConfig.getProperty("context.root.url")+ propBaseCofig.getProperty("kakao.redirectUrl"))

//					.accessTokenVerb(OAuthHttpVerb.POST)
//					.signatureType(SignatureType.Header)

						.tokenFormatNobi(new TokenNobi() {
							@Override
							public Token20 extract(String socialResponse) {
								Map<String,Object> map = gson.fromJson(socialResponse, Map.class);
								return new Token20(map.get("access_token").toString(), ((Double)map.get("expires_in")).intValue(), AccessTokenType.valueOf(map.get("token_type").toString().toUpperCase()), map.get("refresh_token").toString());
							}
						})
//						.tokenFormatNobi(socialResponse -> {
//							Map<String,Object> map = gson.fromJson(socialResponse, Map.class);
//							return new Token20(map.get("access_token").toString(), ((Double)map.get("expires_in")).intValue(), AccessTokenType.valueOf(map.get("token_type").toString().toUpperCase()), map.get("refresh_token").toString());
//						})
//					.tokenFormatNobi(new TokenStreamOutNobi())
						.build()
		);
	}

	@Test
	@Ignore
	public void test(){
		String urlString = client.getAuthorizeUrl(client.generateState());
		System.out.println(urlString);
	}

}
