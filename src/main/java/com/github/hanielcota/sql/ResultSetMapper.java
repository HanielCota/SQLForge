package com.github.hanielcota.sql;


import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultSetMapper<T> {
    T map(ResultSet resultSet) throws SQLException;
}
