📊 Sistema de Controle Financeiro Pessoal

Este projeto é um sistema completo para gestão de finanças pessoais, permitindo ao usuário controlar contas bancárias, cartões de crédito, lançamentos financeiros, pagamentos, transferências e extratos detalhados.
O foco é oferecer organização, previsibilidade e rastreabilidade contábil, com base em regras financeiras reais.

🚀 Tecnologias Utilizadas

Java 17+

Spring Boot (Web, Data JPA, Validation, Security)

PostgreSQL

JPA / Hibernate

Maven

Arquitetura RESTful

🧠 Funcionalidades Principais
🔐 Usuários

Gestão de dados financeiros segregados por usuário

Controle de auditoria (criadoEm, atualizadoEm)

🏦 Contas Bancárias

Cadastro, ativação/inativação e atualização

Movimentação automática através de pagamentos, recebimentos e transferências

Consulta de extratos contábeis e projetados

💳 Cartões de Crédito

Cadastro de cartões

Criação automática de faturas por competência

Regras reais: fechamento bloqueia lançamentos, pagamentos debitam da conta

📄 Lançamentos Financeiros

Contas a pagar e a receber

Classificação via Entidades e Centros de Custo

Controle de status: PENDENTE, BAIXADO, PARCIAL, CANCELADO

💸 Pagamentos e Recebimentos

Baixa total ou parcial

Geração automática de MovimentoConta (débito/crédito)

Atualização automática de status e valores baixados

🔁 Transferências

Débito + crédito atômicos entre contas do mesmo usuário

Validações de integridade (não permite origem=destino, valor > 0)

📊 Extratos e Relatórios

Extrato contábil real (baseado em movimentos)

Extrato projetado (prevê fluxo de caixa futuro)

Relatórios de:

Contas a pagar

Contas a receber

Posição consolidada geral

🧱 Modelagem do Domínio

A documentação inclui:

Módulos principais do domínio (Usuário, Contas, Lançamentos, Cartões, etc.)

Tabela completa de relacionamentos com cardinalidade, agregação, composição e regras de ciclo de vida

Enumerações:

TipoLancamento

StatusLancamento

MeioPagamento

TipoTransacao

StatusFatura

(Detalhes completos estão no PDF de análise do domínio) 

analise

🌐 API REST – Endpoints Principais

Contas Bancárias

POST   /api/v1/contas
GET    /api/v1/contas
GET    /api/v1/contas/{id}
PUT    /api/v1/contas/{id}
DELETE /api/v1/contas/{id}
GET    /api/v1/contas/{id}/extrato?inicio&fim&modo=

Cartões e Faturas

POST /api/v1/cartoes
GET  /api/v1/cartoes
GET  /api/v1/cartoes/{id}/faturas
POST /api/v1/cartoes/{id}/faturas/fechamento
POST /api/v1/cartoes/{id}/faturas/{faturaId}/pagar

Lançamentos

POST /api/v1/lancamentos
GET  /api/v1/lancamentos?tipo&status&inicio&fim
GET  /api/v1/lancamentos/{id}
PUT  /api/v1/lancamentos/{id}
POST /api/v1/lancamentos/{id}/cancelar

Pagamentos / Recebimentos

POST /api/v1/lancamentos/{id}/pagamentos
POST /api/v1/lancamentos/{id}/recebimentos

Transferências

POST /api/v1/transferencias
GET  /api/v1/transferencias?inicio&fim&contaId

Relatórios

GET /api/v1/relatorios/extrato
GET /api/v1/relatorios/contas-a-pagar
GET /api/v1/relatorios/contas-a-receber
GET /api/v1/relatorios/posicao-geral

⚙️ Regras de Negócio Implementadas

Baixas atualizam status e valores baixados

Movimentações contábeis automáticas

Bloqueio de lançamentos em faturas fechadas

Contas inativas não podem movimentar

Transferências são transações atômicas

Conciliação via MovimentoConta

Extrato projetado combina dados reais + previstos

🛠️ Como Executar o Projeto

Instale:

Java 17+

PostgreSQL

Maven

Configure o banco no application.properties

Execute:

mvn spring-boot:run


Acesse a API:

http://localhost:8080/api/v1

📌 Status do Projeto

✔️ Em desenvolvimento (back-end estruturado e documentado)
✔️ Domínio completo e regras de negócio definidas
⬜ Front-end ainda não implementado (se aplicável)

🤝 Contribuições

Pull requests e sugestões são bem-vindos!
