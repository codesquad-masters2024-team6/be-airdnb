package codesquad.airdnb.domain.member.dto.request;

import codesquad.airdnb.domain.member.LoginType;
import codesquad.airdnb.domain.member.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
        @NotBlank
        @Size(min = 4, max = 50)
        String loginId,

        @NotBlank
        @Size(min = 4, max = 30)
        String loginPassword,

        @NotBlank
        @Size(min = 2, max = 50)
        String nickname
) {

    public Member toEntity() {
        return Member.builder()
                .loginId(loginId)
                .loginPassword(loginPassword)
                .nickname(nickname)
                .loginType(LoginType.DEFAULT)
                .build();
    }

    public Member toEntity(LoginType loginType) {
        return Member.builder()
                .loginId(loginId)
                .loginPassword(loginPassword)
                .nickname(nickname)
                .loginType(loginType)
                .build();
    }
}
