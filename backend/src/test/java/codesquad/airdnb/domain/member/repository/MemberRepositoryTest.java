package codesquad.airdnb.domain.member.repository;

import codesquad.airdnb.domain.member.Member;
import codesquad.airdnb.domain.member.MemberRepository;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;


    // JacksonPlugin이 뭐지
    private FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .plugin(new JakartaValidationPlugin())
            .build();


    @Test
    void 멤버를_저장하고_그_멤버를_가져올_수_있다() {
        // Given
        Member member = fixtureMonkey.giveMeOne(Member.class);

        // When
        Member savedMember = memberRepository.save(member);
        Member foundMember = memberRepository.findById(savedMember.getId())
                .orElseThrow(() -> new NoSuchElementException("해당 Id의 멤버가 없습니다."));

        // Then
        assertThat(foundMember).isEqualTo(member);
    }
}
