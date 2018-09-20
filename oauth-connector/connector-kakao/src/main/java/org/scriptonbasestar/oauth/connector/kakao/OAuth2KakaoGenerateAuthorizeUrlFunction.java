package org.scriptonbasestar.oauth.connector.kakao;

import org.scriptonbasestar.oauth.client.OAuth20Constants;
import org.scriptonbasestar.oauth.client.OAuth2GenerateAuthorizeUrlFunction;
import org.scriptonbasestar.oauth.client.config.OAuthPersonalConfig;
import org.scriptonbasestar.oauth.client.http.ParamList;
import org.scriptonbasestar.oauth.client.http.ParamUtil;
import org.scriptonbasestar.oauth.client.model.State;
import org.scriptonbasestar.tool.core.check.Check;

public class OAuth2KakaoGenerateAuthorizeUrlFunction implements OAuth2GenerateAuthorizeUrlFunction {

	private final OAuth2KakaoConfig serviceConfig;
	private final OAuthPersonalConfig personalConfig;
	private final String redirectUri;

	public OAuth2KakaoGenerateAuthorizeUrlFunction(OAuth2KakaoConfig serviceConfig,
												   OAuthPersonalConfig personalConfig,
												   String redirectUri) {
		this.serviceConfig = serviceConfig;
		this.personalConfig = personalConfig;
		this.redirectUri = redirectUri;
	}

	@Override
	public String generate(State state) {
		Check.notNull(state, "state must not null");

		return ParamUtil.generateOAuthQuery(serviceConfig.getAuthorizeUri(), ParamList.create()
			.add(OAuth20Constants.CLIENT_ID, personalConfig.getClientId())
			.add(OAuth20Constants.REDIRECT_URI, redirectUri)
			.add(OAuth20Constants.RESPONSE_TYPE, serviceConfig.getResponseType())
//			.add(OAuth20Constants.SCOPE, )
			.add(OAuth20Constants.STATE, state));
	}
}
