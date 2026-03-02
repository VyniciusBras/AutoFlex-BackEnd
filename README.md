# 🚗 AutoFlex - Back End

O AutoFlex é uma solução de back-end robusta desenvolvida para otimizar o gerenciamento de inventário e sugestão de produção em ambientes industriais ou comerciais. Utilizando algoritmos de composição de produtos, o sistema calcula automaticamente a viabilidade de fabricação com base no estoque atual de matérias-primas.

## 🏗️ Arquitetura do Projeto
O projeto foi edificado sobre bases sólidas de engenharia de software, combinando padrões de mercado para garantir escalabilidade e manutenção:

- Padrão MVC: Organização clara onde os Controllers gerem as rotas REST, as Entities (Models) representam o domínio de dados e os DTOs funcionam como a "View" de dados para o front-end.

- Clean Architecture: A lógica de negócio está isolada na camada de Service (ProductionService), mantendo os controladores simples e desacoplados da implementação.

- Clean Code: Aplicação de princípios como nomes significativos, métodos de responsabilidade única (SRP) e alta testabilidade.

- Repository: Abstração total da camada de dados utilizando Spring Data JPA para interagir com o PostgreSQL.

```bash
autoflex/
├── src/
│   ├── main/
│   │   ├── java/com/example/autoflex/
│   │   │   ├── config/         # Configurações de CORS, Beans e Inicialização
│   │   │   ├── controller/     # Endpoints REST (Exposição das rotas da API)
│   │   │   ├── dto/            # Objetos de Transferência de Dados (Segurança e Performance)
│   │   │   ├── model/          # Entidades JPA (Mapeamento do Banco de Dados)
│   │   │   ├── repository/     # Interfaces Spring Data JPA (Acesso ao PostgreSQL)
│   │   │   ├── service/        # Regras de Negócio e Lógica de Produção
│   │   │   └── AutoflexApplication.java  # Classe Principal (Spring Boot Starter)
│   │   └── resources/
│   │       └── application.properties    # Configurações de conexão e Hibernate
│   └── test/
│       └── java/com/example/autoflex/    # Testes unitários com JUnit 5 e Mockito
├── build/                      # Arquivos compilados (gerados pelo Gradle)
├── gradle/                     # Wrapper do Gradle para portabilidade
├── Dockerfile                  # Receita para containerização da aplicação Java
├── docker-compose.yml          # Orquestração do App + Banco PostgreSQL
├── build.gradle                # Gerenciamento de dependências e build
└── README.md                   # Documentação técnica do projeto              
```

## 🚀 Tecnologias Utilizadas
- Java 21: Versão estável mais recente para alta performance e recursos modernos de linguagem.

- Spring Boot: Framework base para criação de microserviços e APIs REST.

- Gradle: Sistema de automação de build para gerenciamento de dependências e tarefas de compilação.

- PostgreSQL 17: Banco de dados relacional para persistência segura e escalável.

- Docker & Docker Compose: Containerização completa para garantir que o ambiente seja idêntico em qualquer máquina.

- Hibernate/JPA: Abstração de banco de dados e mapeamento objeto-relacional.

- JUnit 5 & Mockito: Garantia de qualidade através de testes unitários automatizados.

## ⚡ Instalação & Execução

Graças à containerização, você não precisa instalar Java ou PostgreSQL localmente. Basta ter o Docker Desktop instalado.

```bash
# Clonar o repositório:
git clone https://github.com/VyniciusBras/AutoFlex-BackEnd.git

# Entrar na pasta autoflex
cd AutoFlex

# Subir o ambiente completo, no terminal da pasta raiz, execute:
docker-compose up -d

# Verificar o status:
A API estará ativa em http://localhost:8080. O log deverá exibir a mensagem: BACK-END IS RUNNING!

# Para visualizar é necessário o front end estar também rodando em localhost, acessando:
http://localhost:3000

```

##🛠️ Configuração de Infraestrutura (Docker)
O projeto orquestra dois serviços principais:

autoflex_backend: Imagem personalizada baseada em eclipse-temurin:21.

postgres_autoflex: Instância isolada do PostgreSQL 17 com persistência de dados via volumes.

## 🧪 Executando Testes
Para validar a lógica de cálculo de produção, execute os testes unitários:
```bash
./gradlew test
```

## 📦 Serviços de API

CRUD em back end a API do AutoFlex foi desenhada para ser reativa e baseada em estados. Ela não apenas armazena dados, mas processa a lógica de "Smart Suggestions".

Fluxo de Funcionamento:
- Requisição: O front-end solicita uma lista de sugestões de produção.

- Processamento (Service Layer): O ProductionService consulta o ProductRepository para obter as composições de produtos e o estoque de matérias-primas disponível no RawMaterialRepository.

- Algoritmo: O sistema calcula, para cada produto, a quantidade máxima que pode ser produzida sem zerar o estoque de forma crítica.

- Resposta: A API retorna um DTO (Data Transfer Object), garantindo que apenas as informações necessárias cheguem ao usuário, mantendo a segurança e performance.

## Autor
Desenvolvido por Vynicius Brasil – Full Stack Developer com foco em excelência técnica e arquitetura de sistemas.
