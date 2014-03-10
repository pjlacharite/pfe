package persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtils {

    public static Connection getConnection(){
        Connection connection = null;
        try {
          Class.forName("org.sqlite.JDBC");
          connection = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e){
          System.out.println(e.getLocalizedMessage());
        }
        return connection;
    }

}
