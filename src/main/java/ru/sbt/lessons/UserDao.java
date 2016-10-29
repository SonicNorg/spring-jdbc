package ru.sbt.lessons;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Dao for app Users
 */
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    Optional<User> findByLogin(String login) {
        return jdbcTemplate.execute(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(
                    "select id, login, password_hash from user " +
                            "where login = ?")
            ) {
                statement.setString(1, login);
                ResultSet resultSet = statement.executeQuery();
                if (!resultSet.next()) {
                    return Optional.empty();
                }

                User user = new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
                statement.close();
                resultSet.close();
                return Optional.of(user);
            }
        });
    }

    boolean create(final User user) {
        return jdbcTemplate.execute(connection -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into user (login, password_hash)" +
                            "values (?,?)")) {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPasswordMD5());
                int result = 0;
                try {
                    result = preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException("User is already exist");
                }
                preparedStatement.close();
                return result == 1;
            }
        });
    }

    boolean update(final User user) {
        return jdbcTemplate.execute(connection -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "update user set login = ?, password_hash = ? where id = ?")) {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPasswordMD5());
                preparedStatement.setLong(3, user.getId());
                int result = 0;
                try {
                    result = preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException("User not found in database, please add him", e);
                }
                preparedStatement.close();
                return result == 1;
            }
        });
    }
    //дз в презентации

    List<User> list() {
        return jdbcTemplate.execute(connection -> {
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("select id, login, password_hash from user")) {
                List<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    User user = new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3));
                    users.add(user);
                }
                statement.close();
                resultSet.close();
                return users;
            }
        });
    }
}
