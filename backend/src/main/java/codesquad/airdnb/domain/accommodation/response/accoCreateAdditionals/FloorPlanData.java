package codesquad.airdnb.domain.accommodation.response.accoCreateAdditionals;

import codesquad.airdnb.domain.accommodation.embedded.FloorPlan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FloorPlanData {

    private Integer maxGuest;

    private Integer maxInfant;

    private Integer bedroomNum;

    private Integer bedNum;

    private Integer bathroomNum;

    public static FloorPlanData of(FloorPlan floorPlan) {
        return FloorPlanData.builder()
                .maxGuest(floorPlan.getMaxGuest())
                .maxInfant(floorPlan.getMaxInfant())
                .bedroomNum(floorPlan.getBedroomNum())
                .bedNum(floorPlan.getBedNum())
                .bathroomNum(floorPlan.getBathroomNum())
                .build();
    }
}
