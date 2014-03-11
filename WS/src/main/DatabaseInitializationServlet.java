package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import fetchers.Fetcher;
import fetchers.FetcherFactory;
import persistence.DatabaseUtils;


public class DatabaseInitializationServlet implements ServletContextListener {

    public static Connection connection;

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        createDatabaseTables();
        populateDatabaseTables();
    }

    private void createDatabaseTables(){
        connection = DatabaseUtils.getConnection();
        Statement statement = null;
        try{
            Class.forName("org.sqlite.JDBC");
            statement = connection.createStatement();
            // CREATE THE TABLES
            /*THIS IS A TABLE TEST*/
            String sql = "DROP TABLE IF EXISTS test";
            statement.executeUpdate(sql);
            sql = "CREATE TABLE test(testField varchar(255))";
            statement.executeUpdate(sql);
            sql = "insert into test values('1')";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch (Exception e){
          System.out.println(e.getLocalizedMessage());
        }
    }

    private void populateDatabaseTables(){
        List<Fetcher> fetchers = new FetcherFactory().getFetchers();
        for (Fetcher fetcher: fetchers){
            fetcher.fetch();
        }
    }

}