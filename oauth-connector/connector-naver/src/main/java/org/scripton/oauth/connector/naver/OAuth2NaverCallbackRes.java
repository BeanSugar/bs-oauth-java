package org.scripton.oauth.connector.naver;

import lombok.Data;

@Data
public class OAuth2NaverCallbackRes {
	private String code;
	private String state;
	private String error;
	private String errorDescription;
}
