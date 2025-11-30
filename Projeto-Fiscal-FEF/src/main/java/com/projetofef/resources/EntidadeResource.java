package com.projetofef.resources;

import com.projetofef.domains.dtos.EntidadeDTO;
import com.projetofef.services.EntidadeService;
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
@RequestMapping("/api/entidades")
public class EntidadeResource {

    private final EntidadeService service;

    public EntidadeResource(EntidadeService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EntidadeDTO>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping
    public ResponseEntity<Page<EntidadeDTO>> list(@PageableDefault(size = 20, sort = "nome") Pageable pageable) {
        List<EntidadeDTO> all = service.findAll();
        Page<EntidadeDTO> page = new PageImpl<>(all, pageable, all.size());
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadeDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<EntidadeDTO> create(@RequestBody @Validated(EntidadeDTO.Create.class) EntidadeDTO dto) {
        EntidadeDTO created = service.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntidadeDTO> update(@PathVariable Long id,
                                              @RequestBody @Validated(EntidadeDTO.Update.class) EntidadeDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
