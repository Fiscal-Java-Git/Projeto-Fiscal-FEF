package com.resources;

import com.domains.dtos.LancamentoDTO;
import com.services.LancamentoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/lancamentos")
public class LancamentoResource {

    private final LancamentoService service;

    public LancamentoResource(LancamentoService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<LancamentoDTO>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping
    public ResponseEntity<Page<LancamentoDTO>> list(@PageableDefault(size = 20) Pageable pageable) {
        List<LancamentoDTO> all = service.findAll();
        Page<LancamentoDTO> page = new PageImpl<>(all, pageable, all.size());
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancamentoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<LancamentoDTO> create(@RequestBody @Validated(LancamentoDTO.Create.class) LancamentoDTO dto) {
        LancamentoDTO created = service.create(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LancamentoDTO> update(@PathVariable Long id,
                                                @RequestBody @Validated(LancamentoDTO.Update.class) LancamentoDTO dto) {
        LancamentoDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{id}/cancelar")
    public ResponseEntity<LancamentoDTO> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(service.cancelar(id));
    }


}
