package org.scripton.oauth.connector.naver;

import org.scriptonbasestar.oauth.client.OAuth20Constants;
import org.scriptonbasestar.oauth.client.OAuth2GenerateAuthorizeEndpointFunction;
import org.scriptonbasestar.oauth.client.http.ParamList;
import org.scriptonbasestar.oauth.client.http.ParamUtil;
import org.scriptonbasestar.oauth.client.model.State;
import org.scriptonbasestar.tool.core.check.Check;

public class OAuth2NaverGenerateAuthorizeEndpointFunction
		implements OAuth2GenerateAuthorizeEndpointFunction {

	private final OAuth2NaverConfig serviceConfig;
	private final String redirectUri;

	public OAuth2NaverGenerateAuthorizeEndpointFunction(OAuth2NaverConfig serviceConfig, String redirectUri) {
		this.serviceConfig = serviceConfig;
		this.redirectUri = redirectUri;
	}

	/**
	 * response_type string Y "code"
	 * client_id string Y
	 * redirect_uri string Y
	 * state string Y
	 * scope string N null
	 *
	 * @param state
	 * @return
	 */
	@Override
	public String generate(State state) {
		Check.notNull(state, "state must not null");

		return ParamUtil.generateOAuthQuery(serviceConfig.getAuthorizeEndpoint(),
											ParamList.create()
													 .add(OAuth20Constants.CLIENT_ID, serviceConfig.getClientId())
													 .add(OAuth20Constants.REDIRECT_URI, redirectUri)
													 .add(OAuth20Constants.RESPONSE_TYPE,
														  serviceConfig.getResponseType())
//			.add(OAuth20Constants.SCOPE, )
													 .add(OAuth20Constants.STATE, state));
	}
}
