package codesquad.airdnb.domain.accommodation.repository;

import codesquad.airdnb.domain.accommodation.entity.AccoImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AccoImageRepository extends JpaRepository<AccoImage, Long> {

    Set<AccoImage> findAccoImageByAccommodation_Id(Long accoId);
}
