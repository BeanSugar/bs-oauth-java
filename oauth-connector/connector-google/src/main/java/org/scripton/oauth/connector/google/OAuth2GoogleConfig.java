package org.scripton.oauth.connector.google;

import lombok.Data;
import org.scriptonbasestar.oauth.client.o20.type.VerifierResponseType;
import org.scriptonbasestar.oauth.client.type.OAuthHttpVerb;
import org.scriptonbasestar.tool.core.check.Check;

import javax.validation.constraints.NotNull;

@Data
public class OAuth2GoogleConfig {
	private static final String DEFAULT_AUTHORIZE = "https://accounts.google.com/o/oauth2/v2/auth";
	private static final String DEFAULT_TOKEN = "https://www.googleapis.com/oauth2/v4/token";
	private static final String DEFAULT_REVOKE = "https://accounts.google.com/o/oauth2/revoke";

	private final String authorizeUri;
	@NotNull
	private final VerifierResponseType responseType = VerifierResponseType.CODE;
	private final String tokenUri;
	private final OAuthHttpVerb tokenVerb;
	private final String revokeUri;

	public OAuth2GoogleConfig() {
		this(DEFAULT_AUTHORIZE, DEFAULT_TOKEN, OAuthHttpVerb.POST, DEFAULT_REVOKE);
	}

	public OAuth2GoogleConfig(String authorizeUri, String tokenUri, OAuthHttpVerb tokenVerb, String revokeUri) {
		Check.urlDomainPattern(authorizeUri, "authorizeUri must not null or empty, and must full uri string");
		Check.urlDomainPattern(tokenUri, "tokenUri must not null or empty, and must full uri string");
		Check.notNull(tokenVerb, "tokenVerb must not null");
		this.authorizeUri = authorizeUri;
		this.tokenUri = tokenUri;
		this.tokenVerb = tokenVerb;
		this.revokeUri = revokeUri;
	}
}

