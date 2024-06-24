package codesquad.airdnb.domain.member.oauth;

import lombok.AllArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@AllArgsConstructor
public class KaKaoLoginParam implements OAuthLoginParams{

    private String authorizationCode;

    @Override
    public OAuthProvider oAuthprovider() {
        return OAuthProvider.KAKAO;
    }

    @Override
    public MultiValueMap<String, String> makeBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", authorizationCode);
        return body;
    }


}
