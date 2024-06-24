package codesquad.airdnb.domain.member.oauth;

import org.springframework.http.ResponseEntity;

public interface OAuthClient {
    OAuthProvider oAuthProvider();

    OAuthToken requestAccessToken(OAuthLoginParams params);

    // 이미 발급받은 accessToken으로 사용자의 정보를 요청
    OAuthUserInfo requestOAuthInfo(String accessToken);

    // 사용자가 로그인 시 사용자의 정보, accessToken, RefreshToken을 요청
    OAuthUserInfoWithToken requestOAuthInfoWithToken(OAuthLoginParams params);

    ResponseEntity<String> requestExpireOAuthTokens(String oauthAccessToken);
}
