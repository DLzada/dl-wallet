# DL Wallet - Gerenciador Financeiro Pessoal

##  Visão Geral - O Problema Real

Muitas pessoas perdem o controle de suas finanças por não terem uma forma simples e centralizada de registrar entradas e saídas. O **DL Wallet** resolve o problema da falta de visibilidade financeira, permitindo que o usuário tenha um histórico claro de suas transações e, futuramente, uma visão consolidada de seu saldo acumulado.

---
## Arquitetura do Sistema
O projeto segue uma estrutura de **Monolito Containerizado**, dividida em camadas para facilitar a manutenção e escalabilidade:

1.  **Client/User**: Interface que consome a API (Postman, Frontend ou Mobile).
2.  **Controller**: Camada de entrada que gerencia as requisições REST.
3.  **Service**: Onde reside a inteligência do negócio (regras de cálculo e validações).
4.  **Repository**: Interface de comunicação com o banco de dados via JPA/Hibernate.
5.  **PostgreSQL**: Banco de dados relacional rodando em um container isolado.