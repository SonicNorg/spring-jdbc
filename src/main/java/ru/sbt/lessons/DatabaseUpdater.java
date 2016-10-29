package ru.sbt.lessons;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by axelk on 22.10.2016.
 */
public class DatabaseUpdater {
    public static void main(String[] args) throws SQLException {
        new DatabaseUpdater().executeScript("sql/user.sql");
    }

    private void executeScript(String filename){
            try {
                String sqlScript = FileUtils.readFileToString(new File(filename));
                executeSql(sqlScript);
            } catch (SQLException e) {
                throw new IllegalArgumentException("Bad a uri "+filename);
            } catch (IOException e) {
               throw new IllegalArgumentException("Can't read file "+filename);
            }
    }

    private static void executeSql(String sql) throws SQLException {
        try (Connection connection =
                     DriverManager.getConnection("jdbc:h2:D:/sbt_training/jdbc_archetype/database/app");
             Statement statement = connection.createStatement())
        {
            boolean execute = statement.execute(sql);
            System.out.println("------------------");
            System.out.println("Result: "+execute) ;
            System.out.println("SQL: "+sql);
            System.out.println("------------------");
        }
    }
}
