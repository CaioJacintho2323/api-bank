
## MVP de Banco

API RESTful desenvolvida em Java com Spring Boot para gerenciar transações financeiras entre usuários.

## 🚀 Tecnologias

- Java 22
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- Jakarta Bean Validation


## 📦 Como rodar o projeto
    Clone o repositório:
    - https://github.com/CaioJacintho2323/api-bank.git
    
    Definir a variaveis de ambiente: 
    - Abrir o arquivo .envTemplate, Nomear para .env e atribuir as variaveis

    Rodar comando Docker na raiz do projeto:  
    - docker compose up -d



## Variáveis de Ambiente

Para que o projeto funcione corretamente, é necessário configurar as variáveis de ambiente listadas abaixo no arquivo .env, localizado na raiz do projeto e na pasta resources.

`ENV_ROOT_USER`
`ENV_ROOT_PASSWORD`
`ENV_MYSQL_USER`
`ENV_MYSQL_PASSWORD`



## Funcionalidades

- BankAccount
    - Buscar todos : exemplo: http://localhost:8080/v1/bank/all
    - Buscar por id : exemplo: http://localhost:8080/v1/bank/13
    - Buscar por agência : exemplo: http://localhost:8080/v1/bank/agency/0001
    - Buscar por documento : exemplo: http://localhost:8080/v1/bank/document/07447286575
    - Cadastrar conta bancária 
    - Atualizar conta bancária
- Balance
    - Buscar todos : exemplo: http://localhost:8080/v1/balance/all
    - Buscar por id : exemplo: http://localhost:8080/v1/bank/13
    
- Transaction
    - Cadastrar transação bancária
    - Buscar por documento(ordenado por ordem da última transação ) : exemplo: http://localhost:8080/v1/transaction/document/07447286575
    - Buscar por id : exemplo: http://localhost:8080/v1/transaction/id/13
    - Buscar todos(ordenado por ordem da última transação )  : exemplo: http://localhost:8080/v1/transaction/all

  
## Payload

    -bankAccount(POST)

    {
        "branch": "001",
        "number": "1222",
        "type": "PAYMENT",
        "holderName": "Wesley Alves",
        "holderEmail": "wesleyalves@example.com",
        "holderDocument": "12345678910",
        "holderType": "PERSON"
    }

    -bankAccount(PUT)

    {
        "id": 13,
        "status": "ACTIVE",
        "holderEmail":"caio.tavares@example.com"
    }

    -Transaction(POST)

    {
    "type": "DEBIT",
    "amount": 1.00,
    "bankAccountId": 13,
    "counterpartyBankName": "Banco Delbank",
    "counterpartyBranch": "0001",
    "counterpartyAccountNumber": "68105",
    "counterpartyAccountType": "PAYMENT",
    "counterpartyHolderName": "Caio Jacintho",
    "counterpartyHolderType": "COMPANY",
    "counterpartyHolderDocument": "07447286575"
    }



