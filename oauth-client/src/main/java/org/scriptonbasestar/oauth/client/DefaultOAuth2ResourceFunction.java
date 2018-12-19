package org.scriptonbasestar.oauth.client;

import com.google.gson.JsonParseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@Slf4j
public class DefaultOAuth2ResourceFunction
		implements OAuth2ResourceFunction<String> {

	private final String resourceUri;

	public DefaultOAuth2ResourceFunction(String resourceUri) {
		this.resourceUri = resourceUri;
	}

	@Override
	public String run(String accessToken) {
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpGet httpget1 = new HttpGet(resourceUri);
			httpget1.addHeader("Authorization", "Bearer " + accessToken);
			log.debug("Executing request " + httpget1.getRequestLine());
			ResponseHandler<String> responseHandler1 = response -> {
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					return entity != null ? EntityUtils.toString(entity) : null;
				} else {
					throw new ClientProtocolException("Unexpected response status: " + status);
				}
			};
			return httpClient.execute(httpget1, responseHandler1);
		} catch (JsonParseException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
