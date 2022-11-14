package com.books.peanut.member.controller;

import com.github.scribejava.core.builder.api.DefaultApi20;

public class KakaoOAuthApi extends DefaultApi20 {
	protected KakaoOAuthApi() {
	}

	private static class InstanceHolder {
		private static final KakaoOAuthApi INSTANCE = new KakaoOAuthApi();
	}

	public static KakaoOAuthApi instance() {
		return InstanceHolder.INSTANCE;
	}
	
	@Override
	public String getAccessTokenEndpoint() {
		return "https://kauth.kakao.com/oauth/token";
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		return "https://kauth.kakao.com/oauth/authorize?client_id=790165a64700b146767baf6da66e9f06&redirect_uri=http://127.0.0.1:7777/callbackKakao.do&response_type=code";
	}

}
