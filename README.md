# Curso DS Java Spring Boot

## Quarto projeto:

#### Módulo: Back end
#### Capítulo: JPA, consultas SQL e JPQL

#### DESAFIO: Consulta vendas

**Competências obtidas no projeto:**
- Implementação de consultas customizadas com JPQL e parâmetros opcionais

- Uso de DTOs para retorno de dados específicos

- Paginação de resultados com Spring Data JPA

- Tratamento de datas com `LocalDate` e valores padrão

- Separação de responsabilidades entre Controller, Service e Repository
#

Trata-se de um sistema de vendas (Sale) e vendedores (Seller). Cada venda está associada a um vendedor, e um vendedor pode ter várias vendas.

#### Diagrama de classes:

| Sale | | Seller |
|---|---|---|
| id : Long | → seller (N:1) | id : Long |
| visited : Integer | | name : String |
| deals : Integer | | email : String |
| amount : Double | | phone : String |
| date : LocalDate | | |

#

### Endpoints implementados:

#### Relatório de vendas
`GET /sales/report`

Parâmetros opcionais: `minDate`, `maxDate`, `name`

Retorna listagem **paginada** contendo id, data, quantia vendida e nome do vendedor.

- Se `maxDate` não for informada, considera a data atual
- Se `minDate` não for informada, considera 1 ano antes da data final
- Se `name` não for informado, considera texto vazio (retorna todos)

#### Sumário de vendas por vendedor
`GET /sales/summary`

Parâmetros opcionais: `minDate`, `maxDate`

Retorna listagem com nome do vendedor e soma total de vendas no período.

- Mesmas regras de datas do relatório de vendas

#

#### Como o trabalho será corrigido?

1) Importação do projeto
O professor deverá ser capaz de fazer um simples clone do projeto Github, e importar e executar o mesmo na IDE sem necessidade de qualquer configuração especial diferente daquelas das aulas.

2) Testes manuais no Postman

**Sumário de vendas por vendedor (teste 1)**
```
GET /sales/summary?minDate=2022-01-01&maxDate=2022-06-30
```

**Sumário de vendas por vendedor (teste 2)**
```
GET /sales/summary
```
Retorna o sumário dos últimos 12 meses.

**Relatório de vendas (teste 1)**
```
GET /sales/report
```
Retorna o relatório dos últimos 12 meses.

**Relatório de vendas (teste 2)**
```
GET /sales/report?minDate=2022-05-01&maxDate=2022-05-31&name=odinson
```
