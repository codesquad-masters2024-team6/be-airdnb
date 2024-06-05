package codesquad.airdnb.domain.accommodation.response;

import codesquad.airdnb.domain.accommodation.embedded.FloorPlan;
import codesquad.airdnb.domain.accommodation.embedded.Location;
import lombok.Builder;

import java.sql.Time;
import java.util.List;

@Builder
public class AccoDetailResponse {

    private Long id;

    private Long hostId;

    private String title;

    private String placeCategory;

    private Long price;

    private String description;

    private Time checkInTime;

    private Time checkOutTime;

    private Location location;

    private FloorPlan floorPlan;

    private List<String> imageUrls;
}
