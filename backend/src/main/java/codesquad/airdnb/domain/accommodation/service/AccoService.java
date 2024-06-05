package codesquad.airdnb.domain.accommodation.service;

import codesquad.airdnb.domain.accommodation.entity.AccoImage;
import codesquad.airdnb.domain.accommodation.entity.Accommodation;
import codesquad.airdnb.domain.accommodation.repository.AccoImageRepository;
import codesquad.airdnb.domain.accommodation.repository.AccoRepository;
import codesquad.airdnb.domain.accommodation.request.AccoCreateRequest;
import codesquad.airdnb.domain.accommodation.response.AccoContentResponse;
import codesquad.airdnb.domain.member.Member;
import codesquad.airdnb.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AccoService {

    private final AccoRepository accoRepository;

    private final AccoImageRepository accoImageRepository;

    private final MemberRepository memberRepository;

    public AccoContentResponse create(AccoCreateRequest request) {
        Member host = memberRepository.findById(request.getHostId()).orElseThrow(NoSuchElementException::new);
        Accommodation accommodation = request.buildAccommodation(host);
        Accommodation savedAcco = accoRepository.save(accommodation);
        List<AccoImage> accoImages = request.buildAccoImages(savedAcco);
        accoImageRepository.saveAllAndFlush(accoImages);
        return AccoContentResponse.of(savedAcco, accoImages);
    }
}
