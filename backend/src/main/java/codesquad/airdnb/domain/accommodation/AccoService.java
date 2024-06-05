package codesquad.airdnb.domain.accommodation;

import codesquad.airdnb.domain.accommodation.request.AccoCreateRequest;
import codesquad.airdnb.domain.accommodation.response.AccoDetailResponse;
import codesquad.airdnb.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AccoService {

    private final AccoRepository accoRepository;

    private final MemberRepository memberRepository;

    public AccoDetailResponse create(AccoCreateRequest accoCreateRequest) {
        Accommodation data = Accommodation.builder()
                .host(memberRepository.findById(accoCreateRequest.getHostId())
                        .orElseThrow(() -> new NoSuchElementException()))
                .build();

        accoRepository.save(data);
        return AccoDetailResponse
    }
}
