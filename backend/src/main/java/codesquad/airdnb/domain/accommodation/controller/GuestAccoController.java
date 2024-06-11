package codesquad.airdnb.domain.accommodation.controller;

import codesquad.airdnb.domain.accommodation.service.AccoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/accommodations")
@RequiredArgsConstructor
public class GuestAccoController {

    private final AccoService accoService;

    @GetMapping("/{accoId}")
    public ResponseEntity<?> getDetailsForReservation(
            @PathVariable("accoId") Long accoId,
            @RequestParam("guestCount") Long guestCount,
            @RequestParam(value = "infantCount", required = false) Long infantCount,
            @RequestParam("checkInDate") LocalDateTime checkInDate,
            @RequestParam("checkOutDate") LocalDateTime checkOutDate
    ) {

        return ResponseEntity.ok("ok");
    }

    @PostMapping("/reserve")
    public ResponseEntity<Void> reservation(@RequestBody  )
}
