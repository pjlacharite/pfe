package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import model.ModelManager;
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
            String sql = "DROP TABLE IF EXISTS Serie";
            statement.executeUpdate(sql);
            sql = "CREATE TABLE Serie("
                    + "serieId varchar(255),"
                    + "seasonCount int,"
                    + "serieName varchar(255),"
                    + "serieDescription varchar(255))";
            statement.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS Season";
            statement.executeUpdate(sql);
            sql = "CREATE TABLE Season("
                    + "seasonId varchar(255),"
                    + "serieId varchar(255),"
                    + "seasonNumber int,"
                    + "dvdReleaseDate date,"
                    + "episodeCount int)";
            statement.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS Episode";
            statement.executeUpdate(sql);
            sql = "CREATE TABLE Episode("
                    + "episodeId varchar(255),"
                    + "serieId varchar(255),"
                    + "episodeNumber int,"
                    + "seasonNumber int,"
                    + "episodeName varchar(255),"
                    + "episodeDescription varchar(255),"
                    + "originalAirDate date,"
                    + "originalViewers int)";
            statement.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS ScheduleSlot";
            statement.executeUpdate(sql);
            sql = "CREATE TABLE ScheduleSlot("
                    + "serieId varchar(255),"
                    + "broadcasterId varchar(255),"
                    + "duration varchar(255),"
                    + "title varchar(255),"
                    + "airingTime varchar(255),"
                    + "source varchar(255),"
                    + "episodeTitle varchar(255))";
            statement.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS Broadcaster";
            statement.executeUpdate(sql);
            sql = "CREATE TABLE Broadcaster("
                    + "broadcasterId varchar(255),"
                    + "name varchar(255))";
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
        ModelManager.getInstance().processModels();
    }

}