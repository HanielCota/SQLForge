package com.github.hanielcota.sql.operations;

import com.github.hanielcota.sql.ResultSetMapper;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
public class SqlExecutor {

    private final HikariDataSource dataSource;
    private final Map<String, PreparedStatement> preparedStatements = new ConcurrentHashMap<>();

    public void executeUpdate(String query, Object... parameters) {
        Objects.requireNonNull(query, "Query must not be null");
        Objects.requireNonNull(parameters, "Parameters must not be null");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = getOrCreatePreparedStatement(connection, query)) {
            setParameters(statement, parameters);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error executing update query: {}", query, e);
        }
    }

    public <T> T executeQuery(String query, ResultSetMapper<T> mapper, Object... parameters) {
        Objects.requireNonNull(query, "Query must not be null");
        Objects.requireNonNull(mapper, "Mapper must not be null");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = getOrCreatePreparedStatement(connection, query)) {
            setParameters(statement, parameters);
            try (ResultSet resultSet = statement.executeQuery()) {
                return mapper.map(resultSet);
            }
        } catch (SQLException e) {
            log.error("Error executing query: {}", query, e);
            return null;
        }
    }

    private PreparedStatement getOrCreatePreparedStatement(Connection connection, String query) {
        return preparedStatements.computeIfAbsent(query, k -> {
            try {
                return connection.prepareStatement(k);
            } catch (SQLException e) {
                log.error("Error creating prepared statement for query: {}", k, e);
                throw new RuntimeException(e);
            }
        });
    }

    private void setParameters(PreparedStatement statement, Object... parameters) throws SQLException {
        Objects.requireNonNull(statement, "Statement must not be null");
        Objects.requireNonNull(parameters, "Parameters must not be null");

        for (int i = 0; i < parameters.length; i++) {
            statement.setObject(i + 1, parameters[i]);
        }
    }
}
