package ru.sbt.lessons;

import java.util.List;
import java.util.Optional;

/**
 * Created by axelk on 29.10.2016.
 */

public interface UserDao {
    Optional<User> findByLogin(String login);

    boolean create(User user);

    boolean update(User user);

    List<User> list();
}
