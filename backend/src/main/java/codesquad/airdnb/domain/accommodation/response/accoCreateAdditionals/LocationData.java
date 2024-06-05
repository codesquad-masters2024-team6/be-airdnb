package codesquad.airdnb.domain.accommodation.response.accoCreateAdditionals;

import codesquad.airdnb.domain.accommodation.embedded.Location;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationData {

    private String country;

    private String province;

    private String city;

    private String district;

    private String streetAddress;

    private String streetAddressDetail;

    private Long postalCode;

    private Point coordinate;

    public static LocationData of(Location location) {
        return LocationData.builder()
                .country(location.getCountry())
                .province(location.getProvince())
                .city(location.getCity())
                .district(location.getDistrict())
                .streetAddress(location.getStreetAddress())
                .streetAddressDetail(location.getStreetAddressDetail())
                .postalCode(location.getPostalCode())
                .coordinate(location.getCoordinate())
                .build();
    }
}
