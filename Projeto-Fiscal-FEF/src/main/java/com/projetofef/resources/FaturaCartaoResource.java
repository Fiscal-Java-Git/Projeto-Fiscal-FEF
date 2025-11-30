package com.projetofef.resources;

import com.projetofef.domains.dtos.FaturaCartaoDTO;
import com.projetofef.services.FaturaCartaoService;
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
@RequestMapping("/api/faturas")
public class FaturaCartaoResource {

    private final FaturaCartaoService service;

    public FaturaCartaoResource(FaturaCartaoService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<FaturaCartaoDTO>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping
    public ResponseEntity<Page<FaturaCartaoDTO>> list(@PageableDefault(size = 20, sort = "competencia") Pageable pageable) {
        List<FaturaCartaoDTO> all = service.findAll();
        Page<FaturaCartaoDTO> page = new PageImpl<>(all, pageable, all.size());
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FaturaCartaoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<FaturaCartaoDTO> create(@RequestBody @Validated(FaturaCartaoDTO.Create.class) FaturaCartaoDTO dto) {
        FaturaCartaoDTO created = service.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FaturaCartaoDTO> update(@PathVariable Long id,
                                                  @RequestBody @Validated(FaturaCartaoDTO.Update.class) FaturaCartaoDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
