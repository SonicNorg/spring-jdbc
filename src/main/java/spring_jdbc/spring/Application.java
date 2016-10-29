package spring_jdbc.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Конфигурация Спринга. Аналогична хмл-ной конфигурации.
 */
public class Application {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);

        List<Map<String, Object>> userList = jdbcTemplate.queryForList("select * from USER");

        DataSource bean = context.getBean(DataSource.class);
        System.out.println(bean.getConnection()
                .createStatement()
                .executeQuery("select count(1) from user")
                .getInt(1));
    }
}
