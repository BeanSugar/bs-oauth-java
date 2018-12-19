package org.scripton.oauth.connector.facebook;

import org.scriptonbasestar.oauth.client.OAuth20Constants;
import org.scriptonbasestar.oauth.client.OAuth2GenerateAuthorizeEndpointFunction;
import org.scriptonbasestar.oauth.client.http.ParamList;
import org.scriptonbasestar.oauth.client.http.ParamUtil;
import org.scriptonbasestar.oauth.client.model.State;
import org.scriptonbasestar.tool.core.check.Check;

public class OAuth2FacebookGenerateAuthorizeEndpointFunction
	implements OAuth2GenerateAuthorizeEndpointFunction {

	private final OAuth2FacebookConfig config;

	public OAuth2FacebookGenerateAuthorizeEndpointFunction(OAuth2FacebookConfig config) {
		this.config = config;
	}

	/**
	 * client_id string Y redirect_uri string Y state string Y
	 *
	 * <p>-- optional response_type code/token/code%20token/ gradnted_scopes scope string N
	 *
	 * @param state
	 * @return
	 */
	@Override
	public String generate(State state) {
		Check.notNull(state, "state must not null");

		return ParamUtil.generateOAuthQuery(
			config.getAuthorizeEndpoint(),
			ParamList.create()
					 .add(OAuth20Constants.CLIENT_ID, config.getClientId())
					 .add(OAuth20Constants.REDIRECT_URI, config.getRedirectUri())
					 .add(OAuth20Constants.RESPONSE_TYPE, config.getResponseType())
					 .add(OAuth20Constants.SCOPE, config.getScope())
					 .add(OAuth20Constants.STATE, state));
	}
}
