package codesquad.airdnb.domain.accommodation.controller;

import codesquad.airdnb.domain.accommodation.dto.response.AccoContentResponse;
import codesquad.airdnb.domain.accommodation.request.AccoCreateRequest;
import codesquad.airdnb.domain.accommodation.service.AccoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/host/accommodations")
@RequiredArgsConstructor
public class AccoController {

    private final AccoService accoService;

    @PostMapping
    public ResponseEntity<AccoContentResponse> create(@Valid @RequestBody AccoCreateRequest request) {;
        return ResponseEntity.ok(accoService.create(request));
    }

    @GetMapping
    public ResponseEntity<AccoListResponse> getList() {
        return ResponseEntity.ok(accoService.getList());
    }
}
