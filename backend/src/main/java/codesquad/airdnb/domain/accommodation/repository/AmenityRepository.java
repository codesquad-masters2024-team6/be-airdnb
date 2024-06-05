package codesquad.airdnb.domain.accommodation.repository;

import codesquad.airdnb.domain.accommodation.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {
}
