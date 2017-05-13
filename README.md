# campaign-api
Serviço de Campanha

## Code Architecture

http://blog.cleancoder.com/uncle-bob/2016/01/04/ALittleArchitecture.html
  -> https://martinfowler.com/eaaCatalog/repository.html

## Status Utilizados nos controllers

https://httpstatuses.com/

  - 200 OK
  - 204 No Content
  - 500 Internal Server Error

### Repository
Optei trabalhar com GemFire por ser um NoSQL que consigo trabalhar tanto com a instalação deste NoSQL quanto com a subida dele em memória (embedado).
 -> NoSQL - https://pivotal.io/pivotal-gemfire

Members in your GemFire distributed system receive cache updates from other members through cache events.

Obs.: A outra opção seria DynamoDB que nos provê a mesma funcionalidade que acelera o desenvolvimento (é muito melhor), porém para subir ele embeddado, na primeira execução o maven/gradle teria que baixar quase 500Mb para começarem a brincar com o serviço.

