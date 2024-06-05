package codesquad.airdnb.domain.accommodation.request;

import codesquad.airdnb.domain.accommodation.Accommodation;
import codesquad.airdnb.domain.accommodation.request.accoCreationAdditionals.FloorPlanData;
import codesquad.airdnb.domain.accommodation.request.accoCreationAdditionals.LocationData;
import codesquad.airdnb.domain.member.Member;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccoCreateRequest {

    @NotBlank
    private Long hostId;

    @NotBlank
    @Size(max = 50)
    private String title;

    @NotBlank
    private String placeCategory;

    @NotBlank
    private Long price;

    @Size(max = 500)
    private String description;

    @NotBlank
    private Time checkInTime;

    @NotBlank
    private Time checkOutTime;

    @Valid
    private LocationData locationData;

    @Valid
    private FloorPlanData floorPlanData;

    @Size(min = 5)
    private List<String> imageUrls;

    public boolean validateImageUrls() {
        return imageUrls.stream().noneMatch(String::isBlank);
    }

    // TODO: Image는? 그러면 toEntity가 두 개??
    public Accommodation toEntity(Member host) {
        return Accommodation.builder()
                .host(host)
                .title(title)
                .placeCategory(placeCategory)
                .price(price)
                .description(description)
                .checkInTime(checkInTime)
                .checkOutTime(checkOutTime)
                .floorPlan(floorPlanData.toEmbedded())
                .location(locationData.toEmbedded())
                .build();
    }
}
