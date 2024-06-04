package codesquad.airdnb.domain.accommodation;


import jakarta.persistence.*;

@Entity
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
