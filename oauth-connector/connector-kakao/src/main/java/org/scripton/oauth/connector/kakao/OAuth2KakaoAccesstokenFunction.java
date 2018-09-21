package org.scripton.oauth.connector.kakao;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BufferedHeader;
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

import java.util.ArrayList;
import java.util.List;

public class OAuth2KakaoAccesstokenFunction implements OAuth2AccessTokenFunction<OAuth2KakaoTokenRes> {

	private final OAuth2KakaoConfig serviceConfig;
	private final OAuthPersonalConfig personalConfig;
	private final TokenExtractor<OAuth2KakaoTokenRes> tokenExtractor;
	private final TokenStorage tokenStorage;
	private final String redirectUri;

	public OAuth2KakaoAccesstokenFunction(OAuth2KakaoConfig serviceConfig,
										  OAuthPersonalConfig personalConfig,
										  TokenExtractor<OAuth2KakaoTokenRes> tokenExtractor,
										  TokenStorage tokenStorage,
										  String redirectUri
	) {
		this.serviceConfig = serviceConfig;
		this.personalConfig = personalConfig;
		this.tokenExtractor = tokenExtractor;
		this.tokenStorage = tokenStorage;
		this.redirectUri = redirectUri;
	}

	/**
	 * grant_type string Y "authorization_code"
	 * client_id string Y
	 * client_secret string YN 카카오는 기형아라 기본으로 빠져 있지만 최근에 수정을 했는지
	 * 추가기능이추가됨추가를눌렀으면추가해야됨
	 * <p>
	 * redirect_uri string Y 좀전에썼던값
	 * <p>
	 * code string Y
	 * state???없네
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
		paramList.add(OAuth20Constants.REDIRECT_URI, redirectUri);
//		paramList.add(OAuth20Constants.CLIENT_SECRET, personalConfig.getClientSecret());

		paramList.add(OAuth20Constants.CODE, verifier);
//		paramList.add(OAuth20Constants.STATE, state);

		HttpRequest request = HttpRequest.create(serviceConfig.getTokenUri(), paramList);

		return tokenExtractor.extract(request.run(serviceConfig.getTokenVerb()));
	}

	/**
	 * grant_type string Y
	 * client_id string Y
	 * client_secret string YN 맘대로. 위에있음. 맘대로 막하면안되고
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
	public OAuth2KakaoTokenRes revoke(Token accessToken) {
		List<Header> headers = new ArrayList<>();
		headers.add(new BasicHeader("Authorization", "Bearer "+accessToken.getValue()));
		HttpRequest request = HttpRequest.create(serviceConfig.getTokenUri(), headers);

		return tokenExtractor.extract(request.run(serviceConfig.getTokenVerb()));
	}

}
