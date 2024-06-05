package codesquad.airdnb.domain.accommodation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccoRepository extends JpaRepository<Accommodation, Long> {
}
