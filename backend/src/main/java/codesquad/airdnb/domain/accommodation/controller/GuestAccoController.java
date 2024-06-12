package codesquad.airdnb.domain.accommodation.controller;

import codesquad.airdnb.domain.accommodation.service.AccoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/accommodations")
@RequiredArgsConstructor
public class GuestAccoController {

    private final AccoService accoService;

    @GetMapping("/{accoId}")
    public ResponseEntity<?> getAccoByCondition(
            @RequestParam("guestCount") Integer guestCount,
            @RequestParam(value = "infantCount", required = false) Integer infantCount,
            @RequestParam("checkInDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam("checkOutDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
            @RequestParam("longitude") Double longitude,
            @RequestParam("latitude") Double latitude
    )
    {
        return ResponseEntity.ok(accoService.getFilteredList(guestCount, infantCount, checkInDate, checkOutDate, longitude, latitude));
    }
}
