package codesquad.airdnb.domain.accommodation.request.accoCreationAdditionals;

import codesquad.airdnb.domain.accommodation.embedded.FloorPlan;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FloorPlanData {

    @NotNull
    @Min(value = 1)
    private Integer maxGuest;

    @NotNull
    @Min(value = 0)
    private Integer maxInfant;

    @NotNull
    @Min(value = 0)
    private Integer bedroomNum;

    @NotNull
    @Min(value = 0)
    private Integer bedNum;

    @NotNull
    @Min(value = 0)
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
