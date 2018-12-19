package org.scripton.oauth.connector.kakao;

import org.scriptonbasestar.oauth.client.OAuth20Constants;
import org.scriptonbasestar.oauth.client.OAuth2GenerateAuthorizeEndpointFunction;
import org.scriptonbasestar.oauth.client.http.ParamList;
import org.scriptonbasestar.oauth.client.http.ParamUtil;
import org.scriptonbasestar.oauth.client.model.State;
import org.scriptonbasestar.tool.core.check.Check;

public class OAuth2KakaoGenerateAuthorizeEndpointFunction
		implements OAuth2GenerateAuthorizeEndpointFunction {

	private final OAuth2KakaoConfig serviceConfig;
	private final String redirectUri;

	public OAuth2KakaoGenerateAuthorizeEndpointFunction(OAuth2KakaoConfig serviceConfig, String redirectUri) {
		this.serviceConfig = serviceConfig;
		this.redirectUri = redirectUri;
	}

	/**
	 * client_id string Y
	 * redirect_uri string Y
	 * response_type string Y
	 * state string N
	 * encode_state boolean N "false"
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
													 .add(OAuth20Constants.STATE, state)
													 .add("encode_state", "false"));
	}
}
