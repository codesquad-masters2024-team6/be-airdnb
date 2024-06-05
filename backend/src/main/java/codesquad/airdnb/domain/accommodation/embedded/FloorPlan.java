package codesquad.airdnb.domain.accommodation.embedded;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FloorPlan {

    private Integer maxGuest;

    private Integer maxInfant;

    private Integer bedroomNum;

    private Integer bedNum;

    private Integer bathroomNum;
}
