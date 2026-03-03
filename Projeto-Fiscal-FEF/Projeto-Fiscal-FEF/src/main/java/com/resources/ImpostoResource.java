package com.resources;

import com.domains.dtos.ImpostoRequestDTO;
import com.domains.dtos.ImpostoResponseDTO;
import com.services.ImpostoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/v1/impostos")
public class ImpostoResource {

    private final ImpostoService service;

    public ImpostoResource(ImpostoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Impostos API OK");
    }

    @PostMapping("/calcular")
    public ResponseEntity<ImpostoResponseDTO> calcular(
            @RequestBody @Validated ImpostoRequestDTO request
    ) {
        double imposto = service.calcularImposto(request.getValor(), request.getTipoTransacao());
        return ResponseEntity.ok(new ImpostoResponseDTO(request.getValor(), request.getTipoTransacao(), imposto));
    }
}