# SMILE-GO

## Sobre o Projeto

Explique aqui em mais detalhes o propósito do projeto, os problemas que ele resolve e por que ele foi criado.

### Tecnologias Utilizadas
- [Java 23](https://openjdk.org/projects/jdk/23/)
- [Spring Framework](https://spring.io/)
- PostgreSQL, RabbitMQ, Redis, Resilience4J, H2 para testes

### Checklist de itens do desafio

- ✅ **Criação da API**
- ❌ **Autenticação**
- ✅ **CRUD de Assinaturas e Pagamentos**
- ✅ **Api externa de pagamentos (fiz o mock da maneira mais simples, a ideia era utilizar WireMock)**
- ✅ **Utilização de Retry e Circuit Break**
- ✅ **Utilização de Cache**
- ✅ **Criação de Relatórios**
- ✅ **Testes unitários**
- ✅ **Testes de integração (Não fiz de todas as classes)**
- ✅ **Documentação Swagger**

## Setup

Para executar o projeto, você precisa de docker instalado na sua máquina e rodar o seguinte comando abaixo:

### MacOS/Linux

Se for a primeira vez (o first_setup já irá inicializar os containers)
```bash
make first_setup
```

Se for a segunda vez
```bash
make start
```

### Windows

Primeiro rode o seguinte comando
```bash
docker compose build --no-cache
```

Depois rode o seguinte comando
```bash
docker compose up --build 
```

## Comentários

Fiz uso da clean architeture de forma que uso no dia a dia, utilizando adapters em toda a camada de infra, mas utilizando
casos de uso da forma convecional, infelizmente pelo tempo não consegui implementar o spring security, nem fazer o plus, que 
seria implementar Strategy na atualização de pagamentos, para que a depender do status do pagamento, a assinatura seria atualizada.
Tentei focar mais em mostrar conhecimento de arquitetura, conteinerização, testes, se o prazo for aumentado, farei de tudo para
entregar todos os requesitos. No mais foi um prazer fazer esse teste, ele me ajudou a reaprender o javinha que fazia bastante 
tempo que não pegava, obrigado pela oportunidade.



