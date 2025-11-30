package com.projetofef.resources;

import com.projetofef.domains.dtos.CentroCustoDTO;
import com.projetofef.services.CentroCustoService;
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
@RequestMapping("/api/centros-custo")
public class CentroCustoResource {

    private final CentroCustoService service;

    public CentroCustoResource(CentroCustoService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CentroCustoDTO>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping
    public ResponseEntity<Page<CentroCustoDTO>> list(@PageableDefault(size = 20, sort = "nome") Pageable pageable) {
        List<CentroCustoDTO> all = service.findAll();
        Page<CentroCustoDTO> page = new PageImpl<>(all, pageable, all.size());
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentroCustoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CentroCustoDTO> create(@RequestBody @Validated(CentroCustoDTO.Create.class) CentroCustoDTO dto) {
        CentroCustoDTO created = service.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentroCustoDTO> update(@PathVariable Long id,
                                                 @RequestBody @Validated(CentroCustoDTO.Update.class) CentroCustoDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
