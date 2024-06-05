package codesquad.airdnb.domain.accommodation.service;

import codesquad.airdnb.domain.accommodation.entity.AccoAmen;
import codesquad.airdnb.domain.accommodation.entity.AccoImage;
import codesquad.airdnb.domain.accommodation.entity.Accommodation;
import codesquad.airdnb.domain.accommodation.entity.Amenity;
import codesquad.airdnb.domain.accommodation.repository.AccoImageRepository;
import codesquad.airdnb.domain.accommodation.repository.AccoRepository;
import codesquad.airdnb.domain.accommodation.repository.AmenityRepository;
import codesquad.airdnb.domain.accommodation.request.AccoCreateRequest;
import codesquad.airdnb.domain.accommodation.dto.response.AccoContentResponse;
import codesquad.airdnb.domain.member.Member;
import codesquad.airdnb.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AccoService {

    private final AccoRepository accoRepository;

    private final AccoImageRepository accoImageRepository;

    private final AmenityRepository amenityRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public AccoContentResponse create(AccoCreateRequest request) {
        Member host = memberRepository.findById(request.getHostId()).orElseThrow(NoSuchElementException::new);
        Accommodation accommodation = request.buildAccommodation(host);

        List<Amenity> amenities = amenityRepository.findAllById(request.getAmenities());
        List<AccoAmen> accoAmens = amenities.stream()
                .map(amenity -> AccoAmen.builder()
                        .amenity(amenity)
                        .accommodation(accommodation)
                        .build()
                ).toList();
        accommodation.addAmenities(accoAmens);

        Accommodation savedAcco = accoRepository.save(accommodation);
        List<AccoImage> accoImages = request.buildAccoImages(savedAcco);
        accoImageRepository.saveAll(accoImages);
        return AccoContentResponse.of(savedAcco, accoImages);
    }
}
