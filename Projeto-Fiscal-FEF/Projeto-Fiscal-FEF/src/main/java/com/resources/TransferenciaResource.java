package com.resources;

import com.domains.dtos.TransferenciaDTO;
import com.services.TransferenciaService;
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
@RequestMapping("/api/v1/transferencias")
public class TransferenciaResource {

    private final TransferenciaService service;

    public TransferenciaResource(TransferenciaService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TransferenciaDTO>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping
    public ResponseEntity<Page<TransferenciaDTO>> list(@PageableDefault(size = 20) Pageable pageable) {
        List<TransferenciaDTO> all = service.findAll();
        Page<TransferenciaDTO> page = new PageImpl<>(all, pageable, all.size());
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferenciaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<TransferenciaDTO> create(@RequestBody @Validated(TransferenciaDTO.Create.class) TransferenciaDTO dto) {
        TransferenciaDTO created = service.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransferenciaDTO> update(@PathVariable Long id,
                                                   @RequestBody @Validated(TransferenciaDTO.Update.class) TransferenciaDTO dto) {
        TransferenciaDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
