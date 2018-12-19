package org.scripton.oauth.connector.kakao;

import lombok.Getter;
import org.scriptonbasestar.oauth.client.config.OAuthBaseConfig;
import org.scriptonbasestar.oauth.client.o20.type.VerifierResponseType;
import org.scriptonbasestar.oauth.client.type.OAuthHttpVerb;
import org.scriptonbasestar.tool.core.check.Check;
import org.scriptonbasestar.tool.core.check.MatchPattern;

/**
 * @author archmagece
 * @since 2017-09-12
 */
@Getter
public class OAuth2KakaoConfig
		extends OAuthBaseConfig {

	private final String authorizeEndpoint;
	private final String scope;
	private final VerifierResponseType responseType = VerifierResponseType.CODE;
	private final String accessTokenEndpoint;
	private final OAuthHttpVerb accessTokenVerb;

	/**
	 * @param authorizeEndpoint
	 * @param scope
	 * @param accessTokenEndpoint
	 * @param accessTokenVerb     OAuthHttpVerb.POST
	 */
	public OAuth2KakaoConfig(String clientId,
							 String clientSecret,
							 String authorizeEndpoint,
							 String scope,
							 String accessTokenEndpoint,
							 OAuthHttpVerb accessTokenVerb) {
		super(clientId, clientSecret);
		Check.customPattern(authorizeEndpoint,
							MatchPattern.url,
							"authorizeEndpoint must not null or empty, and must full uri string");
		Check.notNull(scope, "scope must not null but empty is allowed");
		Check.customPattern(accessTokenEndpoint,
							MatchPattern.url,
							"accessTokenEndpoint must not null or empty, and must full uri string");
		Check.notNull(accessTokenVerb, "accessTokenVerb must not null");
		this.authorizeEndpoint = authorizeEndpoint;
		this.scope = scope;
		this.accessTokenEndpoint = accessTokenEndpoint;
		this.accessTokenVerb = accessTokenVerb;
	}
}
