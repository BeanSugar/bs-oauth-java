package org.scripton.oauth.connector.google;

import lombok.Data;

@Data
public class OAuth2GoogleCallbackRes {
	private String code;
	private String state;
	private String error;
	private String errorDescription;
}
