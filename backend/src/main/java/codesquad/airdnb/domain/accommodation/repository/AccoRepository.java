package codesquad.airdnb.domain.accommodation.repository;

import codesquad.airdnb.domain.accommodation.controller.AccoListData;
import codesquad.airdnb.domain.accommodation.entity.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccoRepository extends JpaRepository<Accommodation, Long> {

    List<AccoListData> findAllBy();
}
