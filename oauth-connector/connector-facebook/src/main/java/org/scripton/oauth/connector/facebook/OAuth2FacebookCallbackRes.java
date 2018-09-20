package org.scripton.oauth.connector.facebook;

import lombok.Data;

@Data
public class OAuth2FacebookCallbackRes {
	private String code;
	private String state;
	private String error;
	private String errorDescription;
}
