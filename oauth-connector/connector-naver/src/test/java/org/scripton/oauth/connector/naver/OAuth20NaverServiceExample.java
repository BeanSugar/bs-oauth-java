package org.scripton.oauth.connector.naver;

import com.fasterxml.jackson.core.type.TypeReference;
import org.scripton.test.OAuth2ExampleHelper;
import org.scripton.test.ReadSetting;
import org.scriptonbasestar.oauth.client.DefaultOAuth2ResourceFunction;
import org.scriptonbasestar.oauth.client.OAuth2AccessTokenFunction;
import org.scriptonbasestar.oauth.client.OAuth2GenerateAuthorizeUrlFunction;
import org.scriptonbasestar.oauth.client.OAuth2ResourceFunction;
import org.scriptonbasestar.oauth.client.config.OAuthPersonalConfig;
import org.scriptonbasestar.oauth.client.nobi.LocalTokenStorage;
import org.scriptonbasestar.oauth.client.nobi.TokenStorage;
import org.scriptonbasestar.oauth.client.nobi.state.RandomStringStateGenerator;
import org.scriptonbasestar.oauth.client.nobi.token.JsonTokenExtractor;
import org.scriptonbasestar.oauth.client.nobi.token.TokenExtractor;

/**
 * @author archmagece
 * @since 2016-10-25
 */
public class OAuth20NaverServiceExample {

	private static final String      SERVICE_NAME = "NAVER";
	private static final ReadSetting readSetting = ReadSetting.readFile(System.getProperty("user.home") + "/.devenv/oauth/" + SERVICE_NAME + ".cfg");

	private static final String CLIENT_ID = readSetting.getProperty("client_id");
	private static final String CLIENT_SECRET = readSetting.getProperty("client_secret");
	private static final String REDIRECT_URI = readSetting.getProperty("redirect_uri");
	private static final String RESOURCE_PROFILE_URI = readSetting.getProperty("resource_profile_uri");


	private static final OAuth2NaverConfig serviceConfig = new OAuth2NaverConfig();
	private static final OAuthPersonalConfig personalConfig = new OAuthPersonalConfig(
		CLIENT_ID,
		CLIENT_SECRET
	);
//	private static final TokenExtractor<OAuth2NaverTokenRes> tokenExtractor = new PrintTokenExtractor<>();
	private static final TokenExtractor<OAuth2NaverTokenRes> tokenExtractor = new JsonTokenExtractor<>(new TypeReference<OAuth2NaverTokenRes>(){});
	private static final TokenStorage tokenStorage = new LocalTokenStorage();

	private static final OAuth2ExampleHelper<OAuth2NaverTokenRes> exampleHelper = new OAuth2ExampleHelper<>(
		SERVICE_NAME,
		new RandomStringStateGenerator(SERVICE_NAME)
	);
	private static final OAuth2GenerateAuthorizeUrlFunction authorizeUrlFunction = new OAuth2NaverGenerateAuthorizeUrlFunction(
		serviceConfig, personalConfig, REDIRECT_URI
	);
	private static final OAuth2AccessTokenFunction<OAuth2NaverTokenRes> tokenFunction = new OAuth2NaverAccesstokenFunction(
		serviceConfig, personalConfig, tokenExtractor, tokenStorage
	);
	private static final OAuth2ResourceFunction<String> resourceFunction = new DefaultOAuth2ResourceFunction(RESOURCE_PROFILE_URI);


	public static void main(String[] args) {
		exampleHelper.test(
			authorizeUrlFunction,
			tokenFunction,
			resourceFunction
		);
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
