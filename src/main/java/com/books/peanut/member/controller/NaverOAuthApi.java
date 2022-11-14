package com.books.peanut.member.controller;

import com.github.scribejava.core.builder.api.DefaultApi20;

public class NaverOAuthApi extends DefaultApi20 {

    protected NaverOAuthApi(){
    }

    private static class InstanceHolder{
        private static final NaverOAuthApi INSTANCE = new NaverOAuthApi();
    }

    public static NaverOAuthApi instance(){
        return InstanceHolder.INSTANCE;
    }
	
	@Override
	public String getAccessTokenEndpoint() {
		return "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		return "https://nid.naver.com/oauth2.0/authorize";
	}

}
