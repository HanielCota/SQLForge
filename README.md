# SQLForge

## Descrição do Projeto
<p align="center">Simplificando interações com bancos de dados usando HikariCP e práticas de código limpo em Java.</p>

## Badges
![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=java)
![HikariCP](https://img.shields.io/badge/HikariCP-5.1.0-007396?style=for-the-badge&logo=java)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

## Tabela de Conteúdos
=================
<!--ts-->
   * [Sobre](#sobre)
   * [Exemplo de Uso](#exemplo-de-uso)
   * [Componentes](#componentes)
      * [DatabaseConfig](#databaseconfig)
      * [TableCreator](#tablecreator)
      * [SqlExecutor](#sqlexecutor)
   * [Configuração](#configuração)
   * [Dependências](#dependências)
   * [Licença](#licença)
<!--te-->

## Sobre
O SQLForge é uma aplicação Java que simplifica a interação com bancos de dados usando o HikariCP e seguindo práticas de código limpo. O README fornece um exemplo de uso e destaca os principais componentes da aplicação.

## Exemplo de Uso

```java
package com.github.hanielcota;

import com.github.hanielcota.sql.config.DatabaseConfig;
import com.github.hanielcota.sql.operations.SqlExecutor;
import com.github.hanielcota.sql.operations.TableCreator;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleUsage {

    public static void main(String[] args) {
        // Configure the database
        HikariDataSource dataSource = configureDataSource();

        // Create a table
        createTable(dataSource);

        // Execute an update
        executeUpdate(dataSource);

        // Execute a query
        String result = executeQuery(dataSource);

        // Use Lombok logger instead of System.out
        log.info("Query result: {}", result);

        // Close the connection pool (optional)
        dataSource.close();
    }

    // ... (Código continua)
```

## Componentes

### DatabaseConfig
A classe `DatabaseConfig` fornece métodos para configurar e criar um `DataSource` do HikariCP com propriedades predefinidas.

### TableCreator
A classe `TableCreator` permite criar tabelas no banco de dados. Ele valida os parâmetros de entrada e registra quaisquer erros que possam ocorrer durante a criação da tabela.

### SqlExecutor
A classe `SqlExecutor` fornece métodos para executar atualizações e consultas SQL usando um `DataSource` do HikariCP. Ela gerencia instruções preparadas e lida com exceções SQL.

## Configuração

Para usar o SQLForge em seu projeto, inclua a seguinte dependência em seu arquivo de compilação do Gradle:

```gradle
implementation 'com.zaxxer:HikariCP:5.1.0'
```

Sinta-se à vontade para modificar e adaptar os trechos de código fornecidos com base nas necessidades do seu projeto.

## Dependências
- [HikariCP 5.1.0](https://github.com/brettwooldridge/HikariCP)

## Licença
Este projeto é distribuído sob a licença MIT. Consulte o arquivo [LICENSE](LICENSE) para obter mais detalhes.
