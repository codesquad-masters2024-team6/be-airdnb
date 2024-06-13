package codesquad.airdnb.domain.accommodation.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

import codesquad.airdnb.domain.accommodation.dto.request.AccoCreateRequest;
import codesquad.airdnb.domain.accommodation.dto.request.AccoReservationRequest;
import codesquad.airdnb.domain.accommodation.dto.response.AccoContentResponse;
import codesquad.airdnb.domain.accommodation.entity.AccoProduct;
import codesquad.airdnb.domain.accommodation.entity.Accommodation;
import codesquad.airdnb.domain.accommodation.entity.Amenity;
import codesquad.airdnb.domain.accommodation.repository.*;
import codesquad.airdnb.domain.accommodation.util.GeometryHelper;
import codesquad.airdnb.domain.member.Member;
import codesquad.airdnb.domain.member.MemberRepository;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@SpringBootTest
class AccoServiceTest {

    @MockBean
    private AccoRepository accoRepository;

    @MockBean
    private MemberRepository memberRepository;

    @MockBean
    private AmenityRepository amenityRepository;

    @MockBean
    private AccoProductRepository accoProductRepository;

    @Autowired
    private AccoService accoService;

    private final GeometryHelper geometryHelper = new GeometryHelper();

    private final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .plugin(new JakartaValidationPlugin())
            .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
            .build();


    @RepeatedTest(100)
    void 등록된_호스트의_정보로_숙소_등록을_할_경우_숙소_정보_저장에_성공한다() {
        // given
        AccoCreateRequest fakeRequest = fixtureMonkey.giveMeBuilder(AccoCreateRequest.class)
                .setNotNull("locationData")
                .setNotNull("floorPlanData")
                .set("locationData.point", geometryHelper.createPoint(11.11, 22.22))
                .set("checkInTime", Time.valueOf("16:00:00"))
                .set("checkOutTime", Time.valueOf("11:00:00"))
                .sample();
        Member member = mock(Member.class);
        List<Amenity> amenities = fixtureMonkey.giveMe(Amenity.class, fakeRequest.amenities().size());
        Accommodation fakeAcco = Mockito.spy(fakeRequest.buildAccommodation(member));
        when(fakeAcco.getId()).thenReturn(1L);

        given(memberRepository.findById(fakeRequest.hostId())).willReturn(Optional.ofNullable(member));
        given(amenityRepository.findAllById(fakeRequest.amenities())).willReturn(amenities);
        given(accoRepository.save(any(Accommodation.class))).willReturn(fakeAcco);
        given(accoRepository.findById(fakeAcco.getId())).willReturn(Optional.of(fakeAcco));
        willDoNothing().given(accoProductRepository).createYearlyProduct(anyLong(), anyLong());

        // when
        AccoContentResponse response = accoService.create(fakeRequest);

        // then
        assertThat(response).isEqualTo(AccoContentResponse.of(fakeAcco));
    }

    @Test
    void 등록된_숙소_상품을_예약할_수_있다() {
        // given
        LocalDate now = LocalDate.now();
        AccoReservationRequest fakeRequest = fixtureMonkey.giveMeBuilder(AccoReservationRequest.class)
                .set("accoId")
                .setPostCondition("startDate", LocalDate.class, startDate -> startDate.isAfter(now))
                .thenApply((it, builder) ->
                        builder.setPostCondition(
                                "endDate", LocalDate.class, endDate -> endDate.isAfter(it.startDate())
                        )
                )
                .sample();
        List<AccoProduct> products = fixtureMonkey.giveMe(AccoProduct.class, 5);

        given(accoProductRepository.findAllById(fakeRequest.products())).willReturn(List.of(mock(AccoProduct.class)));

    }
}
