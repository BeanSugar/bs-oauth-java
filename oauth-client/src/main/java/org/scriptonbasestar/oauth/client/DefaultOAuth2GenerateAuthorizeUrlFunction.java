package org.scriptonbasestar.oauth.client;

import org.scriptonbasestar.oauth.client.http.ParamList;
import org.scriptonbasestar.oauth.client.http.ParamUtil;
import org.scriptonbasestar.oauth.client.model.State;
import org.scriptonbasestar.oauth.client.o20.type.VerifierResponseType;
import org.scriptonbasestar.tool.core.check.Check;

public class DefaultOAuth2GenerateAuthorizeUrlFunction implements OAuth2GenerateAuthorizeUrlFunction {

	private final String authorizeUri;
	private final String callbackUri;
	private final String apiKey;
	private final VerifierResponseType responseType;
	private final String scope;

	public DefaultOAuth2GenerateAuthorizeUrlFunction(String authorizeUri, String callbackUri, String apiKey,
													 VerifierResponseType responseType,
													 String scope) {
		Check.notNullOrEmptyString(authorizeUri, "authorizeUri must not null or empty");
		Check.notNullOrEmptyString(callbackUri, "callbackUri must not null or empty");
		Check.urlCustomPattern(callbackUri, "callbackUrl must not null. Check oauth service OOB support.");
		Check.notNullOrEmptyString(apiKey, "apiKey must not null or empty");
		Check.notNull(responseType, "responseType must not null or empty");
		Check.notNullOrEmptyString(apiKey, "apiKey must not null or empty");

		this.authorizeUri = authorizeUri;
		this.callbackUri = callbackUri;
		this.apiKey = apiKey;
		this.responseType = responseType;
		this.scope = scope;
	}

	@Override
	public String generate(State state) {
		Check.notNull(state, "state must not null");
		return ParamUtil.generateOAuthQuery(authorizeUri, ParamList.create()
			.add(OAuth20Constants.CLIENT_ID, apiKey)
			.add(OAuth20Constants.REDIRECT_URI, callbackUri)
			.add(OAuth20Constants.RESPONSE_TYPE, responseType)
			.add(OAuth20Constants.SCOPE, scope)
			.add(OAuth20Constants.STATE, state));
	}
}
