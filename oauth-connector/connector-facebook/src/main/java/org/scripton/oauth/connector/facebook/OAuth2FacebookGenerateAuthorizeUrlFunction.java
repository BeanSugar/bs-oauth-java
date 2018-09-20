package org.scripton.oauth.connector.facebook;

import org.scriptonbasestar.oauth.client.OAuth20Constants;
import org.scriptonbasestar.oauth.client.OAuth2GenerateAuthorizeUrlFunction;
import org.scriptonbasestar.oauth.client.config.OAuthPersonalConfig;
import org.scriptonbasestar.oauth.client.http.ParamList;
import org.scriptonbasestar.oauth.client.http.ParamUtil;
import org.scriptonbasestar.oauth.client.model.State;
import org.scriptonbasestar.tool.core.check.Check;

public class OAuth2FacebookGenerateAuthorizeUrlFunction implements OAuth2GenerateAuthorizeUrlFunction {

	private final OAuth2FacebookConfig serviceConfig;
	private final OAuthPersonalConfig personalConfig;
	private final String redirectUri;

	public OAuth2FacebookGenerateAuthorizeUrlFunction(OAuth2FacebookConfig serviceConfig,
													  OAuthPersonalConfig personalConfig,
													  String redirectUri) {
		this.serviceConfig = serviceConfig;
		this.personalConfig = personalConfig;
		this.redirectUri = redirectUri;
	}

	/**
	 * client_id string Y
	 * redirect_uri string Y
	 * state string Y
	 * <p>
	 * -- optional
	 * response_type code/token/code%20token/
	 * gradnted_scopes
	 * scope string N
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
//			.add(OAuth20Constants.SCOPE, )
			.add(OAuth20Constants.STATE, state));
	}
}
