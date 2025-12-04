package com.resources;

import com.domains.dtos.PagamentoDTO;
import com.services.PagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/lancamentos/pagamentos")
public class PagamentoResource {

    private final PagamentoService service;

    public PagamentoResource(PagamentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> create(@RequestBody @Validated(PagamentoDTO.Create.class) PagamentoDTO dto) {
        PagamentoDTO created = service.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDTO> update(@PathVariable Long id,
                                               @RequestBody @Validated(PagamentoDTO.Update.class) PagamentoDTO dto) {
        PagamentoDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
