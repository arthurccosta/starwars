# PROVA TÉCNICA - B2W - STAR WARS

## Sumário

 <ol>
  <li><a href="#Tecnologias">Tecnologias utilizadas</a></li>
  <li><a href="#Start">Iniciando a Aplicação/a></li>
  <li><a href="#Features">Funcionalidades</a>
    <ol>
      <li><a href="#Add">Adicionar um planeta</a></li>
      <li><a href="#FindAll">Listar planetas do banco de dados</a></li>
      <li><a href="#FindAllSwapi">Listar planetas da API do Star Wars</a></li>
      <li><a href="#FindById">Buscar por ID no banco de dados</a></li>
      <li><a href="#FindByName">Buscar por nome no banco de dados</a></li>
      <li><a href="#deleta">Deletando um planeta</a></li>
    </ol>
  </li>
</ol> 

<dl>
  

### <a name="Tecnologias">Tecnologias utilizadas</a> 
&nbsp;&nbsp;&nbsp;&nbsp;Java 8, Spring Boot 2.1.4, MongoDB, JUnit e Redis.

### <a name="Start">Iniciando a Aplicação</a>  
&nbsp;&nbsp;&nbsp;&nbsp;Para utilizar deverá ter o Docker e o Java SDK 8 instalados e executar os seguintes comandos dentro da raiz do projeto:
```
./start-docker-compose.sh up 
```
```
mvn spring-boot:run
```

Assim a aplicação estará rodando. O script do docker apenas sobe o MongoDb e o Redis.


### <a name="Features">Funcionalidades</a>

#### <a name="Add"> Adicionar um planeta:</a>  

&nbsp;&nbsp;&nbsp;&nbsp;Para adicionar um planeta deve ser feita uma requisição post em json para o endpoint "/planets".

&nbsp;&nbsp;&nbsp;&nbsp;Ex:
POST http://localhost:8080/planets
```JSON
{
	"name":"planet",
	"climate":"climate",
	"terrain": "terrain"
}
```
&nbsp;&nbsp;&nbsp;&nbsp; O objeto criado será retornado junto com o id criado. Um planete cujo nome não aparece no universo do Star Wars é criado com uma quantidade de aparições igual a 0.



#### <a name="FindAll">Listar planetas do banco de dados:</a>

&nbsp;&nbsp;&nbsp;&nbsp;Fazer uma solicitação get para o endpoint "/planet".

&nbsp;&nbsp;&nbsp;&nbsp;Ex:
GET http://localhost:8080/planet

&nbsp;&nbsp;&nbsp;&nbsp;Será retornado todos os planetas inseridos no banco de dados. Possuindo id, nome, clima, terreno e quantidade de aparições. 

#### <a name="FindAllSwapi">Listar planetas da API do Star Wars:</a>

&nbsp;&nbsp;&nbsp;&nbsp;Fazer uma solicitação get para o endpoint "/planet/findAll/swapi".

&nbsp;&nbsp;&nbsp;&nbsp;Ex:
GET http://localhost:8080/planet/findAll/swapi

&nbsp;&nbsp;&nbsp;&nbsp;Será retornado todos os planetas inseridos na Api do Star Wars com todas as suas informações. 


#### <a name="FindById">Buscar por ID no banco de dados:</a>

&nbsp;&nbsp;&nbsp;&nbsp;Fazer uma  uma solicitação get para o endpoint "/planet/{ID}", substituir {ID} pelo ID que deseja buscar. 

&nbsp;&nbsp;&nbsp;&nbsp;Ex:
GET http://localhost:8080/planet/{ID}

&nbsp;&nbsp;&nbsp;&nbsp;Caso seja inserida uma id inválida, será retornado o erro 404 não encontrado. 

#### <a name="FindByName">Buscar por nome no banco de dados:</a>

&nbsp;&nbsp;&nbsp;&nbsp;Fazer uma solicitação get para o endpoint "/planet/findByName?name={NOME}", substituir {NOME} pelo nome que deseja buscar.

&nbsp;&nbsp;&nbsp;&nbsp;Ex:
GET http://localhost:8080/planet/findByName?name={NOME}

#### <a name="deleta">Remover planeta:</a>

&nbsp;&nbsp;&nbsp;&nbsp;Fazer uma solicitação delete para o endpoint "/planet/{ID}" indicando a ID do planeta no final do endpoint.

&nbsp;&nbsp;&nbsp;&nbsp;Ex: 
DELETE http://localhost:8080/planetas/1
