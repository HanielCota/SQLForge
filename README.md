# SQLForge

## Project Description
<p align="center">Simplify interactions with databases using HikariCP and clean code practices in Java.</p>

## Badges
![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=java)
![HikariCP](https://img.shields.io/badge/HikariCP-5.1.0-007396?style=for-the-badge&logo=java)
![Lombok](https://img.shields.io/badge/Lombok-1.18.22-60B045?style=for-the-badge&logo=lombok)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

## Table of Contents
<!--ts-->
   * [About](#about)
   * [Example Usage](#example-usage)
   * [Components](#components)
      * [DatabaseConfig](#databaseconfig)
      * [TableCreator](#tablecreator)
      * [SqlExecutor](#sqlexecutor)
   * [Configuration](#configuration)
   * [Dependencies](#dependencies)
   * [License](#license)
<!--te-->

## About
SQLForge is a Java application that simplifies database interactions using HikariCP and adheres to clean code practices. The README provides a usage example and highlights the main components of the application.

## Example Usage

```java

@Slf4j
public class ExempleUsage {

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

    private static HikariDataSource configureDataSource() {
        return new DatabaseConfig()
                .configureDataSource("jdbc:mysql://localhost:3306/your_database",
                        "your_username",
                        "your_password");
    }

    private static void createTable(HikariDataSource dataSource) {
        TableCreator tableCreator = new TableCreator(dataSource);
        tableCreator.createTable("example_table", "id INT PRIMARY KEY, name VARCHAR(255)");
    }

    private static void executeUpdate(HikariDataSource dataSource) {
        SqlExecutor sqlExecutor = new SqlExecutor(dataSource);
        sqlExecutor.executeUpdate("INSERT INTO example_table (id, name) VALUES (?, ?)", 1, "Example");
    }

    private static String executeQuery(HikariDataSource dataSource) {
        SqlExecutor sqlExecutor = new SqlExecutor(dataSource);
        return sqlExecutor.executeQuery(
                "SELECT name FROM example_table WHERE id = ?",
                resultSet -> resultSet.next() ? resultSet.getString("name") : null,
                1);
    }

```

## Components

### DatabaseConfig
The `DatabaseConfig` class provides methods to configure and create a HikariCP `DataSource` with predefined properties.

### TableCreator
The `TableCreator` class allows for creating tables in the database. It validates input parameters and logs any errors that may occur during table creation.

### SqlExecutor
The `SqlExecutor` class provides methods to execute SQL updates and queries using a HikariCP `DataSource`. It manages prepared statements and handles SQL exceptions.

## Configuration

To use SQLForge in your project, include the following dependency in your Gradle build file:

```gradle
implementation 'com.zaxxer:HikariCP:5.1.0'
implementation 'org.projectlombok:lombok:1.18.22'
```

Feel free to modify and adapt the provided code snippets based on your project's needs.

## Dependencies
- [HikariCP 5.1.0](https://github.com/brettwooldridge/HikariCP)
- [Lombok 1.18.22](https://projectlombok.org/)

## License
This project is distributed under the MIT license. See the [LICENSE](LICENSE) file for more details.
