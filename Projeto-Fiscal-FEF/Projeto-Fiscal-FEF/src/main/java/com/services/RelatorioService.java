package com.services;

import com.domains.Lancamento;
import com.domains.dtos.PosicaoGeralDTO;
import com.domains.enums.StatusLancamento;
import com.domains.enums.TipoLancamento;
import com.repositories.LancamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final LancamentoRepository lancamentoRepository;

    public List<Lancamento> contasAPagar(Long usuarioId) {
        return lancamentoRepository.findByUsuario_IdAndTipoAndStatus(
                usuarioId,
                TipoLancamento.PAGAR,
                StatusLancamento.PENDENTE
        );
    }
    public List<Lancamento> contasAReceber(Long usuarioId) {
        return lancamentoRepository.findByUsuario_IdAndTipoAndStatus(
                usuarioId,
                TipoLancamento.RECEBER,
                StatusLancamento.PENDENTE
        );
    }
    public List<Lancamento> gerarExtrato(
            Long usuarioId,
            LocalDate inicio,
            LocalDate fim) {

        return lancamentoRepository
                .findByUsuario_IdAndTipoAndStatusAndDataVencimentoBetween(
                        usuarioId,
                        TipoLancamento.RECEBER,
                        StatusLancamento.BAIXADO,
                        inicio.atStartOfDay(),
                        fim.atTime(23, 59, 59)
                );
    }
    public PosicaoGeralDTO posicaoGeral(Long usuarioId) {

        BigDecimal totalReceber = lancamentoRepository
                .somarPorTipo(usuarioId, TipoLancamento.RECEBER);

        BigDecimal totalPagar = lancamentoRepository
                .somarPorTipo(usuarioId, TipoLancamento.PAGAR);

        BigDecimal saldo = totalReceber.subtract(totalPagar);

        return new PosicaoGeralDTO(totalReceber, totalPagar, saldo);
    }
}