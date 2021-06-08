
<h1 align="center">
   ⚕️ <a href="#" alt="site do DiMedical"> DiMedical </a>
</h1>

<h2 align="center">
   Com o DiMedical você cadastra e visualiza pedidos médicos de uma maneira simples e rápida.
</h2>


## [](https://github.com/diegofortunato/diMedical#stack-)Stack  💻

-   Kotlin
-   Spring Boot
-   Postgres
-   Docker

## [](https://github.com/diegofortunato/diMedical#build--)Build  🚀

Certifique que você tenha o Docker, Git e Gradle instalados em sua maquina e rode os seguintes comandos:

-   Primeiro passo clone o projeto:  `git clone git@github.com:diegofortunato/diMedical.git`
-   Entre na pasta raiz no local clonado.
-   Build o projeto com:  `gradle clean build`
-   Após o build execute o seguinte comando:  `docker build ./ -t app`
-   Após esse comando. execute:  `docker-compose up`

Pronto, o projeto estara disponivel em:  `localhost:8080`


## [](https://github.com/diegofortunato/diMedical#painel--)Painel  📊

Documentação e orientações do Painel para visualização dos dados cadastrados: [Painel](https://github.com/diegofortunato/diMedical-front)


## [](https://github.com/diegofortunato/diMedical#teste-%EF%B8%8F)Teste  ⚙️

Uma Collection do Postman de exemplo esta disponivel aqui:  [Collection](https://github.com/diegofortunato/diMedical/blob/develop/dimedical.postman_collection)

Abaixo um pouco sobre as chamadas da API:

***Criação de Paciente***

POST:  /patient

    {
    "request": {
        "nome": "Felipe Purchatti",
        "data_nascimento": "2000-12-25",
        "sexo": "MASCULINO",
        "nome_mae": "Carla Cristina",
        "endereco": {
            "rua": "Rua Itapolis",
            "numero": 1325,
            "cep": "01245000",
            "bairro": "Pacaembu",
            "cidade": "Sao Paulo",
            "estado": "SP"
        },
        "contato": {
            "telefone" : "(19)9.9477-8827",
            "email": "felipe@gmail.com"
        },
        "documento": {
            "cpf": "45474132822",
            "rg": "387963592"
        }
      }
    }

**Buscar Paciente**

GET: /patient/{id}

 **Atualizar Paciente**

PATCH: /patient/{id}

    {
    "request": {
        "nome": "Felipe Purchatti Ventura",
        "data_nascimento": "2000-12-25",
        "sexo": "MASCULINO",
        "nome_mae": "Carla Cristina",
        "endereco": {
            "rua": "Rua Itapolis",
            "numero": 1325,
            "cep": "01245000",
            "bairro": "Pacaembu",
            "cidade": "Sao Paulo",
            "estado": "SP"
        },
        "contato": {
            "telefone" : "(19)9.9477-8827",
            "email": "felipe@gmail.com"
        },
        "documento": {
            "cpf": "45474132822",
            "rg": "387963592"
         }
	    }
    }	

**Deletar Paciente:** 

DELETE: /patient/{id}

**Criar Médico(a):**

POST: /doctor

    {
    "request": {
        "nome": "Isabelly Fortes",
        "numero_conselho": 1423432532,
        "estado_conselho": "SP",
        "tipo_conselho": "CRM"
	    }
    }

**Buscar Médico(a):** 

GET: /doctor/{id}

**Atualizar Médico(a):**

PATCH: /doctor/{id}

    {
    "request": {
        "nome": "Isabelly Fortes da Silva",
        "numero_conselho": 1423432532,
        "estado_conselho": "SP",
        "tipo_conselho": "CRM"
	    }
    }

**Deletar Médico(a):** 

DELETE: /doctor/{id}


**Criar Pedido Médico:** 

POST: /exam

    {
    "request":{
        "paciente_id": 4,
        "medico(a)_id": 14,
        "exames": [
            {
                "nome": "Exame de sangue",
                "data_expiracao": "2021-07-10"
            },
            {
                "nome": "Exame de urina",
                "data_expiracao": "2021-07-11"
            }
        ]
     }
    }

**Buscar Pedido Médico:** 

GET: /exam/{id}

**Buscar todos Pedidos Médicos(Paginados):** 

/exam?page=0&size=3&orderBy=id

## [](https://github.com/diegofortunato/xy-inc#documeta%C3%A7%C3%A3o-)Health Check  🏥

Saúde da API esta disponivel aqui:  [Actuator](http://localhost:8080/actuator)


## [](https://github.com/diegofortunato/diMedical#documeta%C3%A7%C3%A3o-)Documetação  📝

Você pode encontrar a documentação do projeto aqui: [Swagger-UI](http://localhost:8080/swagger-ui.html#/)
                                                    [Doc]()

## [](https://github.com/diegofortunato/diMedical#autor-)Autor  🦸

Diego Fortunato Candido
