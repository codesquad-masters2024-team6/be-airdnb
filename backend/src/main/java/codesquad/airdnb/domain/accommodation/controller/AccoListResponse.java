package codesquad.airdnb.domain.accommodation.controller;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AccoListResponse {

    private List<AccoListData> accommodationList;
}
