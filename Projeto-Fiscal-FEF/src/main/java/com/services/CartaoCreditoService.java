package com.services;

import com.projetofef.domains.CartaoCredito;
import com.projetofef.domains.Usuario;
import com.projetofef.domains.dtos.CartaoCreditoDTO;
import com.projetofef.mappers.CartaoCreditoMapper;
import com.projetofef.repositories.CartaoCreditoRepository;
import com.projetofef.repositories.UsuarioRepository;
import com.projetofef.services.exceptions.DataIntegrityException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartaoCreditoService {

    private final CartaoCreditoRepository repository;
    private final UsuarioRepository usuarioRepository;

    public CartaoCreditoService(CartaoCreditoRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<CartaoCreditoDTO> findAll() {
        return CartaoCreditoMapper.toDtoList(repository.findAll());
    }

    public CartaoCreditoDTO findById(Long id) {
        CartaoCredito cartao = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Cartão não encontrado: " + id));
        return CartaoCreditoMapper.toDto(cartao);
    }

    @Transactional
    public CartaoCreditoDTO create(CartaoCreditoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new DataIntegrityException("Usuário não encontrado: " + dto.getUsuarioId()));
        CartaoCredito cartao = CartaoCreditoMapper.toEntity(dto, usuario);
        cartao = repository.save(cartao);
        return CartaoCreditoMapper.toDto(cartao);
    }

    @Transactional
    public CartaoCreditoDTO update(Long id, CartaoCreditoDTO dto) {
        CartaoCredito cartao = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Cartão não encontrado: " + id));
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new DataIntegrityException("Usuário não encontrado: " + dto.getUsuarioId()));
        CartaoCreditoMapper.copyToEntity(dto, cartao, usuario);
        cartao = repository.save(cartao);
        return CartaoCreditoMapper.toDto(cartao);
    }

    @Transactional
    public void delete(Long id) {
        CartaoCredito cartao = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Cartão não encontrado: " + id));
        cartao.setAtivo(false);
        repository.save(cartao);
    }
}
