package codesquad.airdnb.domain.accommodation.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record AccoReservationRequest (

        @NotNull
        Long accoId,

        @NotNull
        @Min(value = 1, message = "최소 한명의 성인이 있어야 합니다.")
        Long adultCount,

        // todo: 어린이, 유아 Notnull?
        @Min(value = 0)
        Long childCount,

        @Min(value = 0)
        Long infantCount,

        @NotNull
        @FutureOrPresent
        Date startDate,

        @NotNull
        @Future
        Date endDate
) {

    
    
}
