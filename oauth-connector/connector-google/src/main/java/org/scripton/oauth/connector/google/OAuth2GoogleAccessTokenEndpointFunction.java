package org.scripton.oauth.connector.google;

import org.scriptonbasestar.oauth.client.OAuth20Constants;
import org.scriptonbasestar.oauth.client.OAuth2AccessTokenEndpointFunction;
import org.scriptonbasestar.oauth.client.http.HttpRequest;
import org.scriptonbasestar.oauth.client.http.ParamList;
import org.scriptonbasestar.oauth.client.model.State;
import org.scriptonbasestar.oauth.client.model.Token;
import org.scriptonbasestar.oauth.client.model.Verifier;
import org.scriptonbasestar.oauth.client.nobi.TokenStorage;
import org.scriptonbasestar.oauth.client.nobi.token.TokenExtractor;
import org.scriptonbasestar.oauth.client.type.GrantType;
import org.scriptonbasestar.oauth.client.type.OAuthHttpVerb;
import org.scriptonbasestar.tool.core.check.Check;

public class OAuth2GoogleAccessTokenEndpointFunction
		implements OAuth2AccessTokenEndpointFunction<OAuth2GoogleTokenRes> {

	private final OAuth2GoogleConfig serviceConfig;
	private final TokenExtractor<OAuth2GoogleTokenRes> tokenExtractor;
	private final TokenStorage tokenStorage;
	private final String redirectUri;

	public OAuth2GoogleAccessTokenEndpointFunction(OAuth2GoogleConfig serviceConfig,
												   TokenExtractor<OAuth2GoogleTokenRes> tokenExtractor,
												   TokenStorage tokenStorage,
												   String redirectUri) {
		this.serviceConfig = serviceConfig;
		this.tokenExtractor = tokenExtractor;
		this.tokenStorage = tokenStorage;
		this.redirectUri = redirectUri;
	}

	/**
	 * grant_type string Y
	 * client_id string Y
	 * client_secret string Y
	 * <p>
	 * code string Y
	 * redirect_uri string Y
	 *
	 * @param verifier
	 * @param state
	 * @return
	 */
	@Override
	public OAuth2GoogleTokenRes issue(Verifier verifier, State state) {
		Check.notNull(verifier, "verifier must not null");
		Check.notNull(state, "state must not null");

		ParamList paramList = new ParamList();

		paramList.add(OAuth20Constants.GRANT_TYPE, GrantType.AUTHORIZATION_CODE);
		paramList.add(OAuth20Constants.CLIENT_ID, serviceConfig.getClientId());
		paramList.add(OAuth20Constants.CLIENT_SECRET, serviceConfig.getClientSecret());

		paramList.add(OAuth20Constants.CODE, verifier);
//		paramList.add(OAuth20Constants.STATE, state);
		paramList.add(OAuth20Constants.REDIRECT_URI, redirectUri);

		HttpRequest request = HttpRequest.create(serviceConfig.getAccessTokenEndpoint(), paramList);

		return tokenExtractor.extract(request.run(serviceConfig.getAccessTokenVerb()));
	}

	/**
	 * grant_type string Y
	 * client_id string Y
	 * client_secret string Y
	 * <p>
	 * refresh_token string Y
	 *
	 * @param refreshToken
	 * @return
	 */
	@Override
	public OAuth2GoogleTokenRes refresh(Token refreshToken) {
		ParamList paramList = new ParamList();

		paramList.add(OAuth20Constants.GRANT_TYPE, GrantType.REFRESH_TOKEN);
		paramList.add(OAuth20Constants.CLIENT_ID, serviceConfig.getClientId());
		paramList.add(OAuth20Constants.CLIENT_SECRET, serviceConfig.getClientSecret());

		paramList.add(OAuth20Constants.REFRESH_TOKEN, refreshToken);

		HttpRequest request = HttpRequest.create(serviceConfig.getAccessTokenEndpoint(), paramList);

		return tokenExtractor.extract(request.run(serviceConfig.getAccessTokenVerb()));
	}

	/**
	 * grant_type string Y
	 * client_id string Y
	 * client_secret string Y
	 * <p>
	 * access_token string Y
	 *
	 * @param accessToken
	 * @return
	 */
	@Override
	public OAuth2GoogleTokenRes revoke(Token accessToken) {
		ParamList paramList = new ParamList();

		paramList.add(OAuth20Constants.ACCESS_TOKEN, accessToken);

		HttpRequest request = HttpRequest.create(serviceConfig.getRevokeUrl(), paramList);

		return tokenExtractor.extract(request.run(OAuthHttpVerb.GET));
	}

}
