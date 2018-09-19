package org.scriptonbasestar.oauth.o20;

import org.scriptonbasestar.oauth.client.core.nobi.RandomStringStateGenerator;
import org.scriptonbasestar.oauth.client.o20.client.OAuth20Client;
import org.scriptonbasestar.oauth.client.o20.model.Token20;
import org.junit.Ignore;
import org.junit.Test;
import org.scriptonbasestar.oauth.client.core.nobi.JsonTokenExtractor;
import org.scriptonbasestar.oauth.client.o20.model.OAuth20AccessTokenConfig;
import org.scriptonbasestar.oauth.client.o20.model.OAuth20AuthorizeTokenConfig;
import org.scriptonbasestar.oauth.client.core.model.OAuthPersonalConfig;
import org.scriptonbasestar.oauth.client.o20.type.ResponseFormatType;

/**
 * @author archmagece
 * @since 2016-10-25
 */
public class OAuth20NaverServiceExample {

	private static final String SERVICE_NAME = "NAVER";
	private static final String redirectUri = "http://test1.polypia.net/callback1";
	private static OAuth20Client oAuth20Service = new OAuth20Client(
			SERVICE_NAME,
			new RandomStringStateGenerator(),
			new OAuthPersonalConfig("client-id", "client-secret"),
			OAuth20AuthorizeTokenConfig.builder()
					.authorizeUrl("https://nid.naver.com/oauth2.0/authorize")
					.callbackUrl(redirectUri)

					//nullable default CODE
					//CODE 코드나옴 code=verifier accessToken 호출해야함
//					.responseType(ResponseFormatType.CODE)
					//TOKEN access token.. 사용자별 토큰?
					.responseType(ResponseFormatType.TOKEN)

//					기본 scope 자동적용
//					.scope("")

					.tokenFormatNobi(new JsonTokenExtractor())
//					.tokenFormatNobi(new TokenStreamOutExtractor())
					.build(),
			OAuth20AccessTokenConfig.builder()
					.accessTokenUrl("https://nid.naver.com/oauth2.0/token")
					//nullable 네이버는 callback 없어도됨
					.callbackUrl(redirectUri)

//					.accessTokenVerb(OAuthHttpVerb.POST)
//					.signatureType(SignatureType.Header)

					.tokenFormatNobi(new JsonTokenExtractor())
//					.tokenFormatNobi(new TokenStreamOutExtractor())
					.build()
	);

	public static void main(String[] args) {
		OAuth2ExampleHelper.test(oAuth20Service, SERVICE_NAME, "https://openapi.naver.com/v1/nid/me");
	}

	@Ignore
	@Test
	public void accessTokenBearerTest() {
		Token20 token20 = oAuth20Service.getAccessTokenBearer();
		System.out.println("accessToken " + token20.getAccessToken());
		System.out.println("expireIn " + token20.getExpireIn());
		System.out.println("type " + token20.getTokenType());
		System.out.println("idToken " + token20.getIdToken());
		System.out.println("refreshToken " + token20.getRefreshToken());
	}

	@Ignore
	@Test
	public void accessTokenBearer_resource_Test() {
		Token20 token20 = oAuth20Service.getAccessTokenBearer();
		System.out.println("accessToken " + token20.getAccessToken());
		System.out.println("expireIn " + token20.getExpireIn());
//		System.out.println("type "+token20.getTokenType());
//		System.out.println("idToken "+token20.getIdToken());
//		System.out.println("refreshToken "+token20.getRefreshToken());

		String result = oAuth20Service.getResource("https://graph.facebook.com/v2.8/pizzahutkorea/feed", token20.getAccessToken());
		System.out.println(result);
	}

}
