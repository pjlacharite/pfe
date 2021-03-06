package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Serie;

public class SerieDAO extends AbstractDAO<Serie> {

    public SerieDAO(){
        this.connection = DatabaseUtils.getConnection();
    }
    @Override
    public Serie create(Serie object) {
        try{
            String sql = "INSERT INTO Serie (serieId, seasonCount, serieName, serieDescription) values (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, object.getId());
            statement.setInt(2, object.getSeasonCount());
            statement.setString(3, object.getName());
            statement.setString(4, object.getDescription());
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
        return object;
    }

    @Override
    public List<Serie> findAll(List<String> filters) {
        List<Serie> series = new ArrayList<Serie>();
        try{
            String sql = "SELECT * FROM Serie";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Serie serie = new Serie();
                serie.setId(resultSet.getString(1));
                serie.setSeasonCount(resultSet.getInt(2));
                serie.setName(resultSet.getString(3));
                serie.setDescription(resultSet.getString(4));
                series.add(serie);
            }
        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
        return series;
    }

    @Override
    public Serie find(String id) {
        Serie serie = null;
        try{
            String sql = "SELECT * FROM Serie WHERE serieId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                serie = new Serie();
                serie.setId(resultSet.getString(1));
                serie.setSeasonCount(resultSet.getInt(2));
                serie.setName(resultSet.getString(3));
                serie.setDescription(resultSet.getString(4));
            }
        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
        return serie;
    }

}
