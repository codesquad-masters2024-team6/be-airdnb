package codesquad.airdnb.domain.accommodation.dto.response;

import codesquad.airdnb.domain.accommodation.entity.AccoImage;
import codesquad.airdnb.domain.accommodation.entity.Amenity;
import codesquad.airdnb.domain.member.Member;
import org.locationtech.jts.geom.Point;

import java.sql.Time;
import java.util.List;

public record AccoDetailsResponse(
        Long id,

        Member host,

        String title,

        String placeCategory,

        Long basePricePerNight,

        String description,

        Time checkInTime,

        Time checkOutTime,

        Integer maxGuestCount,

        Integer maxInfantCount,

        Integer bedroomCount,

        Integer bedCount,

        Integer bathroomCount,

        Point coordinate,

        List<AccoImage> imageUrls,
        List<Amenity> amenities
) {
}
