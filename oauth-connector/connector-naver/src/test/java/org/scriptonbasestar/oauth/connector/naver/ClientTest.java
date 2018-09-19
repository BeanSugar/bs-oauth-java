package org.scriptonbasestar.oauth.connector.naver;

import com.google.gson.Gson;
import org.junit.Ignore;
import org.scriptonbasestar.oauth.client.core.model.OAuthPersonalConfig;
import org.scriptonbasestar.oauth.client.core.nobi.RandomStringStateGenerator;
import org.scriptonbasestar.oauth.client.core.nobi.JsonTokenExtractor;
import org.scriptonbasestar.oauth.client.o20.client.OAuth20Client;
import org.scriptonbasestar.oauth.client.o20.model.OAuth20AccessTokenConfig;
import org.scriptonbasestar.oauth.client.o20.model.OAuth20AuthorizeTokenConfig;
import org.scriptonbasestar.oauth.client.o20.type.ResponseFormatType;
import org.junit.Before;
import org.junit.Test;
import org.scriptonbasestar.tool.core.prop.SBPropertiesUtil;

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
				"common-test.properties",
				"base-test.properties"
		);
		propsUserConfig = SBPropertiesUtil.propertiesMaker(
				ClientTest.class,
				"user-test.properties"
		);

		client = new OAuth20Client(
				SERVICE_NAME,
				new RandomStringStateGenerator(),
				new OAuthPersonalConfig(propBaseCofig.getProperty("naver.apiKey"), propBaseCofig.getProperty("naver.secret")),
				OAuth20AuthorizeTokenConfig.builder()
						.authorizeUrl("https://nid.naver.com/oauth2.0/authorize")
						.callbackUrl(propsUserConfig.getProperty("context.root.url")+ propBaseCofig.getProperty("naver.redirectUrl"))

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
						.callbackUrl("")

//					.accessTokenVerb(OAuthHttpVerb.POST)
//					.signatureType(SignatureType.Header)

						.tokenFormatNobi(new JsonTokenExtractor())
//					.tokenFormatNobi(new TokenStreamOutExtractor())
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
