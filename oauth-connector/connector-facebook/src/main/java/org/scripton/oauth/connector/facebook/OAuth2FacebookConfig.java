package org.scripton.oauth.connector.facebook;

import lombok.Data;
import org.scriptonbasestar.oauth.client.o20.type.VerifierResponseType;
import org.scriptonbasestar.oauth.client.type.OAuthHttpVerb;
import org.scriptonbasestar.tool.core.check.Check;
import org.scriptonbasestar.tool.core.check.MatchPattern;

@Data
public class OAuth2FacebookConfig {
	//	debug token
	//	graph.facebook.com/debug_token
	private final        String               authorizeUri;
	private final        String               scope;
	private final        VerifierResponseType responseType      = VerifierResponseType.CODE;
	private final        String               tokenUri;
	private final        OAuthHttpVerb        tokenVerb;

	/**
	 *
	 * @param authorizeUri https://www.facebook.com/v3.2/dialog/oauth
	 * @param scope
	 * @param tokenUri https://graph.facebook.com/v3.2/oauth/access_token
	 * @param tokenVerb OAuthHttpVerb.POST
	 */
	public OAuth2FacebookConfig(String authorizeUri, String scope, String tokenUri, OAuthHttpVerb tokenVerb) {
		Check.customPattern(authorizeUri, MatchPattern.url, "authorizeUri must not null or empty, and must full uri string");
		Check.notNull(scope, "scope must not null but empty is allowed");
		Check.customPattern(tokenUri, MatchPattern.url, "tokenUri must not null or empty, and must full uri string");
		Check.notNull(tokenVerb, "tokenVerb must not null");
		this.authorizeUri = authorizeUri;
		this.scope = scope;
		this.tokenUri = tokenUri;
		this.tokenVerb = tokenVerb;
	}
}
