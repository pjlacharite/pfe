package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Episode;

public class EpisodeDAO extends AbstractDAO<Episode> {

    @Override
    public Episode create(Episode object) {
        Connection connection = DatabaseUtils.getConnection();
        try{
            String sql = "INSERT INTO Episode (episodeId, serieId, serieId, seasonNumber, episodeName, episodeDescription, originalAirDate, originalViewers) values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, object.getId());
            statement.setString(2, object.getSerieId());
            statement.setInt(3, object.getEpisodeNumber());
            statement.setInt(4, object.getSeasonNumber());
            statement.setString(5, object.getEpisodeName());
            statement.setString(6, object.getEpisodeDescription());
            if (object.getOriginalAirDate() != null){
                statement.setDate(7, new java.sql.Date(object.getOriginalAirDate().getTime()));
            }
            statement.setInt(8, object.getOriginalViewers());
            statement.executeUpdate();
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
        return object;
    }

    @Override
    public Episode find(String id) {
        Connection connection = DatabaseUtils.getConnection();
        Episode episode = null;
        try{
            String sql = "SELECT * FROM Episode WHERE episodeId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                episode = new Episode();
                episode.setId(resultSet.getString(1));
                episode.setSerieId(resultSet.getString(2));
                episode.setEpisodeNumber(resultSet.getInt(3));
                episode.setSeasonNumber(resultSet.getInt(4));
                episode.setEpisodeName(resultSet.getString(5));
                episode.setEpisodeDescription(resultSet.getString(6));
                episode.setOriginalAirDate(new Date(resultSet.getInt(7)));
                episode.setOriginalViewers(resultSet.getInt(8));
            }
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
        return episode;
    }

    @Override
    public List<Episode> findAll(List<String> filters) {
        Connection connection = DatabaseUtils.getConnection();
        List<Episode> episodes = new ArrayList<Episode>();
        try{
            String sql = "SELECT * FROM Episode WHERE serieId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, filters.get(0));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Episode episode = new Episode();
                episode.setId(resultSet.getString(1));
                episode.setSerieId(resultSet.getString(2));
                episode.setEpisodeNumber(resultSet.getInt(3));
                episode.setSeasonNumber(resultSet.getInt(4));
                episode.setEpisodeName(resultSet.getString(5));
                episode.setEpisodeDescription(resultSet.getString(6));
                episode.setOriginalAirDate(new Date(resultSet.getInt(7)));
                episode.setOriginalViewers(resultSet.getInt(8));
                episodes.add(episode);
            }
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
        return episodes;
    }

}
