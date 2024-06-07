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

    // TODO: 이후 로그인이 구현되면 토큰 등으로 로그인한 사용자의 정보를 전달받아 해당 사용자의 숙소만 뽑아내도록 변경
    @GetMapping
    public ResponseEntity<AccoListResponse> getList(@RequestParam Long hostId) {
        return ResponseEntity.ok(accoService.getList(hostId));
    }
}