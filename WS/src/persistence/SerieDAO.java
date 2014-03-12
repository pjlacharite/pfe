package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Serie;

public class SerieDAO extends AbstractDAO<Serie> {

    @Override
    public Serie create(Serie object) {
        Connection connection = DatabaseUtils.getConnection();
        try{
            String sql = "INSERT INTO";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, object.getId());
            statement.executeUpdate();
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
        return object;
    }

    @Override
    public List<Serie> findAll(List<String> filters) {
        Connection connection = DatabaseUtils.getConnection();
        List<Serie> series = null;
        try{
            String sql = "SELECT ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, filters.get(0));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Serie serie = new Serie();
                String serieId = resultSet.getString(0);
                //TODO ADD THE REST
                series.add(serie);
            }
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
        return series;
    }

    @Override
    public Serie find(String id) {
        Connection connection = DatabaseUtils.getConnection();
        Serie serie = null;
        try{
            String sql = "SELECT ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                serie = new Serie();
                String serieId = id;
                String serieName = resultSet.getString(0);
                //TODO ADD THE REST
            }
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
        return serie;
    }

}
