package org.scripton.oauth.connector.kakao;

import lombok.Data;
import org.scriptonbasestar.oauth.client.o20.type.VerifierResponseType;
import org.scriptonbasestar.oauth.client.type.OAuthHttpVerb;
import org.scriptonbasestar.tool.core.check.Check;
import org.scriptonbasestar.tool.core.check.MatchPattern;

/**
 * @author chaeeung.e
 * @since 2017-09-12
 */
@Data
public class OAuth2KakaoConfig {
	private static final String DEFAULT_AUTHORIZE = "https://kauth.kakao.com/oauth/authorize";
	private static final String DEFAULT_TOKEN     = "https://kauth.kakao.com/oauth/token";
	private static final String DEFAULT_REVOKE    = "https://kapi.kakao.com/v1/user/logout";

	private final String               authorizeUri;
	private final String               scope;
	private final VerifierResponseType responseType = VerifierResponseType.CODE;
	private final String               tokenUri;
	private final OAuthHttpVerb        tokenVerb;

	public OAuth2KakaoConfig() {
		this(DEFAULT_AUTHORIZE, "", DEFAULT_TOKEN, OAuthHttpVerb.POST);
	}

	public OAuth2KakaoConfig(String authorizeUri, String scope, String tokenUri, OAuthHttpVerb tokenVerb) {
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
