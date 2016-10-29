package spring_jdbc.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by axelk on 29.10.2016.
 */
public class Application {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        DataSource bean = context.getBean(DataSource.class);
        System.out.println(bean.getConnection()
                .createStatement()
                .executeQuery("select count(1) from user")
                .getInt(1));
    }
}
