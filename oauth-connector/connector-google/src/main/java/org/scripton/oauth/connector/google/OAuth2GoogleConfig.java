package org.scripton.oauth.connector.google;

import lombok.Data;
import org.scriptonbasestar.oauth.client.o20.type.VerifierResponseType;
import org.scriptonbasestar.oauth.client.type.OAuthHttpVerb;
import org.scriptonbasestar.tool.core.check.Check;
import org.scriptonbasestar.tool.core.check.MatchPattern;

@Data
public class OAuth2GoogleConfig {
	private final String               authorizeUri;
	private final String               scope;
	private final VerifierResponseType responseType = VerifierResponseType.CODE;
	private final String               tokenUri;
	private final OAuthHttpVerb        tokenVerb;
	private final String               revokeUri;

	/**
	 * @param authorizeUri https://accounts.google.com/o/oauth2/v2/auth
	 * @param scope        https://www.googleapis.com/auth/userinfo.profile
	 * @param tokenUri     https://www.googleapis.com/oauth2/v4/token
	 * @param tokenVerb    OAuthHttpVerb.POST
	 * @param revokeUri    https://accounts.google.com/o/oauth2/revoke
	 */
	public OAuth2GoogleConfig(String authorizeUri, String scope, String tokenUri, OAuthHttpVerb tokenVerb, String revokeUri) {
		Check.customPattern(authorizeUri, MatchPattern.url, "authorizeUri must not null or empty, and must full uri string");
		Check.notNull(scope, "scope must not null but empty is allowed");
		Check.customPattern(tokenUri, MatchPattern.url, "authorizeUri must not null or empty, and must full uri string");
		Check.notNull(tokenVerb, "tokenVerb must not null");
		this.authorizeUri = authorizeUri;
		this.scope = scope;
		this.tokenUri = tokenUri;
		this.tokenVerb = tokenVerb;
		this.revokeUri = revokeUri;
	}
}

