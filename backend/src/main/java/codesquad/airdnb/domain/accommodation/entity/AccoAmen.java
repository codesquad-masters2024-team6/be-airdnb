package codesquad.airdnb.domain.accommodation.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
