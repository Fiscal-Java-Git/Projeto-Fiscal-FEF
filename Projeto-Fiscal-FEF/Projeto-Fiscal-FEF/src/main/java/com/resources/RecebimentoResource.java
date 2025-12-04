package com.resources;

import com.domains.dtos.RecebimentoDTO;
import com.services.RecebimentoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/lancamentos/recebimentos")
public class RecebimentoResource {

    private final RecebimentoService service;

    public RecebimentoResource(RecebimentoService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RecebimentoDTO>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping
    public ResponseEntity<Page<RecebimentoDTO>> list(@PageableDefault(size = 20) Pageable pageable) {
        List<RecebimentoDTO> all = service.findAll();
        Page<RecebimentoDTO> page = new PageImpl<>(all, pageable, all.size());
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecebimentoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<RecebimentoDTO> create(@RequestBody @Validated(RecebimentoDTO.Create.class) RecebimentoDTO dto) {
        RecebimentoDTO created = service.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecebimentoDTO> update(@PathVariable Long id,
                                                 @RequestBody @Validated(RecebimentoDTO.Update.class) RecebimentoDTO dto) {
        RecebimentoDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
