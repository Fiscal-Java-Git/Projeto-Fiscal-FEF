package com.services;

import com.domains.*;
import com.domains.enums.*;
import com.repositories.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DBService {

    private final UsuarioRepository usuarioRepo;
    private final CentroCustoRepository centroRepo;
    private final ContaBancariaRepository contaRepo;
    private final EntidadeRepository entidadeRepo;
    private final CartaoCreditoRepository cartaoRepo;
    private final FaturaCartaoRepository faturaRepo;
    private final LancamentoRepository lancamentoRepo;
    private final MovimentoContaRepository movimentoRepo;
    private final PagamentoRepository pagamentoRepo;
    private final RecebimentoRepository recebimentoRepo;
    private final TransferenciaRepository transferenciaRepo;

    public DBService(UsuarioRepository usuarioRepo,
                     CentroCustoRepository centroRepo,
                     ContaBancariaRepository contaRepo,
                     EntidadeRepository entidadeRepo,
                     CartaoCreditoRepository cartaoRepo,
                     FaturaCartaoRepository faturaRepo,
                     LancamentoRepository lancamentoRepo,
                     MovimentoContaRepository movimentoRepo,
                     PagamentoRepository pagamentoRepo,
                     RecebimentoRepository recebimentoRepo,
                     TransferenciaRepository transferenciaRepo) {
        this.usuarioRepo = usuarioRepo;
        this.centroRepo = centroRepo;
        this.contaRepo = contaRepo;
        this.entidadeRepo = entidadeRepo;
        this.cartaoRepo = cartaoRepo;
        this.faturaRepo = faturaRepo;
        this.lancamentoRepo = lancamentoRepo;
        this.movimentoRepo = movimentoRepo;
        this.pagamentoRepo = pagamentoRepo;
        this.recebimentoRepo = recebimentoRepo;
        this.transferenciaRepo = transferenciaRepo;
    }

    public void initDB() {

        Usuario u1 = new Usuario();
        u1.setNome("Rian Araújo");
        u1.setEmail("rian@email.com");
        usuarioRepo.save(u1);

        Usuario u2 = new Usuario();
        u2.setNome("Gustavo Henrique");
        u2.setEmail("gustavo@email.com");
        usuarioRepo.save(u2);

        Usuario u3 = new Usuario();
        u3.setNome("Paulo Candido");
        u3.setEmail("paulo@email.com");
        usuarioRepo.save(u3);

        CentroCusto c1 = new CentroCusto();
        c1.setUsuario(u1);
        c1.setNome("Alimentação");
        c1.setCodigo("ALIM");
        c1.setAtivo(true);
        centroRepo.save(c1);

        CentroCusto c2 = new CentroCusto();
        c2.setUsuario(u1);
        c2.setNome("Transporte");
        c2.setCodigo("TRAN");
        c2.setAtivo(true);
        centroRepo.save(c2);

        ContaBancaria conta1 = new ContaBancaria();
        conta1.setUsuario(u1);
        conta1.setInstituicao("Banco do Brasil");
        conta1.setAgencia("1234");
        conta1.setNumero("000111");
        conta1.setApelido("Conta Principal");
        conta1.setSaldoInicial(new BigDecimal("1000.00"));
        conta1.setDataSaldoInicial(LocalDateTime.now());
        conta1.setAtiva(true);
        contaRepo.save(conta1);

        ContaBancaria conta2 = new ContaBancaria();
        conta2.setUsuario(u1);
        conta2.setInstituicao("Nubank");
        conta2.setAgencia("5678");
        conta2.setNumero("000222");
        conta2.setApelido("Conta Secundária");
        conta2.setSaldoInicial(new BigDecimal("500.00"));
        conta2.setDataSaldoInicial(LocalDateTime.now());
        conta2.setAtiva(true);
        contaRepo.save(conta2);

        Entidade e1 = new Entidade();
        e1.setUsuario(u1);
        e1.setNome("Supermercado X");
        e1.setDocumento("12345678901");
        e1.setTipo("FORNECEDOR");
        e1.setAtivo(true);
        entidadeRepo.save(e1);

        Entidade e2 = new Entidade();
        e2.setUsuario(u1);
        e2.setNome("Empresa Y");
        e2.setDocumento("98765432100");
        e2.setTipo("CLIENTE");
        e2.setAtivo(true);
        entidadeRepo.save(e2);

        CartaoCredito cartao1 = new CartaoCredito();
        cartao1.setUsuario(u1);
        cartao1.setBandeira("VISA");
        cartao1.setEmissor("Banco do Brasil");
        cartao1.setApelido("Meu Visa");
        cartao1.setFechamentoFaturaDia(10);
        cartao1.setVencimentoFaturaDia(20);
        cartao1.setAtivo(true);
        cartaoRepo.save(cartao1);

        CartaoCredito cartao2 = new CartaoCredito();
        cartao2.setUsuario(u1);
        cartao2.setBandeira("MASTERCARD");
        cartao2.setEmissor("Nubank");
        cartao2.setApelido("Meu Master");
        cartao2.setFechamentoFaturaDia(5);
        cartao2.setVencimentoFaturaDia(15);
        cartao2.setAtivo(true);
        cartaoRepo.save(cartao2);


        FaturaCartao f1 = new FaturaCartao();
        f1.setCartao(cartao1);
        f1.setCompetencia(LocalDate.now());
        f1.setDataFechamento(LocalDate.now().plusDays(10).atStartOfDay());
        f1.setDataVencimento(LocalDate.now().plusDays(20).atStartOfDay());
        f1.setValorTotal(new BigDecimal("500.00"));
        f1.setStatus(StatusFatura.ABERTA);
        faturaRepo.save(f1);

        FaturaCartao f2 = new FaturaCartao();
        f2.setCartao(cartao2);
        f2.setCompetencia(LocalDate.now());
        f2.setDataFechamento(LocalDate.now().plusDays(5).atStartOfDay());
        f2.setDataVencimento(LocalDate.now().plusDays(15).atStartOfDay());
        f2.setValorTotal(new BigDecimal("300.00"));
        f2.setStatus(StatusFatura.ABERTA);
        faturaRepo.save(f2);

        Lancamento l1 = new Lancamento();
        l1.setTipo(TipoLancamento.PAGAR);
        l1.setDescricao("Compra Supermercado");
        l1.setUsuario(u1);
        l1.setEntidade(e1);
        l1.setCentroCusto(c1);
        l1.setValor(new BigDecimal("150.00"));
        l1.setDataCompetencia(LocalDateTime.now());
        l1.setDataVencimento(LocalDateTime.now().plusDays(5));
        l1.setMeioPagamento(MeioPagamento.DINHEIRO);
        l1.setContaBancaria(conta1);
        l1.setStatus(StatusLancamento.PENDENTE);
        l1.setValorBaixado(BigDecimal.ZERO);
        lancamentoRepo.save(l1);

        Lancamento l2 = new Lancamento();
        l2.setTipo(TipoLancamento.RECEBER);
        l2.setDescricao("Serviço Empresa Y");
        l2.setUsuario(u1);
        l2.setEntidade(e2);
        l2.setCentroCusto(c2);
        l2.setValor(new BigDecimal("400.00"));
        l2.setDataCompetencia(LocalDateTime.now());
        l2.setDataVencimento(LocalDateTime.now().plusDays(10));
        l2.setMeioPagamento(MeioPagamento.CONTA);
        l2.setContaBancaria(conta2);
        l2.setStatus(StatusLancamento.PENDENTE);
        l2.setValorBaixado(BigDecimal.ZERO);
        lancamentoRepo.save(l2);

        MovimentoConta m1 = new MovimentoConta();
        m1.setConta(conta1);
        m1.setTipo(TipoTransacao.DEBITO);
        m1.setValor(new BigDecimal("150.00"));
        m1.setHistorico("Pagamento Supermercado");
        m1.setReferenciaId(l1.getId());
        m1.setReferenciaTipo("LANCAMENTO");
        m1.setDataMovimento(LocalDateTime.now());
        movimentoRepo.save(m1);

        MovimentoConta m2 = new MovimentoConta();
        m2.setConta(conta2);
        m2.setTipo(TipoTransacao.CREDITO);
        m2.setValor(new BigDecimal("400.00"));
        m2.setHistorico("Recebimento Serviço");
        m2.setReferenciaId(l2.getId());
        m2.setReferenciaTipo("LANCAMENTO");
        m2.setDataMovimento(LocalDateTime.now());
        movimentoRepo.save(m2);

        Pagamento p1 = new Pagamento();
        p1.setLancamento(l1);
        p1.setContaOrigem(conta1);
        p1.setDataPagamento(LocalDateTime.now());
        p1.setValorPago(new BigDecimal("150.00"));
        p1.setObservacao("Pagamento feito em dinheiro");
        pagamentoRepo.save(p1);

        Recebimento r1 = new Recebimento();
        r1.setLancamento(l2);
        r1.setContaDestino(conta2);
        r1.setDataRecebimento(LocalDate.now());
        r1.setValor(new BigDecimal("400.00"));
        r1.setObservacao("Recebimento via transferência");
        recebimentoRepo.save(r1);

        Transferencia t1 = new Transferencia();
        t1.setUsuario(u1);
        t1.setContaOrigem(conta1);
        t1.setContaDestino(conta2);
        t1.setData(LocalDateTime.now());
        t1.setValor(new BigDecimal("200.00"));
        t1.setObservacao("Transferência entre contas");
        transferenciaRepo.save(t1);

    }
}
