# 🛒 E-Commerce API RESTful

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

Uma API RESTful completa para gestão de e-commerce, construída com foco em segurança, escalabilidade e boas práticas de Engenharia de Software.

## 📌 Sobre o Projeto
Este projeto simula o back-end de uma loja virtual. O sistema permite a gestão de produtos, categorias e a realização de pedidos, tudo protegido por uma camada de segurança robusta. A arquitetura garante que cada pedido seja inequivocamente vinculado ao utilizador autenticado, prevenindo fraudes.

**Principais Desafios Resolvidos:**
- Implementação de autenticação *stateless* utilizando **Spring Security** e **Tokens JWT**.
- Encriptação de palavras-passe em repouso com **BCrypt**.
- Tratamento padronizado de erros globais (Global Exception Handler).
- Validação rigorosa de dados de entrada com Jakarta Validation.

## 📸 Documentação Interativa (Swagger)
A API está totalmente documentada utilizando o SpringDoc OpenAPI.

![Print do Swagger da API](Coloque_aqui_o_link_para_a_sua_imagem_do_swagger.png)
*> Dica: Adicione aqui um print do Swagger com o cadeado do JWT fechado.*

## 🚀 Tecnologias Utilizadas
* **Java 17**
* **Spring Boot 3** (Web, Data JPA, Security, Validation)
* **PostgreSQL** (Base de dados relacional)
* **Auth0 java-jwt** (Geração e validação de tokens)
* **SpringDoc OpenAPI** (Swagger UI)

## ⚙️ Como Executar Localmente

### Pré-requisitos
* Java 17+ instalado
* Maven
* PostgreSQL rodando na porta `5432`

### Passo a Passo

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/Lukezinx/E-commerce

2. **Configure a Base de Dados:**
 
Crie uma base de dados no seu PostgreSQL chamada Ecommerce.
As credenciais padrão da aplicação estão no ficheiro application.yaml:

Username: postgres
Password: postgres

3. **Inicie a Aplicação:**
Na raiz do projeto, execute:
mvn spring-boot:run

4. **Aceda ao Swagger:**
Aceda ao Swagger:
Com a aplicação a rodar, abra o navegador no seguinte endereço para testar os endpoints:
http://localhost:8080/swagger-ui/index.html


**Fluxo de Autenticação (Testando)**
Para aceder às rotas protegidas (como criar um produto ou pedido):

   1. Crie uma conta através do POST /auth/register.

   2. Faça login em POST /auth/login para receber o seu Bearer Token.

   3. No Swagger, clique no botão Authorize (cadeado verde) e cole o Token gerado.


Desenvolvido por Lucas Santos
