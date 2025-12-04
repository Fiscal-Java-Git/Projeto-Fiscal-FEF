package com.resources;

import com.domains.dtos.MovimentoContaDTO;
import com.services.MovimentoContaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movimentos")
public class MovimentoContaResource {

    private final MovimentoContaService service;

    public MovimentoContaResource(MovimentoContaService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovimentoContaDTO>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping
    public ResponseEntity<Page<MovimentoContaDTO>> list(@PageableDefault(size = 20) Pageable pageable) {
        List<MovimentoContaDTO> all = service.findAll();
        Page<MovimentoContaDTO> page = new PageImpl<>(all, pageable, all.size());
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentoContaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
