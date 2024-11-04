package com.github.hanielcota.sql.operations;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class TableCreator {

    private final HikariDataSource dataSource;

    public void createTable(String tableName, String tableDefinition) throws SQLException {
        validateInputs(tableName, tableDefinition);

        String createTableQuery = String.format("CREATE TABLE IF NOT EXISTS %s (%s)", tableName, tableDefinition);

        try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {

            statement.executeUpdate(createTableQuery);
            log.info("Table '{}' created successfully. SQL Query: '{}'", tableName, createTableQuery);

        } catch (SQLException e) {
            log.error("Error creating table '{}'. SQL Query: '{}'", tableName, createTableQuery);
            log.error("SQLException Details - SQL State: {}, Error Code: {}, Message: {}", e.getSQLState(), e.getErrorCode(), e.getMessage());
            throw e;
        }
    }

    private void validateInputs(String tableName, String tableDefinition) {
        Objects.requireNonNull(tableName, "Table name must not be null");
        Objects.requireNonNull(tableDefinition, "Table definition must not be null");

        if (!tableName.matches("[a-zA-Z0-9_]+")) {
            throw new IllegalArgumentException("Invalid table name: " + tableName);
        }

        if (!tableDefinition.matches("[a-zA-Z0-9_, ()]+")) {
            throw new IllegalArgumentException("Invalid table definition");
        }
    }
}
