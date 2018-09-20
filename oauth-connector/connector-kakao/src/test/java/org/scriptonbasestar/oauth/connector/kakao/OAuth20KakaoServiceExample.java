//package org.scriptonbasestar.oauth.connector.kakao;//package org.scripton.test.o20;
//
//import org.junit.Ignore;
//import org.junit.Test;
//import org.scriptonbasestar.oauth.client.core.model.OAuthPersonalConfig;
//import org.scriptonbasestar.oauth.client.nobi.state.RandomStringStateGenerator;
//import org.scriptonbasestar.oauth.client.nobi.token.JsonTokenExtractor;
//import org.scriptonbasestar.oauth.client.o20.model.OAuth20AccessTokenConfig;
//import org.scriptonbasestar.oauth.client.o20.model.OAuth20AuthorizeTokenConfig;
//import org.scriptonbasestar.oauth.client.o20.model.Token20;
//import org.scriptonbasestar.oauth.client.o20.type.VerifierResponseType;
//
///**
// * @author archmagece
// * @since 2016-10-25
// * <p>
// * 카카오는 api-secret이 null이다. 아예 없다.
// */
//public class OAuth20KakaoServiceExample {
//
//	private static final String SERVICE_NAME = "KAKAO";
//	private static final String redirectUri = "http://test1.polypia.net/callback1";
//	private static OAuth20Client oAuth20Service = new OAuth20Client(
//			SERVICE_NAME,
//			new RandomStringStateGenerator(),
//			new OAuthPersonalConfig("client-id", null),
//			OAuth20AuthorizeTokenConfig.builder()
//					.authorizeUrl("https://kauth.kakao.com/oauth/authorize")
//					.callbackUrl(redirectUri)
//
//					//notnull
//					//CODE 코드나옴 code=verifier accessToken 호출해야함
//					//TOKEN 지원안함
//					.responseType(VerifierResponseType.CODE)
//
////					기본 scope 자동적용
////					.scope("PROFILE, TALK_MESSAGE")
//
//					.tokenFormatNobi(new JsonTokenExtractor())
////					.tokenFormatNobi(new StreamOutTokenExtractor())
//					.build(),
//			OAuth20AccessTokenConfig.builder()
//					.accessTokenUrl("https://kauth.kakao.com/oauth/token")
//					.callbackUrl(redirectUri)
//
////					.accessTokenVerb(OAuthHttpVerb.POST)
////					.signatureType(SignatureType.Header)
//
//					.tokenFormatNobi(new JsonTokenExtractor())
////					.tokenFormatNobi(new StreamOutTokenExtractor())
//					.build()
//	);
//
//	public static void main(String[] args) {
//		OAuth2ExampleHelper.test(oAuth20Service, SERVICE_NAME, "https://kapi.kakao.com/v1/user/me");
//	}
//
//	@Ignore
//	@Test
//	public void accessTokenBearerTest() {
//		Token20 token20 = oAuth20Service.getAccessTokenBearer();
//		System.out.println("accessToken " + token20.getAccessToken());
//		System.out.println("expireIn " + token20.getExpireIn());
//		System.out.println("type " + token20.getTokenType());
//		System.out.println("idToken " + token20.getIdToken());
//		System.out.println("refreshToken " + token20.getRefreshToken());
//	}
//
//	@Ignore
//	@Test
//	public void accessTokenBearer_resource_Test() {
//		Token20 token20 = oAuth20Service.getAccessTokenBearer();
//		System.out.println("accessToken " + token20.getAccessToken());
//		System.out.println("expireIn " + token20.getExpireIn());
////		System.out.println("type "+token20.getTokenType());
////		System.out.println("idToken "+token20.getIdToken());
////		System.out.println("refreshToken "+token20.getRefreshToken());
//
//		String result = oAuth20Service.getResource("https://kapi.kakao.com/v1/user/me", token20.getAccessToken());
//		System.out.println(result);
//	}
//
//}
