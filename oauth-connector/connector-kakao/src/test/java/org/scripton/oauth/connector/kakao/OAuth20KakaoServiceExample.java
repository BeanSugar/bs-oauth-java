package org.scripton.oauth.connector.kakao;//package org.scripton.test.o20;

import com.fasterxml.jackson.core.type.TypeReference;
import org.scripton.test.OAuth2ExampleHelper;
import org.scripton.test.ReadSetting;
import org.scriptonbasestar.oauth.client.DefaultOAuth2ResourceFunction;
import org.scriptonbasestar.oauth.client.OAuth2AccessTokenEndpointFunction;
import org.scriptonbasestar.oauth.client.OAuth2GenerateAuthorizeEndpointFunction;
import org.scriptonbasestar.oauth.client.OAuth2ResourceFunction;
import org.scriptonbasestar.oauth.client.nobi.LocalTokenStorage;
import org.scriptonbasestar.oauth.client.nobi.TokenStorage;
import org.scriptonbasestar.oauth.client.nobi.state.RandomStringStateGenerator;
import org.scriptonbasestar.oauth.client.nobi.token.JsonTokenExtractor;
import org.scriptonbasestar.oauth.client.nobi.token.TokenExtractor;
import org.scriptonbasestar.oauth.client.type.OAuthHttpVerb;

/**
 * @author archmagece
 * @since 2016-10-25
 * <p>
 * 카카오는 api-secret이 null이다. 아예 없다.
 */
public class OAuth20KakaoServiceExample {

	private static final String SERVICE_NAME = "KAKAO";
	private static final ReadSetting readSetting = ReadSetting.readFile(System.getProperty("user.home") + "/.devenv/oauth/" + SERVICE_NAME + ".cfg");

	private static final String CLIENT_ID = readSetting.getProperty("client_id");
	private static final String CLIENT_SECRET = readSetting.getProperty("client_secret");
	private static final String SCOPE = readSetting.getProperty("scope");
	private static final String REDIRECT_URI = readSetting.getProperty("redirect_uri");
	private static final String RESOURCE_PROFILE_URI = readSetting.getProperty("resource_profile_endpoint");

	private static final String AUTHORIZE_ENDPOINT = readSetting.getProperty("authorize_endpoint");
	private static final String ACCESS_TOKEN_ENDPOINT = readSetting.getProperty("access_token_endpoint");
	private static final String REVOKE_ENDPOINT = readSetting.getProperty("revoke_endpoint");

	private static final OAuth2KakaoConfig serviceConfig = new OAuth2KakaoConfig(CLIENT_ID,
																				 CLIENT_SECRET,
																				 AUTHORIZE_ENDPOINT,
																				 SCOPE,
																				 ACCESS_TOKEN_ENDPOINT,
																				 OAuthHttpVerb.POST);
	//	private static final TokenExtractor<OAuth2KakaoTokenRes> tokenExtractor = new PrintTokenExtractor<>();
	private static final TokenExtractor<OAuth2KakaoTokenRes> tokenExtractor = new JsonTokenExtractor<>(new TypeReference<OAuth2KakaoTokenRes>() {});
	private static final TokenStorage tokenStorage = new LocalTokenStorage();

	private static final OAuth2ExampleHelper<OAuth2KakaoTokenRes> exampleHelper = new OAuth2ExampleHelper<>(SERVICE_NAME,
																											new RandomStringStateGenerator(
																													SERVICE_NAME));
	private static final OAuth2GenerateAuthorizeEndpointFunction authorizeEndpointFunction = new OAuth2KakaoGenerateAuthorizeEndpointFunction(
			serviceConfig,
			REDIRECT_URI);
	private static final OAuth2AccessTokenEndpointFunction<OAuth2KakaoTokenRes> tokenFunction = new OAuth2KakaoAccesstokenFunction(
			serviceConfig,
			tokenExtractor,
			tokenStorage,
			REDIRECT_URI);
	private static final OAuth2ResourceFunction<String> resourceFunction = new DefaultOAuth2ResourceFunction(
			RESOURCE_PROFILE_URI);


	public static void main(String[] args) {
		exampleHelper.test(authorizeEndpointFunction, tokenFunction, resourceFunction);
	}

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
//		String result = oAuth20Service.getResource("https://graph.facebook.com/v2.8/pizzahutkorea/feed", token20.getAccessToken());
//		System.out.println(result);
//	}

}
