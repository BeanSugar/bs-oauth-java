package org.beansugar.oauth.connector.naver;

import com.google.gson.Gson;
import org.beansugar.oauth.client.base.model.OAuthPersonalConfig;
import org.beansugar.oauth.client.core.model.State;
import org.beansugar.oauth.client.core.nobi.DefaultStateNobi;
import org.beansugar.oauth.client.core.nobi.JsonTokenNobi;
import org.beansugar.oauth.client.o20.client.OAuth20Client;
import org.beansugar.oauth.client.o20.model.OAuth20AccessTokenConfig;
import org.beansugar.oauth.client.o20.model.OAuth20AuthorizeTokenConfig;
import org.beansugar.oauth.client.o20.model.Token20;
import org.beansugar.oauth.client.o20.type.AccessTokenType;
import org.beansugar.oauth.client.o20.type.ResponseFormatType;
import org.beansugar.tools.core.prop.BSPropertiesUtil;
import org.junit.Before;
import org.junit.Test;

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
		propBaseCofig = BSPropertiesUtil.propertiesMaker(
				ClientTest.class,
				"common-test.properties",
				"base-test.properties"
		);
		propsUserConfig = BSPropertiesUtil.propertiesMaker(
				ClientTest.class,
				"user-test.properties"
		);

		client = new OAuth20Client(
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

						.tokenFormatNobi(new JsonTokenNobi())
//					.tokenFormatNobi(new TokenStreamOutNobi())
						.build(),
				OAuth20AccessTokenConfig.builder()
						.accessTokenUrl("https://nid.naver.com/oauth2.0/token")
						//nullable 네이버는 callback 없어도됨
						.callbackUrl("")

//					.accessTokenVerb(OAuthHttpVerb.POST)
//					.signatureType(SignatureType.Header)

						.tokenFormatNobi(new JsonTokenNobi())
//					.tokenFormatNobi(new TokenStreamOutNobi())
						.build()
		);
	}

	@Test
	public void test(){
		State state0 = new DefaultStateNobi(SERVICE_NAME).getState();
		String urlString = client.getAuthorizeUrl(state0);
		System.out.println(urlString);
	}

}
