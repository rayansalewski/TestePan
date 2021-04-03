# Customer Service

### Requirements

1. JDK 8
1. Maven 4
1. Framework: Spring Boot
1. Testes unitários: Junit
1. Mock: Mockito
1. Database em memória

### Running

1. Clone : `https://github.com/rayansalewski/TestePan.git`
1. Go to the folder `TestePan` and execute: `mvn spring-boot:run`

### Request methods

| Command                       | URI                                                | JSON                                                                                                                            |
| ----------------------------- | -------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------- |
| `Listar todos os cliente`     | GET http://localhost:8080/clientes/                | NA                                                                                                                              |
| `Consultar clientes pelo CPF` | GET http://localhost:8080/clientes/{cpf}           | NA                                                                                                                              |
| `Altera endereço do cliente`  | PUT http://localhost:8080/clientes/{cpf}/enderecos | {"logradouro": "Rua Almirante","numero": 30,"bairro": "Residencial Cumbica","cidade": "Guarulhos","uf": "SP","cep": "07174470"} |
| `Consultar endereço pelo CEP` | GET http://localhost:8080/enderecos/{cep}          | NA                                                                                                                              |
| `Consultar Municipio`         | GET http://localhost:8080/estados/{uf}             | NA                                                                                                                              |
| `Consultar Estados`           | GET http://localhost:8080/estados/                 | NA                                                                                                                              |
