# DSCatalog
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/neliocursos/exemplo-readme/blob/main/LICENSE) 

# Sobre o projeto


DSCatalog é uma aplicação Full Stack web e mobile construída durante o Bootcamps Spring e React edição 3.0 pela DevSuperior [DevSuperior](https://devsuperior.com.br/ "Site da DevSuperior").

Este projeto multifuncional serve como uma plataforma de catálogo de produtos, estruturada em torno de três níveis de permissão de usuário: visitantes não autenticados podem apenas navegar pelos produtos; usuários com perfil de operador têm a capacidade de adicionar produtos e categorias; e administradores, que detêm todos os privilégios anteriores, podem também gerenciar as contas dos usuários.

Além da funcionalidade central, o DSCatalog foi enriquecido com recursos avançados, incluindo a implementação de testes com JUnit, integração com serviços de armazenamento de imagens na AWS e a adoção de práticas de CI/CD, facilitando a implantação contínua através de Docker e AWS. Essa combinação de funcionalidades e tecnologias faz do DSCatalog uma solução robusta e versátil para a gestão de catálogos de produtos em ambientes empresariais.


## Layout web
![Web 1](https://github.com/Uillianleal/imagens/blob/main/assets/dscatalog-01.png?raw=true)

![Web 2](https://github.com/Uillianleal/imagens/blob/main/assets/dscatalog-02.png?raw=true)

![Web 3](https://github.com/Uillianleal/imagens/blob/main/assets/dscatalog-03.png?raw=true)

![Web 4](https://github.com/Uillianleal/imagens/blob/main/assets/dscatalog-04.png?raw=true)

## Modelo conceitual
![Modelo Conceitual](https://github.com/Uillianleal/imagens/blob/main/assets/Sem%20t%C3%ADtulo.png?raw=true)

# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- JPA / Hibernate
- Maven
- OAuth/JWT
- Junit

## Front end
- HTML / CSS / Sass/ Bootstrap/ JS / TypeScript
- ReactJS
- Axios
- React Hook Forms
- React Select
- Entre outras

- ## Implantação em produção
- Back end: heroku
- Front end web: Netlify
- Banco de dados: Postgresql

# Como executar o projeto

## Back end
Pré-requisitos: Java 11

```bash
# clonar repositório
git clone git@github.com:Uillianleal/DScatalog.git

# entrar na pasta do projeto back end
cd backend

# executar o projeto
./mvnw spring-boot:run
```

## Front end web
Pré-requisitos: npm / yarn 

nvm: version 14.21.3

```bash
# clonar repositório
git clone git@github.com:Uillianleal/DScatalog.git

# entrar na pasta do projeto front end web
cd front-web

# instalar dependências
yarn install

# executar o projeto
yarn start
```

# Autor
Uillian Leal Santos

linkedin: https://www.linkedin.com/in/uillian-leal-santos-06a897161/
