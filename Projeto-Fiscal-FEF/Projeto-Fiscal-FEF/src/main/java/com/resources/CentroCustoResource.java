package com.resources;

import com.domains.dtos.CentroCustoDTO;
import com.services.CentroCustoService;
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
@RequestMapping("/api/v1/centros-custo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public ResponseEntity<Page<CentroCustoDTO>> list(
            @RequestParam(required = false) Boolean ativo,
            @PageableDefault(size = 20, sort = "nome") Pageable pageable) {

        List<CentroCustoDTO> list;

        if (ativo != null) {
            list = service.findByAtivo(ativo);
        } else {
            list = service.findAll();
        }

        Page<CentroCustoDTO> page = new PageImpl<>(list, pageable, list.size());
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
