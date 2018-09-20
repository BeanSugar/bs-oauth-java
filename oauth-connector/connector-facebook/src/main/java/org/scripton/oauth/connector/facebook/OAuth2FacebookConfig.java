package org.scripton.oauth.connector.facebook;

import lombok.Data;
import org.scriptonbasestar.oauth.client.o20.type.VerifierResponseType;
import org.scriptonbasestar.oauth.client.type.OAuthHttpVerb;
import org.scriptonbasestar.tool.core.check.Check;

import javax.validation.constraints.NotNull;

@Data
public class OAuth2FacebookConfig {
	private static final String DEFAULT_AUTHORIZE = "https://www.facebook.com/v3.1/dialog/oauth";
	private static final String DEFAULT_TOKEN = "https://graph.facebook.com/v3.1/oauth/access_token";
//	debug token
//	graph.facebook.com/debug_token
	private final String authorizeUri;
	@NotNull
	private final VerifierResponseType responseType = VerifierResponseType.CODE;
	private final String tokenUri;
	private final OAuthHttpVerb tokenVerb;

	public OAuth2FacebookConfig() {
		this(DEFAULT_AUTHORIZE, DEFAULT_TOKEN, OAuthHttpVerb.POST);
	}

	public OAuth2FacebookConfig(String authorizeUri, String tokenUri, OAuthHttpVerb tokenVerb) {
		Check.urlDomainPattern(authorizeUri, "authorizeUri must not null or empty, and must full uri string");
		Check.urlDomainPattern(tokenUri, "tokenUri must not null or empty, and must full uri string");
		Check.notNull(tokenVerb, "tokenVerb must not null");
		this.authorizeUri = authorizeUri;
		this.tokenUri = tokenUri;
		this.tokenVerb = tokenVerb;
	}
}

