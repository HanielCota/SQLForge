package com.github.hanielcota.sql.operations;

import com.github.hanielcota.sql.ResultSetMapper;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SqlExecutor {

	private final HikariDataSource dataSource;

	public void executeUpdate(String query, Object... parameters) throws SQLException {
		Objects.requireNonNull(query, "Query must not be null");
		Objects.requireNonNull(parameters, "Parameters must not be null");

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			setParameters(statement, parameters);
			statement.executeUpdate();

		} catch (SQLException e) {
			log.error("Error executing update query: {}", query, e);
			throw e;
		}
	}

	public <T> T executeQuery(String query, ResultSetMapper<T> mapper, Object... parameters) throws SQLException {
		Objects.requireNonNull(query, "Query must not be null");
		Objects.requireNonNull(mapper, "Mapper must not be null");

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			setParameters(statement, parameters);

			try (ResultSet resultSet = statement.executeQuery()) {
				return mapper.map(resultSet);
			}

		} catch (SQLException e) {
			log.error("Error executing query: {}", query, e);
			throw e;
		}
	}

	private void setParameters(PreparedStatement statement, Object... parameters)
			throws SQLException {
		Objects.requireNonNull(statement, "Statement must not be null");
		Objects.requireNonNull(parameters, "Parameters must not be null");

		for (int i = 0; i < parameters.length; i++) {
			statement.setObject(i + 1, parameters[i]);
		}
	}
}
