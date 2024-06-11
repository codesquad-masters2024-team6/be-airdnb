package codesquad.airdnb.domain.accommodation.repository;

import codesquad.airdnb.domain.accommodation.entity.AccoProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AccoProductRepository extends JpaRepository<AccoProduct, Long> {

    // DetailsForReservationResponse getAccoProductDetails();
    @Query(value = "CALL createYearlyProduct(:accoId, :price)", nativeQuery = true)
    @Modifying
    @Transactional
    void createYearlyProduct(Long accoId, Long price);

    @Query(value = "CALL createNextProductForAllAcco()", nativeQuery = true)
    @Modifying
    @Transactional
    void createNextProductForAllAcco();
}
