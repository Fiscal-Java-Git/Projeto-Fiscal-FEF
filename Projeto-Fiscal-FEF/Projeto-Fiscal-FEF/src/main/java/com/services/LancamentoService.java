package com.services;

import com.domains.*;
import com.domains.dtos.LancamentoDTO;
import com.domains.enums.StatusLancamento;
import com.mappers.LancamentoMapper;
import com.repositories.*;
import com.services.exceptions.DataIntegrityException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LancamentoService {

    private final LancamentoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final ContaBancariaRepository contaRepository;
    private final CartaoCreditoRepository cartaoRepository;
    private final EntidadeRepository entidadeRepository;
    private final CentroCustoRepository centroRepository;

    public LancamentoService(
            LancamentoRepository repository,
            UsuarioRepository usuarioRepository,
            ContaBancariaRepository contaRepository,
            CartaoCreditoRepository cartaoRepository,
            EntidadeRepository entidadeRepository,
            CentroCustoRepository centroRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.contaRepository = contaRepository;
        this.cartaoRepository = cartaoRepository;
        this.entidadeRepository = entidadeRepository;
        this.centroRepository = centroRepository;
    }

    public List<LancamentoDTO> findAll() {
        return LancamentoMapper.toDtoList(repository.findAll());
    }

    public LancamentoDTO findById(Long id) {
        Lancamento lancamento = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Lançamento não encontrado: " + id));
        return LancamentoMapper.toDto(lancamento);
    }

    @Transactional
    public LancamentoDTO create(LancamentoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new DataIntegrityException("Usuário não encontrado: " + dto.getUsuarioId()));

        Lancamento lancamento = LancamentoMapper.toEntity(dto);
        lancamento.setUsuario(usuario);

        if (dto.getContaBancariaId() != null) {
            ContaBancaria conta = contaRepository.findById(dto.getContaBancariaId())
                    .orElseThrow(() -> new DataIntegrityException("Conta não encontrada: " + dto.getContaBancariaId()));
            lancamento.setContaBancaria(conta);
        }

        if (dto.getCartaoCreditoId() != null) {
            CartaoCredito cartao = cartaoRepository.findById(dto.getCartaoCreditoId())
                    .orElseThrow(() -> new DataIntegrityException("Cartão não encontrado: " + dto.getCartaoCreditoId()));
            lancamento.setCartaoCredito(cartao);
        }

        if (dto.getEntidadeId() != null) {
            Entidade entidade = entidadeRepository.findById(dto.getEntidadeId())
                    .orElseThrow(() -> new DataIntegrityException("Entidade não encontrada: " + dto.getEntidadeId()));
            lancamento.setEntidade(entidade);
        }

        if (dto.getCentroCustoId() != null) {
            CentroCusto centro = centroRepository.findById(dto.getCentroCustoId())
                    .orElseThrow(() -> new DataIntegrityException("Centro de Custo não encontrado: " + dto.getCentroCustoId()));
            lancamento.setCentroCusto(centro);
        }

        if (lancamento.getStatus() == null) {
            lancamento.setStatus(StatusLancamento.PENDENTE);
        }

        lancamento = repository.save(lancamento);
        return LancamentoMapper.toDto(lancamento);
    }

    @Transactional
    public LancamentoDTO update(Long id, LancamentoDTO dto) {
        Lancamento lancamento = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Lançamento não encontrado: " + id));

        LancamentoMapper.copyToEntity(dto, lancamento);

        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new DataIntegrityException("Usuário não encontrado: " + dto.getUsuarioId()));
            lancamento.setUsuario(usuario);
        }

        if (dto.getContaBancariaId() != null) {
            ContaBancaria conta = contaRepository.findById(dto.getContaBancariaId())
                    .orElseThrow(() -> new DataIntegrityException("Conta não encontrada: " + dto.getContaBancariaId()));
            lancamento.setContaBancaria(conta);
        }

        if (dto.getCartaoCreditoId() != null) {
            CartaoCredito cartao = cartaoRepository.findById(dto.getCartaoCreditoId())
                    .orElseThrow(() -> new DataIntegrityException("Cartão não encontrado: " + dto.getCartaoCreditoId()));
            lancamento.setCartaoCredito(cartao);
        }

        if (dto.getEntidadeId() != null) {
            Entidade entidade = entidadeRepository.findById(dto.getEntidadeId())
                    .orElseThrow(() -> new DataIntegrityException("Entidade não encontrada: " + dto.getEntidadeId()));
            lancamento.setEntidade(entidade);
        }

        if (dto.getCentroCustoId() != null) {
            CentroCusto centro = centroRepository.findById(dto.getCentroCustoId())
                    .orElseThrow(() -> new DataIntegrityException("Centro de Custo não encontrado: " + dto.getCentroCustoId()));
            lancamento.setCentroCusto(centro);
        }

        lancamento = repository.save(lancamento);
        return LancamentoMapper.toDto(lancamento);
    }

    @Transactional
    public void delete(Long id) {
        Lancamento lancamento = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Lançamento não encontrado: " + id));
        lancamento.setStatus(StatusLancamento.CANCELADO);
        repository.save(lancamento);
    }
    @Transactional
    public LancamentoDTO cancelar(Long id) {

        Lancamento l = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Lançamento não encontrado: " + id));

        if (l.getStatus() == StatusLancamento.CANCELADO) {
            throw new DataIntegrityException("O lançamento já está cancelado.");
        }

        l.setStatus(StatusLancamento.CANCELADO);
        l.setAtualizadoEm(LocalDateTime.now());

        repository.save(l);

        return LancamentoMapper.toDto(l);
    }


}
