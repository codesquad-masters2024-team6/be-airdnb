package codesquad.airdnb.domain.accommodation.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACCO_AMEN")
public class AccoAmen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ACCO_ID")
    private Accommodation accommodation;

    @ManyToOne
    @JoinColumn(name = "AMEN_ID")
    private Amenity amenity;

    public static List<AccoAmen> of(Accommodation accommodation, List<Amenity> amenities) {
        return amenities.stream()
                .map(amenity -> AccoAmen.builder()
                        .accommodation(accommodation)
                        .amenity(amenity)
                        .build())
                .toList();
    }
}