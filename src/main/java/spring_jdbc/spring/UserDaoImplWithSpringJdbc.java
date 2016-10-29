package spring_jdbc.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.sbt.lessons.UserDao;
import ru.sbt.lessons.UserDaoImpl;

/**
 * Created by Norg on 29.10.2016.
 */
public class UserDaoImplWithSpringJdbc implements UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImplWithSpringJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
