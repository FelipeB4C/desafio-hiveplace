# Desafio HIVEPlace + BBTS

## Descrição
Esse projeto faz parte do desafio HIVEPlace + BBTS e tem como objetivo criar uma API REST utilizando WebFlux e MongoDB, 
além de utilizar algumas tecnologias AWS como as Lambdas, para a execução de funções, e o bucket S3 para anexo de 
arquivos como imagens, pdf e etc...

# inserir imagem do UML

![uml](https://github.com/FelipeB4C/desafio-hiveplace/assets/39536596/03500310-380b-4f9d-ad7a-e650d5e4950e)

Nosso projeto é bastante simples, iremos cadastrar tarefas onde vão ter informações como: nome, descrição, 
status (para saber se já foi concluída ou não), prioridade (para indicar se aquela tarefa precisa de atenção urgente ou não),
data de previsão de finalizar a tarefa e um campos para uma lista de anexos que possam contribuir para a tarefa, como textos, imagens, vídeos e etc.

# INSERIR IMAGEM DO LAMBDA
Na AWS foi criado um bucket S3 para armazenamento dos anexos e também uma Lambda que executa uma função que recebe quantos
dias faltam para completar aquela tarefa e, de acordo com a quantidade, ela muda o status de prioridade da Tarefa 
para prioridade BAIXA, MEDIA ou ALTA. Essa função é executada, através de um Trigger, quando for cadastrada uma nova Tarefa.

## Requisitos para rodar a aplicação
### Serão necessários:
#### Docker: para iniciar o container da nossa aplicação spring e do banco de dados
#### Postman ou Insomnia: para fazer as requisições para o nosso backend

## Comando para rodar o container da aplicação no Docker
    Baixe o projeto do GitHub
    Com o projeto baixado e extraído
    Entre na pasta do projeto até chegar onde tem os arquivos Dockerfile e docker-compose
    Após isso utilize o seu bash de preferênci para poder rodar o seguinte comando dentro dessa pasta

    docker build -t challenge .
    docker-compose up -d

    Isso fará que seja gerado um container no seu docker com as imagens da nossa API e do banco de Dados MongoDB

## Inserir imagem docker

### Após isso você poderá já experimentar os endpoints no Postman ou no Insomnia. Antes disso, você poderá dar uma olhada
na documentação da API pelo swagger, basta entrar no link: http://localhost:8080/swagger-ui/index.html.

## IMAGEM DO SWAGGER

    link para download das collections para importar no postman ou insomnia:

## ENDPOINTS
### POST - localhost:8080/tarefa/salvar
#### REQUEST
    
    {
	"nome": "Criar API Webflux",
	"descricao": "Criar API com Spring Webflux e MongoDB",
	"dataTermino": "08/07/2024"
    }

#### RESPONSE
    {
	"id": "668c3aec41a60014cecc0e8d",
	"nome": "Spring com webflux",
	"descricao": "criando tarefa",
	"status": "A_FAZER",
	"prioridade": "BAIXA",
	"dataTermino": "28/07/2024",
	"listAnexosUrl": []
}

### POST - localhost:8080/tarefa/{Inserir ID da tarefa}/anexo
    Nessa requisição, você deverá colocar o id da tarefa, que foi gerado quando fez o cadastro da mesma. Além de inserir
    um parametro "file", enviando o arquivo que deseja.
    
#### RESPONSE
    "https://hiveplace-desafio-s3.s3.sa-east-1.amazonaws.com/spring.jpg"


### GET - https://zf702kkb7f.execute-api.sa-east-1.amazonaws.com/test/calc-date?dias={Inserir Número de Dias}
    Nessa requisição você poderá testar diretamente o endpoint da AWS Lambda. Lembrando de inseir o número de dias no espaço marcado.
    Também será necessário um Header, com o valor
    campo: authorizationToken  valor: abc123
    Ele será necessário para te dar permissão para utilizar a lambda

### GET - localhost:8080/tarefa/listarTodas
#### RESPONSE
    [
	{
		"id": "668c3aec41a60014cecc0e8d",
		"nome": "Listando toda as tarefas",
		"descricao": "tarefas listadas",
		"status": "A_FAZER",
		"prioridade": "BAIXA",
		"dataTermino": "28/07/2024",
		"listAnexosUrl": []
	}
]

### GET - localhost:8080/tarefa/listarTodas/{0}
    Nesse endpoint você deverá mudar o último valor da URL
    0 - A FAZER
    1 - EM DESENVOLVIMENTO
    2 - CONCLUIDA
    3 - NAO CONCLUIDA
#### RESPONSE
    [{
		"id": "668a37ea7c89d353e0ef21e5",
		"nome": "Listando tarefas por status",
		"descricao": "tarefa listada por status",
		"status": "A_FAZER",
		"dataTermino": "04/02/2023",
		"listAnexosUrl": [
			"https://hiveplace-desafio-s3.s3.sa-east-1.amazonaws.com/webflux.jpg",
			"https://hiveplace-desafio-s3.s3.sa-east-1.amazonaws.com/spring.jpg"
		]
	}}

### PUT - localhost:8080/tarefa/atualizar
    Nessa requisição você deverá passar o ID da tarefa no body.
#### REQUEST
    {
	"id": "668c5ebef766da618216b105",
	"nome": "Criar API Webflux",
	"descricao": "Criar API com Spring Webflux e MongoDB",
	"status": 3,
	"prioridade": 3,
	"dataTermino": "08/07/2024"
}

#### RESPONSE
    {
	"id": "668c5ebef766da618216b105",
	"nome": "Criar API Webflux",
	"descricao": "Criar API com Spring Webflux e MongoDB",
	"status": "NAO_CONCLUIDA",
	"prioridade": "ALTA",
	"dataTermino": "08/07/2024",
	"listAnexosUrl": []
    }

### DELETE - localhost:8080/tarefa/{Insira o id da tarefa}
    Nessa requisição, deverá ser passado o ID pela URL
