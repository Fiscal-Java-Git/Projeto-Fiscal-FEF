package com.resources;

import com.domains.dtos.FaturaCartaoDTO;
import com.services.FaturaCartaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cartoes")
public class FaturaCartaoResource {

    private final FaturaCartaoService service;

    public FaturaCartaoResource(FaturaCartaoService service) {
        this.service = service;
    }

    @GetMapping("/{id}/faturas")
    public ResponseEntity<List<FaturaCartaoDTO>> findAllByCartao(@PathVariable Long id) {
        return ResponseEntity.ok(service.findAllByCartaoId(id));
    }

    @PostMapping("/{id}/faturas/fechamento")
    public ResponseEntity<FaturaCartaoDTO> fechar(@PathVariable Long id) {
        return ResponseEntity.ok(service.fecharFatura(id));
    }

    @PostMapping("/{id}/faturas/{faturaId}/pagar")
    public ResponseEntity<FaturaCartaoDTO> pagar(
            @PathVariable Long id,
            @PathVariable Long faturaId) {

        return ResponseEntity.ok(service.pagarFatura(id, faturaId));
    }
}
