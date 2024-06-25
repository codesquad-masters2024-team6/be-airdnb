package codesquad.airdnb.domain.accommodation.dto.response;

import org.locationtech.jts.geom.Point;

import java.util.List;

public record FilteredAccosResponse(
        Long accoId,
        String title,
        String placeCategory,
        Integer maxGuestCount,
        Integer maxInfantCount,
        Integer bedroomCount,
        Integer bathroomCount,
        Point coordinate,
        Long totalPrice,
        List<String> amenities,
        List<String> imageUrls
) {
}
