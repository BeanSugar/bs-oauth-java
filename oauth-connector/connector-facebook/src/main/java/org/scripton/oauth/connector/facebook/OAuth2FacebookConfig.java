package org.scripton.oauth.connector.facebook;

import lombok.Getter;
import org.scriptonbasestar.oauth.client.config.OAuthBaseConfig;
import org.scriptonbasestar.oauth.client.o20.type.VerifierResponseType;
import org.scriptonbasestar.oauth.client.type.OAuthHttpVerb;
import org.scriptonbasestar.tool.core.check.Check;
import org.scriptonbasestar.tool.core.check.MatchPattern;

@Getter
public class OAuth2FacebookConfig
		extends OAuthBaseConfig {

	//	debug token
	//	graph.facebook.com/debug_token
	private final String authorizeEndpoint;
	private final String scope;
	private final VerifierResponseType responseType = VerifierResponseType.CODE;
	private final String accessTokenUrl;
	private final OAuthHttpVerb accessTokenVerb;

	/**
	 * @param authorizeEndpoint https://www.facebook.com/v3.2/dialog/oauth
	 * @param scope
	 * @param accessTokenUrl    https://graph.facebook.com/v3.2/oauth/access_token
	 * @param accessTokenVerb   OAuthHttpVerb.POST
	 */
	public OAuth2FacebookConfig(String clientId,
								String clientSecret,
								String authorizeEndpoint,
								String scope,
								String accessTokenUrl,
								OAuthHttpVerb accessTokenVerb) {
		super(clientId, clientSecret);
		Check.customPattern(authorizeEndpoint,
							MatchPattern.url,
							"authorizeEndpoint must not null or empty, and must full uri string");
		Check.notNull(scope, "scope must not null but empty is allowed");
		Check.customPattern(accessTokenUrl,
							MatchPattern.url,
							"accessTokenEndpoint must not null or empty, and must full uri string");
		Check.notNull(accessTokenVerb, "accessTokenVerb must not null");
		this.authorizeEndpoint = authorizeEndpoint;
		this.scope = scope;
		this.accessTokenUrl = accessTokenUrl;
		this.accessTokenVerb = accessTokenVerb;
	}
}
