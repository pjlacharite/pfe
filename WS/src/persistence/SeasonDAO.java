package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Season;

public class SeasonDAO extends AbstractDAO<Season> {

    @Override
    public Season create(Season object) {
        Connection connection = DatabaseUtils.getConnection();
        try{
            String sql = "INSERT INTO Season (seasonId, serieId, seasonNumber, dvdReleaseDate, episodeCount) values (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, object.getId());
            statement.setString(2, object.getSerieId());
            statement.setInt(3, object.getSeasonNumber());
            statement.setDate(4, object.getDvdReleaseDate());
            statement.setInt(5, object.getEpisodeCount());
            statement.executeUpdate();
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
        return object;
    }

    @Override
    public List<Season> findAll(List<String> filters) {
        Connection connection = DatabaseUtils.getConnection();
        List<Season> seasons = new ArrayList<Season>();
        try{
            String sql = "SELECT * FROM Season WHERE (seasonNumber = ? AND serieId = ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(filters.get(0)));
            statement.setString(2, filters.get(1));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Season season = new Season();
                season.setId(resultSet.getString(1));
                season.setSerieId(resultSet.getString(2));
                season.setSeasonNumber(resultSet.getInt(3));
                season.setDvdReleaseDate(new Date(resultSet.getInt(4)));
                season.setEpisodeCount(resultSet.getInt(5));
                seasons.add(season);
            }
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
        return seasons;
    }

    @Override
    public Season find(String id) {
        Connection connection = DatabaseUtils.getConnection();
        Season season = null;
        try{
            String sql = "SELECT * FROM Season WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                season = new Season();
                season.setId(resultSet.getString(1));
                season.setSerieId(resultSet.getString(2));
                season.setSeasonNumber(resultSet.getInt(3));
                season.setDvdReleaseDate(new Date(resultSet.getInt(4)));
                season.setEpisodeCount(resultSet.getInt(5));
            }
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
        return season;
    }

}
