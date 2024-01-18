package com.github.hanielcota.sql.operations;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class TableCreator {

    private final HikariDataSource dataSource;

    public void createTable(String tableName, String tableDefinition) {
        validateInputs(tableName, tableDefinition);

        String createTableQuery = String.format("CREATE TABLE IF NOT EXISTS %s (%s)", tableName, tableDefinition);

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(createTableQuery)) {

            statement.executeUpdate();
            log.info("Table '{}' created successfully. SQL Query: '{}'", tableName, createTableQuery);

        } catch (SQLException e) {
            log.error("Error creating table '{}'. SQL Query: '{}'", tableName, createTableQuery);
            log.error("SQLException Details - SQL State: {}, Error Code: {}, Message: {}", e.getSQLState(), e.getErrorCode(), e.getMessage());
        }
    }

    private void validateInputs(String tableName, String tableDefinition) {
        Objects.requireNonNull(tableName, "Table name must not be null");
        Objects.requireNonNull(tableDefinition, "Table definition must not be null");
    }
}
