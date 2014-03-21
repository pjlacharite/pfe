package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Broadcaster;

public class BroadcasterDAO extends AbstractDAO<Broadcaster> {

    public BroadcasterDAO(){
        this.connection = DatabaseUtils.getConnection();
    }

    @Override
    public Broadcaster create(Broadcaster object) {
        try{
            String sql = "INSERT INTO Broadcaster (broadcasterId, name) values (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, object.getId());
            statement.setString(2, object.getName());
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
        return object;
    }

    @Override
    public Broadcaster find(String id) {
        Broadcaster broadcaster = null;
        try{
            String sql = "SELECT * FROM Broadcaster WHERE broadcasterId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                broadcaster = new Broadcaster();
                broadcaster.setId(resultSet.getString(1));
                broadcaster.setName(resultSet.getString(2));
            }
        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
        return broadcaster;

    }

    @Override
    public List<Broadcaster> findAll(List<String> filters) {
        List<Broadcaster> broadcasters = new ArrayList<Broadcaster>();
        try{
            String sql = "SELECT * FROM Broadcaster";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Broadcaster broadcaster = new Broadcaster();
                broadcaster.setId(resultSet.getString(1));
                broadcaster.setName(resultSet.getString(2));
                broadcasters.add(broadcaster);
            }
        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
        return broadcasters;
    }

}
