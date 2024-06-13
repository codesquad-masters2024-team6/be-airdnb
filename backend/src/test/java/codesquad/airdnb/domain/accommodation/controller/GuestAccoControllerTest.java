package codesquad.airdnb.domain.accommodation.controller;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import codesquad.airdnb.domain.accommodation.dto.request.AccoCreateRequest;
import codesquad.airdnb.domain.accommodation.dto.response.AccoContentResponse;
import codesquad.airdnb.domain.accommodation.dto.response.FilteredAccosResponse;
import codesquad.airdnb.domain.accommodation.entity.Accommodation;
import codesquad.airdnb.domain.accommodation.service.AccoService;
import codesquad.airdnb.domain.member.Member;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@WebMvcTest(GuestAccoControllerTest.class)

public class GuestAccoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccoService accoService;

    private FixtureMonkey fixtureMonkey = FixtureMonkey.builder().plugin(new JakartaValidationPlugin())
            .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
            .build();

    @BeforeAll
    static void beforeAll() {
    }

    @Test
    void 숙소를_검색하는_경우_숙소리스트와_함께_200_응답을_얻는다() {
//        List<FilteredAccosResponse> filteredList = fixtureMonkey.giveMe(FilteredAccosResponse.class, 5);
//        when(accoService.getFilteredList(any(), any(), any(), any(), any(), any()))
//                .thenReturn(filteredList);



    }


    @Test
    @Transactional
    void 등록한_숙소와_같은_정보로_검색하는_경우_그_숙소를_반환받을_수_있다() {
        AccoCreateRequest fakeRequest = fixtureMonkey.giveMeOne(AccoCreateRequest.class);
        Member member = new Member(1L, "testId", "testPW", "testUser");
        Accommodation fakeAcco = fakeRequest.buildAccommodation(member);

        AccoContentResponse response = accoService.create(fakeRequest);
        assertThat(response).isEqualTo(AccoContentResponse.of(fakeAcco));
    }
}
