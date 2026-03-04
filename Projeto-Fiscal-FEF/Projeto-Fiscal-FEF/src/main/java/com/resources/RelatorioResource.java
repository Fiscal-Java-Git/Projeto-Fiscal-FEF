package com.resources;

import com.domains.dtos.PosicaoGeralDTO;
import com.services.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/relatorios")
@RequiredArgsConstructor
public class RelatorioResource {

    private final RelatorioService relatorioService;

    @GetMapping("/extrato")
    public ResponseEntity<?> gerarExtrato(
            @RequestParam Long usuarioId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {

        return ResponseEntity.ok(
                relatorioService.gerarExtrato(
                        usuarioId,
                        inicio,
                        fim
                )
        );
    }

    @GetMapping("/contas-a-pagar")
    public ResponseEntity<?> contasAPagar(@RequestParam Long usuarioId) {
        return ResponseEntity.ok(
                relatorioService.contasAPagar(usuarioId)
        );
    }

    @GetMapping("/contas-a-receber")
    public ResponseEntity<?> contasAReceber(@RequestParam Long usuarioId) {
        return ResponseEntity.ok(
                relatorioService.contasAReceber(usuarioId)
        );
    }

    @GetMapping("/posicao-geral")
    public ResponseEntity<PosicaoGeralDTO> posicaoGeral(
            @RequestParam Long usuarioId) {

        return ResponseEntity.ok(
                relatorioService.posicaoGeral(usuarioId)
        );
    }
}