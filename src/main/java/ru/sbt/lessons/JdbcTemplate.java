package ru.sbt.lessons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by axelk on 22.10.2016.
 */
public class JdbcTemplate {
    private String url;

    public JdbcTemplate(String s) {
        this.url = s;
    }

    <T> T execute(JdbcAction<T> action){
        try(Connection connection = openConnection()){
            return action.execute(connection);
        } catch (SQLException e) {
            throw new IllegalStateException("Cant get connection",e);
        }
    }

    private Connection openConnection() throws SQLException {
            return DriverManager.getConnection(url);
    }

    interface JdbcAction<T>{
           T execute(Connection connection) throws SQLException;
    }
}
