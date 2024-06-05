package codesquad.airdnb.domain.accommodation.request.accoCreationAdditionals;

import codesquad.airdnb.domain.accommodation.embedded.FloorPlan;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FloorPlanData {

    @NotBlank
    @Size(min = 1)
    private Integer maxGuest;

    @NotBlank
    private Integer maxInfant;

    @NotBlank
    private Integer bedroomNum;

    @NotBlank
    private Integer bedNum;

    @NotBlank
    private Integer bathroomNum;

    public FloorPlan toEmbedded() {
        return FloorPlan.builder()
                .maxGuest(maxGuest)
                .maxInfant(maxInfant)
                .bedroomNum(bedroomNum)
                .bedNum(bedNum)
                .bathroomNum(bathroomNum)
                .build();
    }
}
