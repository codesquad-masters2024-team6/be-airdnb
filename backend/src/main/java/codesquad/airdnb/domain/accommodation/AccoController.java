package codesquad.airdnb.domain.accommodation;

import codesquad.airdnb.domain.accommodation.request.AccoCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/host/accommodations")
@RequiredArgsConstructor
public class AccoController {

    private final AccoService accoService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AccoCreateRequest accoCreateRequest) {


        return ResponseEntity.ok(accoCreateRequest);
    }

}
