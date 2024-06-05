package codesquad.airdnb.domain.accommodation.request.accoCreationAdditionals;

import codesquad.airdnb.domain.accommodation.embedded.Location;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LocationData {

    @NotBlank
    private String country;

    @NotBlank
    private String province;

    private String city;

    @NotBlank
    private String district;

    @NotBlank
    private String streetAddress;

    private String streetAddressDetail;

    private Long postalCode;

    @NotBlank
    @Pattern(regexp = "^[0-9]+\\.[0-9]+$")
    private Double coordinateX;

    @NotBlank
    @Pattern(regexp = "^[0-9]+\\.[0-9]+$")
    private Double coordinateY;

    public Location toEmbedded() {
        return Location.builder()
                .country(country)
                .province(province)
                .city(city)
                .district(district)
                .streetAddress(streetAddress)
                .streetAddressDetail(streetAddressDetail)
                .postalCode(postalCode)
                .coordinate(new Point(coordinateX, coordinateY))
                .build();
    }
}
