package com.books.peanut.member.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

public class NaverLoginBO {
	
    private final static String CLIENT_ID = "whtrMYSRx1IUM5BHnpiT";
    private final static String CLIENT_SECRET = "epJastwAhg";
    private final static String REDIRECT_URI = "http://127.0.0.1:7777/callbackNaver.do"; // 네이버 로그인 후 이동할 url
    private final static String SESSION_STATE = "oauth_state"; // 애플리케이션이 생성한 상태 토큰
    private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me"; // 프로필 조회 api url
    
    // 네아로 인증 url 생성
    public String getAuthorizationUrl(HttpSession session) {

        // 세션 유효성 검증을 위하여 난수를 생성
        String state = generateRandomString();
        // 생성한 난수 값을 session에 저장
        setSession(session, state);        

        // scribe에서 제공하는 인증 url 생성 기능을 이용하여 네아로 인증 url 생성
        OAuth20Service oauthService = new ServiceBuilder()                                                   
                .apiKey(CLIENT_ID)
                .apiSecret(CLIENT_SECRET)
                .callback(REDIRECT_URI)
                .state(state) // 앞서 생성한 난수 값을 인증 url 생성 시 사용함
                .build(NaverOAuthApi.instance());

        return oauthService.getAuthorizationUrl();
    }

    // 네아로 인증 후 callback 처리 및  access token 발급
    public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws IOException{

        // callback으로 전달받은 세션 검증용 난수 값과 세션에 저장되어있는 값이 일치하는지 확인
        String sessionState = getSession(session);
        
        if(StringUtils.pathEquals(sessionState, state)){
            OAuth20Service oauthService = new ServiceBuilder()
                    .apiKey(CLIENT_ID)
                    .apiSecret(CLIENT_SECRET)
                    .callback(REDIRECT_URI)
                    .state(state)
                    .build(NaverOAuthApi.instance());

            // scribe에서 제공하는 access token 발급 기능으로 네아로 access token 발급
            OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
            return accessToken;
        }
        return null;
    }

    // 세션 유효성 검증을 위한 난수 생성기
    private String generateRandomString() {
        return UUID.randomUUID().toString();
    }

    // http session에 데이터 저장
    private void setSession(HttpSession session,String state){
        session.setAttribute(SESSION_STATE, state);     
    }

    // http session에서 데이터 가져오기 
    private String getSession(HttpSession session){
        return (String) session.getAttribute(SESSION_STATE);
    }
    
    // access token을 이용하여 네이버 사용자 프로필 api를 호출
    public String getUserProfile(OAuth2AccessToken oauthToken) throws IOException{

        OAuth20Service oauthService = new ServiceBuilder()
                .apiKey(CLIENT_ID)
                .apiSecret(CLIENT_SECRET)
                .callback(REDIRECT_URI).build(NaverOAuthApi.instance());

        OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);
        oauthService.signRequest(oauthToken, request);
        Response response = request.send();
        return response.getBody();
    }
}
