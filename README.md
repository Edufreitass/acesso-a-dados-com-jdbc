# [Curso: Programação Orientada a Objetos com Java](https://www.udemy.com/course/java-curso-completo)

### Prof. Dr. Nelio Alves

## Capítulo: Acesso a banco de dados com JDBC

## Objetivo geral:

- Conhecer os principais recursos do JDBC na teoria e prática
- Elaborar a estrutura básica de um projeto com JDBC
- Implementar o padrão DAO manualmente com JDBC

## Visão geral do JDBC

- JDBC (Java Database Connectivity): API padrão do Java para acesso a dados
- Páginas oficiais:
  - https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/
  - https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html
- Pacotes: **java.sql** e **javax.sql** (API suplementar para servidores)

![image](https://user-images.githubusercontent.com/56324728/90773284-0ac35280-e2cc-11ea-84d0-273932623bc9.png)

## Instalação das ferramentas:

- Instalar o MySQL Server e o MySQL Workbench

## Preparação do primeiro projeto no Eclipse

**Código fonte:** https://github.com/acenelio/jdbc1

**Checklist:**
- Usando o MySQL Workbench, crie uma base de dados chamada "coursejdbc"
- Baixar o MySQL Java Connector
- Caso ainda não exista, criar uma User Library contendo o arquivo .jar do driver do MySQL
  - Window -> Preferences -> Java -> Build path -> User Libraries
  - Dê o nome da User Library de MySQLConnector
  - Add external JARs -> (localize o arquivo jar)
- Criar um novo Java Project
  - Acrescentar a User Library MySQLConnector ao projeto
- Na pasta raiz do projeto, criar um arquivo "db.properties" contendo os dados de conexão:

```
user=root
password=root
dburl=jdbc:mysql://localhost:3306/coursejdbc
useSSL=false
```

- No pacote "db", criar uma exceção personalizada DbException
- No pacote "db", criar uma classe DB com métodos estáticos auxiliares
  - Obter e fechar uma conexão com o banco
  
## Demo: recuperar dados

**Script SQL:** material de apoio ou https://github.com/acenelio/demo-dao-jdbc/blob/master/database.sql

**Código fonte:** https://github.com/acenelio/jdbc2

**API:**
- Statement
- ResultSet
  - first() [move para posição 1, se houver]
  - beforeFirst() [move para posição 0]
  - next() [move para o próximo, retorna false se já estiver no último]
  - absolute(int) [move para a posição dada, lembrando que dados reais começam em 1]
  
**Checklist:**
- Usar o script SQL para criar a base de dados "coursejdbc"
- Fazer um pequeno programa para recuperar os departamentos
- Na classe DB, criar métodos auxiliares estáticos para fechar ResultSet e Statement
  
**Atenção: o objeto ResultSet contém os
dados armazenados na forma de tabela:**

| Id | Name         |
|--- | ---          |
| 1  | Computers    |
| 2  | Electronics  |
| 3  | Fashion      |
| 4  | Books        |

## Demo: inserir dados

**Código fonte:** https://github.com/acenelio/jdbc3

**API:**
- PreparedStatement
- executeUpdate
- Statement.RETURN_GENERATED_KEYS
- getGeneratedKeys

**Checklist:**
- Inserção simples com preparedStatement
- Inserção com recuperação de Id

## Demo: atualizar dados

**Código fonte:** https://github.com/acenelio/jdbc4

## Demo: deletar dados

**Código fonte:** https://github.com/acenelio/jdbc5

**Checklist:**
- Criar DbIntegrityException
- Tratar a exceção de integridade referencial

## Demo: transações

**Referências:** https://www.ibm.com/support/knowledgecenter/en/SSGMCP_5.4.0/product-overview/acid.html

**Código fonte:** https://github.com/acenelio/jdbc6

**API:**
- setAutoCommit(false)
- commit()
- rollback()
