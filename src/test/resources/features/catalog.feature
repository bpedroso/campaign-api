#Author: bruno_pedroso@carrefour.com
#Keywords Summary : catalog list complete

Feature: Listar Produtos
  Eu quero validar a consulta de produtos
  Background: 
    Given Que eu tenha o servico de lista de produtos UP 
      And Eu tenha uma lista com 10 produtos e 2 com data posterior a 2017-01-25

Scenario Outline: Servico de catalogo de produtos
Given Eu tenha na lista de produtos os campos id, creationtime_date, totalInStock_string
When Eu passar os parametros de query string <start>, <rows> e <date>
Then Eu recebo um json válido com <totalResponse> produtos

Examples:
    | start | rows |         date         | totalResponse |
    |   0   |  8   |                      |      8        |
    |   0   |  10  | 2015-01-30T00:00:00Z |      10       |
