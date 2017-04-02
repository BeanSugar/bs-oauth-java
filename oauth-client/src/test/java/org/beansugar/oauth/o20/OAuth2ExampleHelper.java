package org.beansugar.oauth.o20;


import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.beansugar.oauth.client.core.exception.OAuthAuthException;
import org.beansugar.oauth.client.o20.client.OAuth20Client;
import org.beansugar.oauth.client.o20.model.Token20;
import org.beansugar.oauth.client.core.model.State;
import org.beansugar.oauth.client.core.nobi.DefaultStateNobi;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author archmagece
 * @since 2015-11-18
 */
public class OAuth2ExampleHelper {
	static void test(OAuth20Client oAuth20Service, final String SERVICE_NAME, final String PROTECTED_RESOURCE_URL) {
		//TODO state 값이 client 안으로 들어갈 필요 있음
		State state0 = new DefaultStateNobi(SERVICE_NAME).getState();
		Scanner scanner = new Scanner(System.in);

		System.out.println("=== " + SERVICE_NAME + "  ===");
		System.out.println();

		//authorize url
		System.out.println("Authorize URL 생성s");
		String authorizationUrl = oAuth20Service.getAuthorizeUrl(state0);
		System.out.println("url : " + authorizationUrl);
		System.out.println("Authorize URL 생성e");

		System.out.println();
		System.out.println();

		System.out.println(SERVICE_NAME + "로부터 받은 code 입력");
		System.out.println("verifier >>");
		String verifier = scanner.nextLine();

		System.out.println(SERVICE_NAME + "로부터 받은 state 입력");
		System.out.println("state >>");
		String stateReturn = scanner.nextLine();
		if (!stateReturn.equals(state0.getValue())) {
			throw new OAuthAuthException("인증실패 - state가 변조되었나 모르겠는데 값이다름");
		}
		if (SERVICE_NAME.equals("GOOGLE")) {
			state0 = null;
		}
		System.out.println();
		System.out.println();

		//access token
		System.out.println("Access Token 호출s");
		Token20 token20 = oAuth20Service.getAccessToken(verifier, state0);
		System.out.println("access token : " + token20);
		System.out.println("Access Token 호출e");
		System.out.println();
		System.out.println();

		//protected resource
		System.out.println("Projected Resource 호출s");
		String response = oAuth20Service.getResource(PROTECTED_RESOURCE_URL, token20.getAccessToken());
		System.out.println("(resource : " + response + " )");
		System.out.println("Projected Resource 호출e");
		System.out.println();
		System.out.println();

		System.out.println("FIN.");
	}

	public void nakedCall(String urlString) {

		CloseableHttpClient httpclient = null;
		try {
			httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(urlString);
			CloseableHttpResponse response = httpclient.execute(httpget);

			HttpEntity entity = response.getEntity();
			InputStream inStream = entity.getContent();
			BufferedInputStream bufInStream = new BufferedInputStream(inStream);
			StringBuffer sb = new StringBuffer();
			byte[] b = new byte[4096];
			for (int n; (n = bufInStream.read(b)) != -1; ) {
				sb.append(new String(b, 0, n));
			}
			System.out.println(sb.toString());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
