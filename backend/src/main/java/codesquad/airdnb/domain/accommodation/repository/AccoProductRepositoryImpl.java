package codesquad.airdnb.domain.accommodation.repository;

import codesquad.airdnb.domain.accommodation.dto.response.FilteredAccosResponse;
import codesquad.airdnb.domain.accommodation.entity.QAccoProduct;
import codesquad.airdnb.domain.accommodation.entity.QAccommodation;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccoProductRepositoryImpl implements AccoProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Query(value = "CALL createYearlyProduct(:accoId, :price)", nativeQuery = true)
    @Modifying
    @Transactional
    @Override
    public void createYearlyProduct(Long accoId, Long price) {
    };

    @Query(value = "CALL createNextProductForAllAcco()", nativeQuery = true)
    @Modifying
    @Transactional
    @Override
    public void createNextProductForAllAcco() {
    };


    @Override
    public List<FilteredAccosResponse> getAccoListFilteredBy(List<Long> accoIds, LocalDate checkInDate, LocalDate checkOutDate) {
        QAccoProduct qAccoProduct = QAccoProduct.accoProduct;
        QAccommodation qAccommodation = QAccommodation.accommodation;

        return queryFactory.select(Projections.constructor(FilteredAccosResponse.class,
                        qAccommodation.id,
                        qAccommodation.title,
                        qAccommodation.placeCategory,
                        qAccommodation.floorPlan.maxGuestCount,
                        qAccommodation.floorPlan.maxInfantCount,
                        qAccommodation.floorPlan.bedroomCount,
                        qAccommodation.floorPlan.bathroomCount,
                        qAccommodation.location.coordinate,
                        qAccoProduct.price.sum())
                )
                .from(qAccoProduct)
                .join(qAccoProduct.accommodation, qAccommodation)
                .where(qAccoProduct.accommodation.id.in(accoIds)
                        .and(qAccoProduct.reserveDate.between(checkInDate, checkOutDate))
                        .and(qAccoProduct.isReserved.isFalse()))
                .groupBy(
                        qAccommodation.id,
                        qAccommodation.title,
                        qAccommodation.placeCategory,
                        qAccommodation.floorPlan.maxGuestCount,
                        qAccommodation.floorPlan.maxInfantCount,
                        qAccommodation.floorPlan.bedroomCount,
                        qAccommodation.floorPlan.bathroomCount,
                        qAccommodation.location.coordinate
                )
                .fetch();
    }
}
