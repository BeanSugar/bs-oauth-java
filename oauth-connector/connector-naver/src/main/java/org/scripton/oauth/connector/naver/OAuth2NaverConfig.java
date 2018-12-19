package org.scripton.oauth.connector.naver;

import lombok.Getter;
import org.scriptonbasestar.oauth.client.config.OAuthBaseConfig;
import org.scriptonbasestar.oauth.client.o20.type.VerifierResponseType;
import org.scriptonbasestar.oauth.client.type.OAuthHttpVerb;
import org.scriptonbasestar.tool.core.check.Check;
import org.scriptonbasestar.tool.core.check.MatchPattern;

@Getter
public class OAuth2NaverConfig
		extends OAuthBaseConfig {

	private final String authorizeEndpoint;
	private final String scope;
	private final VerifierResponseType responseType = VerifierResponseType.CODE;
	private final String accessTokenUri;
	private final OAuthHttpVerb accessTokenVerb;

	/**
	 * @param authorizeEndpoint
	 * @param scope
	 * @param accessTokenEndpoint
	 * @param accessTokenVerb
	 */
	public OAuth2NaverConfig(String clientId,
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
		this.accessTokenUri = accessTokenEndpoint;
		this.accessTokenVerb = accessTokenVerb;
	}
}
