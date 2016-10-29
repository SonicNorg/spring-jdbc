package spring_jdbc.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.sbt.lessons.User;
import ru.sbt.lessons.UserDao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Norg on 29.10.2016.
 */
public class UserDaoImplWithSpringJdbc implements UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImplWithSpringJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        List<Map<String, Object>> userList = jdbcTemplate.queryForList("SELECT * FROM USER WHERE login = ?", login);
        if(userList.isEmpty()) {
            return Optional.empty();
        }
        User user = new User(((Long) userList.get(0).get("id")), ((String) userList.get(0).get("login")), ((String) userList.get(0).get("password_hash")));
        return Optional.of(user);
    }

    @Override
    public boolean create(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public List<User> list() {
        return null;
    }
}
