package org.scriptonbasestar.oauth.connector.kakao;

import org.scriptonbasestar.oauth.client.OAuth20Constants;
import org.scriptonbasestar.oauth.client.OAuth2AccessTokenFunction;
import org.scriptonbasestar.oauth.client.config.OAuthPersonalConfig;
import org.scriptonbasestar.oauth.client.http.HttpRequest;
import org.scriptonbasestar.oauth.client.http.ParamList;
import org.scriptonbasestar.oauth.client.model.State;
import org.scriptonbasestar.oauth.client.model.Token;
import org.scriptonbasestar.oauth.client.model.Verifier;
import org.scriptonbasestar.oauth.client.nobi.TokenStorage;
import org.scriptonbasestar.oauth.client.nobi.token.TokenExtractor;
import org.scriptonbasestar.oauth.client.type.GrantType;
import org.scriptonbasestar.tool.core.check.Check;

public class OAuth2KakaoAccesstokenFunction implements OAuth2AccessTokenFunction<OAuth2KakaoTokenRes> {

	private final OAuth2KakaoConfig serviceConfig;
	private final OAuthPersonalConfig personalConfig;
	private final TokenExtractor<OAuth2KakaoTokenRes> tokenExtractor;
	private final TokenStorage tokenStorage;

	public OAuth2KakaoAccesstokenFunction(OAuth2KakaoConfig serviceConfig,
										  OAuthPersonalConfig personalConfig,
										  TokenExtractor<OAuth2KakaoTokenRes> tokenExtractor,
										  TokenStorage tokenStorage
	) {
		this.serviceConfig = serviceConfig;
		this.personalConfig = personalConfig;
		this.tokenExtractor = tokenExtractor;
		this.tokenStorage = tokenStorage;
	}

	/**
	 * grant_type string Y
	 * client_id string Y
	 * client_secret string Y
	 * <p>
	 * code string Y
	 * state string Y
	 *
	 * @param verifier
	 * @param state
	 * @return
	 */
	@Override
	public OAuth2KakaoTokenRes issue(Verifier verifier, State state) {
		Check.notNull(verifier, "verifier must not null");
		Check.notNull(state, "state must not null");

		ParamList paramList = new ParamList();

		paramList.add(OAuth20Constants.GRANT_TYPE, GrantType.AUTHORIZATION_CODE);
		paramList.add(OAuth20Constants.CLIENT_ID, personalConfig.getClientId());
		paramList.add(OAuth20Constants.CLIENT_SECRET, personalConfig.getClientSecret());

		paramList.add(OAuth20Constants.CODE, verifier);
		paramList.add(OAuth20Constants.STATE, state);
//		paramList.add(OAuth20Constants.REDIRECT_URI, serviceConfig.getAuthorizeUri());

		HttpRequest request = HttpRequest.create(serviceConfig.getTokenUri(), paramList);

		return tokenExtractor.extract(request.run(serviceConfig.getTokenVerb()));
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
	public OAuth2KakaoTokenRes refresh(Token refreshToken) {
		ParamList paramList = new ParamList();

		paramList.add(OAuth20Constants.GRANT_TYPE, GrantType.REFRESH_TOKEN);
		paramList.add(OAuth20Constants.CLIENT_ID, personalConfig.getClientId());
		paramList.add(OAuth20Constants.CLIENT_SECRET, personalConfig.getClientSecret());

		paramList.add(OAuth20Constants.REFRESH_TOKEN, refreshToken);

		HttpRequest request = HttpRequest.create(serviceConfig.getTokenUri(), paramList);

		return tokenExtractor.extract(request.run(serviceConfig.getTokenVerb()));
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
	public OAuth2KakaoTokenRes delete(Token accessToken) {
		ParamList paramList = new ParamList();

		paramList.add(OAuth20Constants.GRANT_TYPE, GrantType.REFRESH_TOKEN);
		paramList.add(OAuth20Constants.CLIENT_ID, personalConfig.getClientId());
		paramList.add(OAuth20Constants.CLIENT_SECRET, personalConfig.getClientSecret());

		paramList.add(OAuth20Constants.ACCESS_TOKEN, accessToken);
		paramList.add("service_provider", "NAVER");

		HttpRequest request = HttpRequest.create(serviceConfig.getTokenUri(), paramList);

		return tokenExtractor.extract(request.run(serviceConfig.getTokenVerb()));
	}

	@Override
	public OAuth2KakaoTokenRes bearer() {
		ParamList paramList = new ParamList();

		paramList.add(OAuth20Constants.GRANT_TYPE, GrantType.AUTHORIZATION_CODE);
		paramList.add(OAuth20Constants.CLIENT_ID, personalConfig.getClientId());
		paramList.add(OAuth20Constants.CLIENT_SECRET, personalConfig.getClientSecret());

		paramList.add(OAuth20Constants.GRANT_TYPE, GrantType.CLIENT_CREDENTIALS);

		HttpRequest request = HttpRequest.create(serviceConfig.getTokenUri(), paramList);

		return tokenExtractor.extract(request.run(serviceConfig.getTokenVerb()));
	}

}
