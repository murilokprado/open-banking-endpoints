![](src/main/resources/static/src/main/resources/static/logos/logo.png)

[Read this page in English](en-us/README.md)

# HUB Openbanking OS (Documentações, informações & API endpoints) [![Build Status](https://travis-ci.com/murilokprado/open-banking-endpoints.svg?branch=main)](https://travis-ci.com/murilokprado/open-banking-endpoints)
O Objetivo deste repositório é centralizar as informações de API para facilitar a vida dos desenvolvedores em sua busca pela informação de endpoints.

De um dev para outro.

Lembrando que estamos em construção e provavelmente não teremos todos endpoints ainda, por isso é de mega importância a contribuição da comunidade :construction_worker:

## Documentação oficial do Open Banking Brasil
[Open Banking Brasil](https://openbanking-brasil.github.io/areadesenvolvedor)

## APIs para desenvolvedores das companias
|  | Nome |  API desenvolvedores
| ------ | ------ | ----- |
| <img src="src/main/resources/static/logos/bb-favicon.png" width="15"> | Banco do Brasil | [Developers API](https://apoio.developers.bb.com.br/referency)
| <img src="src/main/resources/static/logos/banrisul-favicon.png" width="15"> | Banrisul | [Developers API](https://developers.banrisul.com.br/pages/allApis.html#openbanking)
| <img src="src/main/resources/static/logos/bradesco-favicon.ico" width="15"> | Bradesco | [Developers API](https://developers.bradesco.com.br/)
| <img src="src/main/resources/static/logos/caixa-favicon.ico" width="15"> | Caixa | [Developers API](https://desenvolvedores.caixa.gov.br/api-catalog)
| <img src="src/main/resources/static/logos/itau-favicon.ico" width="15"> | Itaú | [Developers API](https://developer.itau.com.br/api-catalog/openbanking)
| <img src="src/main/resources/static/logos/santander-favicon.png" width="15"> | Santander | [Developers API](https://www.santander.pt/open-banking/developers#)

## Endpoints
Aqui você irá encontrar os endpoints da instituições financeiras referentes ao openbanking.

### Produtos e serviços

#### Empréstimos pessoais
|  | Empréstimos pessoais link
| ------ | ------ | 
| <img src="src/main/resources/static/logos/bb-favicon.png" width="15" title="Banco do Brasil"> | [link to json](https://opendata.api.bb.com.br/open-banking/products-services/v1/personal-loans)
| <img src="src/main/resources/static/logos/banrisul-favicon.png" width="15" title="Banrisul"> | [link to json](https://openbanking.banrisul.com.br/open-banking/products-services/v1/personal-loans)
| <img src="src/main/resources/static/logos/bradesco-favicon.ico" width="15" title="Bradesco"> | [link to json](https://api.bradesco.com/bradesco/open-banking/products-services/v1/personal-loans)
| <img src="src/main/resources/static/logos/caixa-favicon.ico" width="15" title="Caixa"> | [empty](empty)
| <img src="src/main/resources/static/logos/itau-favicon.ico" width="15" title="Itaú"> | [link to json](https://api.itau/open-banking/products-services/v1/personal-loans)
| <img src="src/main/resources/static/logos/santander-favicon.png" width="15" title="Santander"> | [empty](empty)

## Contribuição
Para melhorarmos a informação gostaria de contar com a contribuição da comunidade, assim podemos criar um ambiente onde irá facilitar o nosso dia-a-dia como desenvolvedores e irá estimular a disseminação do conhecimento. Sinta-se livre para contribuir e ajudar, é isso que mantêm nossa comunidade em crescimento!

## Execução local :lock:
Para rodar a aplicação basta ter instalado o Java 11, maven e o Docker.

Comando:
`./runner.sh`

Este comando irá compilar e criar a imagem docker e startar o container.

## Documentação da API :memo:
Está sendo utilizado o Swagger para documentar esta API. 
Para acessar, suba o servidor e acesse o link [localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## GraphQLAPI
Neste projeto foi implementado o GraphQL para facilitar o acesso das informação à um possível frontend.
Está disponível um arquivo [insomnia.json](insomnia.json), onde é possível efetuar o upload das requests ao 
[Insomnia](https://insomnia.rest/download/).

## Acessando a aplicação publicada :globe_with_meridians:
[Swagger on Heroku](https://openbanking-br.herokuapp.com/swagger-ui/index.html#/participants-controller/getParticipantsUsingGET)

## Licença
MIT 
