package com.services;

import com.domains.ContaBancaria;
import com.domains.MovimentoConta;
import com.domains.dtos.ExtratoDTO;
import com.domains.dtos.MovimentoContaDTO;
import com.repositories.ContaBancariaRepository;
import com.repositories.MovimentoContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ExtratoService {

    @Autowired
    private ContaBancariaRepository contaRepo;

    @Autowired
    private MovimentoContaRepository movimentoRepo;

    public ExtratoDTO gerarExtrato(Long contaId, LocalDate inicio, LocalDate fim) {

        ContaBancaria conta = contaRepo.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        List<MovimentoConta> movimentos;


        if (inicio != null && fim != null) {
            movimentos = movimentoRepo.findByConta_IdAndDataMovimentoBetween(
                    contaId,
                    inicio.atStartOfDay(),             // 00:00:00
                    fim.atTime(23, 59, 59)            // 23:59:59
            );
        } else {
            movimentos = movimentoRepo.findByConta_IdOrderByDataMovimentoAsc(contaId);
        }


        movimentos.sort(Comparator.comparing(MovimentoConta::getDataMovimento));

        ExtratoDTO dto = new ExtratoDTO();
        dto.setContaId(conta.getId());
        dto.setInstituicao(conta.getInstituicao());
        dto.setAgencia(conta.getAgencia());
        dto.setNumero(conta.getNumero());
        dto.setApelido(conta.getApelido());
        dto.setSaldoInicial(conta.getSaldoInicial());

        BigDecimal saldo = conta.getSaldoInicial();
        List<MovimentoContaDTO> lista = new ArrayList<>();

        for (MovimentoConta m : movimentos) {
            saldo = saldo.add(m.getValor());

            MovimentoContaDTO mov = new MovimentoContaDTO();
            mov.setId(m.getId());
            mov.setContaId(contaId);
            mov.setTipo(m.getTipo());
            mov.setValor(m.getValor());
            mov.setHistorico(m.getHistorico());
            mov.setReferenciaId(m.getReferenciaId());
            mov.setReferenciaTipo(m.getReferenciaTipo());
            mov.setDataMovimento(m.getDataMovimento());
            mov.setCriadoEm(m.getCriadoEm());
            mov.setAtualizadoEm(m.getAtualizadoEm());

            lista.add(mov);
        }

        dto.setMovimentacoes(lista);
        dto.setSaldoFinal(saldo);

        return dto;
    }
}
