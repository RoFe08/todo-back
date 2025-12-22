📌 TODO – Backend API

Backend da aplicação Stefanini TODO, desenvolvido em Java 17 + Spring Boot, seguindo princípios de Clean Architecture, SOLID e boas práticas enterprise, com autenticação via JWT, persistência em SQL Server e migrações com Flyway.

🧱 Arquitetura

O projeto segue uma variação clara de Clean Architecture, separando responsabilidades e evitando acoplamento entre camadas.

src/main/java/com/exemplo/stefanini
├── adapter
│   ├── in
│   │   └── web            # Controllers, DTOs, Web Mappers
│   └── out
│       └── persistence    # JPA Entities, Repositories, Mappers
│
├── application
│   ├── port
│   │   ├── in             # UseCases (interfaces)
│   │   └── out            # Ports (repositories, external deps)
│   └── service            # Implementação dos UseCases
│
├── domain
│   ├── model              # Entidades de domínio (Task, User)
│   └── exception          # Exceções de domínio
│
├── infrastructure
│   └── security           # JWT, Filters, SecurityConfig
│
└── StefaniniApplication.java

🎯 Benefícios dessa abordagem

Independência de framework

Testabilidade

Evolução segura

Clareza de responsabilidades

Facilita manutenção e crescimento do sistema

🚀 Stack Tecnológica
Core

Java 17

Spring Boot 3.5

Spring Web

Spring Data JPA

Spring Security (Resource Server + JWT)

Persistência

SQL Server 2022

Flyway (controle de versão do banco)

Hibernate

Produtividade

Lombok

MapStruct

Infra / DevOps

Docker

Docker Compose

Nginx (proxy reverso no frontend)

CI/CD com GitHub Actions

Deploy em AWS EC2

🔐 Segurança e Autenticação

A aplicação utiliza JWT Stateless Authentication.

Fluxo:

Usuário se cadastra (POST /api/auth/register)

Usuário faz login (POST /api/auth/login)

Backend retorna um JWT

Frontend envia o token no header:

Authorization: Bearer <token>


Endpoints protegidos validam o token automaticamente

Endpoints públicos (permitAll)

/api/auth/login

/api/auth/register

Endpoints protegidos

/api/tasks/**

/api/dashboard/** (futuro)

🗄️ Modelo de Dados
Usuário (app_user)

id (UUID)

name

email (único)

password_hash

created_at

updated_at

Task (task)

id (UUID)

title

description

status (PENDING, IN_PROGRESS, DONE)

user_id (FK → app_user)

created_at

updated_at

Cada Task pertence obrigatoriamente a um Usuário.

🛠️ Migrations (Flyway)

As migrations ficam em:

src/main/resources/db/migration


Exemplo:

V1__init.sql → cria task e app_user com relacionamento

O Flyway executa automaticamente ao subir a aplicação

▶️ Como rodar o projeto
1️⃣ Pré-requisitos

Java 17

Docker

Docker Compose

IntelliJ IDEA (ou IDE similar)

2️⃣ Subir o banco de dados (Docker)

Na raiz do projeto:

docker compose up -d


Isso irá subir:

SQL Server (stefanini-sqlserver)

O volume garante persistência de dados.

3️⃣ Rodar o backend local (IntelliJ)

Abra o projeto no IntelliJ

Configure o perfil ativo:

spring.profiles.active=dev


Rode a classe:

StefaniniApplication


A API ficará disponível em:

http://localhost:8080

4️⃣ Variáveis importantes

Exemplo (application-dev.properties):

spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=todo_db;encrypt=true;trustServerCertificate=true
spring.datasource.username=sa
spring.datasource.password=Stefanini@2025!

spring.jpa.hibernate.ddl-auto=validate

spring.security.oauth2.resourceserver.jwt.secret-key=IHVtYS1jaGF2ZS1...

📡 Principais Endpoints
Auth

POST /api/auth/register

POST /api/auth/login

Tasks (JWT obrigatório)

GET /api/tasks

POST /api/tasks

PUT /api/tasks/{id}

DELETE /api/tasks/{id}

📖 Documentação da API (Swagger)

Após subir a aplicação:

http://localhost:8080/swagger-ui.html

🤖 Uso de IA no projeto

A IA foi utilizada como:

Copiloto técnico

Apoio na definição da arquitetura

Revisão de decisões de design

Sugestão de padrões (Clean Architecture, Ports & Adapters)

Otimização de código e fluxo de autenticação

Todas as decisões finais, implementações e validações foram realizadas de forma consciente e crítica pelo desenvolvedor.

✅ Status do Projeto

✔ Autenticação JWT
✔ CRUD de Tasks por usuário
✔ Clean Architecture aplicada
✔ Banco versionado com Flyway
✔ Docker + CI/CD
✔ Pronto para evolução (Dashboard, métricas, etc.)

👨‍💻 Autor

Rodrigo Ferraz
Desenvolvedor Backend / Full Stack
Projeto técnico desenvolvido com foco em qualidade, arquitetura e boas práticas enterprise.