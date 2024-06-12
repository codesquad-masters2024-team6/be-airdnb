package codesquad.airdnb.domain.accommodation.dto.additionals;

import codesquad.airdnb.domain.accommodation.entity.embedded.Location;
import codesquad.airdnb.domain.accommodation.util.GeometryHelper;
import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record LocationData (

    @NotBlank
    @Size(min = 2, max = 2)
    String country,

    @NotBlank
    String province,

    String city,

    @NotBlank
    String district,

    @NotBlank
    String streetAddress,

    String streetAddressDetail,

    String postalCode,

    @NotNull
    @Min(value = -90)
    @Max(value = 90)
    Double coordinateX,

    @NotNull
    @Min(value = -180)
    @Max(value = 180)
    Double coordinateY
) {

    public Location toEmbedded() {
        return Location.builder()
                .country(country)
                .province(province)
                .city(city)
                .district(district)
                .streetAddress(streetAddress)
                .streetAddressDetail(streetAddressDetail)
                .postalCode(postalCode)
                .coordinate(GeometryHelper.createPoint(coordinateX, coordinateY))
                .build();
    }

    public static LocationData toResponseEmbedded(Location location) {
        return LocationData.builder()
                .country(location.getCountry())
                .province(location.getProvince())
                .city(location.getCity())
                .district(location.getDistrict())
                .streetAddress(location.getStreetAddress())
                .streetAddressDetail(location.getStreetAddressDetail())
                .postalCode(location.getPostalCode())
                .coordinateX(location.getCoordinate().getX())
                .coordinateY(location.getCoordinate().getY())
                .build();
    }
}
