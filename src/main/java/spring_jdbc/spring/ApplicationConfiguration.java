package spring_jdbc.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.DriverManager;

/**
 * Created by axelk on 29.10.2016.
 */
@Configuration
public class ApplicationConfiguration {
    public DataSource dataSource(){
        return new DriverManagerDataSource("jdbc:h2:D:/sbt_training/jdbc_archetype/database/app");
    }
}
