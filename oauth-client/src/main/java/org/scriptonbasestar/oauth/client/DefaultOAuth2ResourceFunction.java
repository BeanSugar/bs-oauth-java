package org.scriptonbasestar.oauth.client;

import com.google.gson.JsonParseException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class DefaultOAuth2ResourceFunction implements OAuth2ResourceFunction<String> {

	private final String resourceUri;

	public DefaultOAuth2ResourceFunction(String resourceUri) {
		this.resourceUri = resourceUri;
	}

	@Override
	public String run(String accessToken) {
		CloseableHttpClient httpclient1 = HttpClients.createDefault();
		try {
			HttpGet httpget1 = new HttpGet(resourceUri);
			httpget1.addHeader("Authorization", "Bearer " + accessToken);
			System.out.println("Executing request " + httpget1.getRequestLine());
			ResponseHandler<String> responseHandler1 = new ResponseHandler<String>() {
				@Override
				public String handleResponse(
					final HttpResponse response) throws IOException {
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
}
