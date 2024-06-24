package codesquad.airdnb.domain.member;


import codesquad.airdnb.domain.member.dto.request.LoginRequest;
import codesquad.airdnb.domain.member.dto.request.SignUpRequest;
import codesquad.airdnb.domain.member.dto.response.AuthResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static codesquad.airdnb.global.security.JwtTokenProvider.ACCESS_TOKEN_EXP_TIME;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody SignUpRequest request) {
        AuthResponse authResponse = memberService.register(request, LoginType.DEFAULT);
        HttpHeaders headers = createAuthResponseHeader(authResponse.accessToken());

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse authResponse = memberService.login(request);
        HttpHeaders headers = createAuthResponseHeader(authResponse.accessToken());

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(authResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader(value = "Authorization", required = false) String token) {
        String accessToken = token.replace("Bearer ", "");
        memberService.logout(accessToken);
        HttpHeaders headers = createAccessTokenExpiredHeader();

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String,String>> refresh(@CookieValue(value = "refreshToken", required = false) String refreshToken) {
        Auth auth = memberService.refresh(refreshToken);
        HttpHeaders headers = createAuthResponseHeader(auth.accessToken());

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(Collections.singletonMap("accessToken", auth.accessToken()));
    }

    @GetMapping("/login/oauth/kakao")
    public ResponseEntity<Void> kakaoLoginRedirection(HttpServletResponse response) throws IOException {
        String uri = UriComponentsBuilder
                .fromUriString("https://kauth.kakao.com/oauth/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", "d10ab92222e4d647141f3f236e610ec9")
                .queryParam("redirect_uri", "http://localhost:5173/oauth/redirected/kakao")
                .queryParam("scope", "profile_nickname,profile_image,account_email")
                .toUriString();
        response.sendRedirect(uri);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login/oauth/authenticate")
    public ResponseEntity<AuthResponse> authenticateOauth(@RequestParam("code") String authCode) {
        AuthResponse authResponse = memberService.authenticateByKakao(authCode);
        HttpHeaders headers = createAuthResponseHeader(authResponse.accessToken());

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(authResponse);
    }

    private HttpHeaders createAuthResponseHeader(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE,
                String.format(
                        "%s=%s; Path=%s; Max-Age=%d; HttpOnly; Secure; SameSite=None",
                        "accessToken", accessToken, "/", ACCESS_TOKEN_EXP_TIME / 1000)
        );

        return headers;
    }

    private HttpHeaders createAccessTokenExpiredHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE,
                String.format(
                        "%s=%s; Path=%s; Max-Age=%d; HttpOnly; Secure; SameSite=None",
                        "accessToken", null, "/", 0)
        );

        return headers;
    }
}