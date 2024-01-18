package com.github.hanielcota;

import com.github.hanielcota.sql.config.DatabaseConfig;
import com.github.hanielcota.sql.operations.SqlExecutor;
import com.github.hanielcota.sql.operations.TableCreator;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

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
}
