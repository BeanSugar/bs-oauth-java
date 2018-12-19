package org.scripton.oauth.connector.google;

import org.scriptonbasestar.oauth.client.OAuth20Constants;
import org.scriptonbasestar.oauth.client.OAuth2GenerateAuthorizeUrlFunction;
import org.scriptonbasestar.oauth.client.config.OAuthPersonalConfig;
import org.scriptonbasestar.oauth.client.http.ParamList;
import org.scriptonbasestar.oauth.client.http.ParamUtil;
import org.scriptonbasestar.oauth.client.model.State;
import org.scriptonbasestar.tool.core.check.Check;

public class OAuth2GoogleGenerateAuthorizeUrlFunction implements OAuth2GenerateAuthorizeUrlFunction {

	private final OAuth2GoogleConfig serviceConfig;
	private final OAuthPersonalConfig personalConfig;
	private final String redirectUri;

	public OAuth2GoogleGenerateAuthorizeUrlFunction(OAuth2GoogleConfig serviceConfig,
													OAuthPersonalConfig personalConfig,
													String redirectUri) {
		this.serviceConfig = serviceConfig;
		this.personalConfig = personalConfig;
		this.redirectUri = redirectUri;
	}

	/**
	 * client_id string Y
	 * redirect_uri string Y
	 * scope string Y
	 * access_type string Recommended online/offline
	 * state string Recommended
	 * include_granted_scopes string N true/false
	 * login_hint string N 유저 정보를 알고있는경우 email/id 등
	 * prompt string N none/consent/select_account
	 *
	 * @param state
	 * @return
	 */
	@Override
	public String generate(State state) {
		Check.notNull(state, "state must not null");

		return ParamUtil.generateOAuthQuery(serviceConfig.getAuthorizeUri(), ParamList.create()
			.add(OAuth20Constants.CLIENT_ID, personalConfig.getClientId())
			.add(OAuth20Constants.REDIRECT_URI, redirectUri)
			.add(OAuth20Constants.RESPONSE_TYPE, serviceConfig.getResponseType())
			.add(OAuth20Constants.SCOPE, serviceConfig.getScope())
			.add(OAuth20Constants.STATE, state));
	}
}
