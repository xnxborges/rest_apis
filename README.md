# CRUD
## Conceitos de CRUD aplicados a API's  

Projeto com conceitos práticos de como criar um CRUD (Creat, Read, Updatte and Delete), utilizando endpoints e gravando os dados dos usuários no bance de dados

## Tecnologias usasdas:  
Java 11  
Springboot   
MySQL   

## Executando a aplicação  
Crie uma pasta em seu computador e faça o clone do projeto.  
Na psta criada, abra o CMD e digite o comando: git clone https://github.com/xnxborges/CRUD.git  
Abra o projeto na sua IDE de preferencia e importe os repositórios Maven. Depois de importados clique com o botão direito sobre a pasta do projeto e selecione o comando "RUN".  
Aguarde até que o SpringBoot suba a aplicação.  

## Fazendo conexão com o banco de dados MySQL  
No diretório src/main/resources -> application.properties, estão todos os comando para que a aplicação faça conexão com obanco de dados, caso precise aletre as informações conforme as características do seu banco.  
Abra o MySQL e cria uma database com as informações contidas no arquivo application.properties e a conexão estará pronta. 
Ao subir a aplicação automaticamente o SpringBoot criará a tabela e adicionará informações nela.  

## Visualizando os dados contidos na tabela
No browser cole o endpoint editado para visualazação de todos os dados do tipo GET.   
GET    
http://localhost:8080/person/  

## Fazendo requisições com o Postman  

POST    
http://localhost:8080/person/  

Abra o Postaman, cole o endpoint acima e verá a mesma informaçõa da tabela mostada no browser. 
Para adicionar novos dados a tabela do banco de dados, selecione a requisição do tipo POST no canto superior esquerdo, clique em "body" -> raw e selecione a propriedade JSON.  
Cole  o objeto: 
´    {
        "fistName": "Geleia",
        "lastName": "Borges",
        "adress": "Cotia - Brazil",
        "gender": "M"
    }´  
Iclua os dados do objeto conforme deseje fazer a requisição do tipo POST no banco e clique em "Send".  

 PUT  
http://localhost:8080/person/

Para alterar um objeto já existente utilize a requisição PUT  
Siga os passoa: Requisição GET para vizualizar os dados existentes, escolha um, copie, edite, selecione a requisição do tipo POST e clique em SEND. 


DELETE  
http://localhost:8080/person/{id}

Para deletar um objeto utilize o endpoint acima e altere o "{id}" pelo número do id que deseja deletar e clique em SEND.  
O Post retornará um lista vazia com a nformação de "Status 200 ok"







