package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ScheduleSlot;

public class ScheduleSlotDAO extends AbstractDAO<ScheduleSlot>{

    public ScheduleSlotDAO(){
        this.connection = DatabaseUtils.getConnection();
    }
    @Override
    public ScheduleSlot create(ScheduleSlot object) {
        try{
            String sql = "INSERT INTO ScheduleSlot (serieId, broadcasterId, duration, title, airingTime, source) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, object.getSerieId());
            statement.setString(2, object.getBroadcasterId());
            statement.setString(1, object.getDuration());
            statement.setString(2, object.getTitle());
            statement.setString(1, object.getAiringTime());
            statement.setString(2, object.getSource());
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
        return object;
    }

    @Override
    public ScheduleSlot find(String id) {
        ScheduleSlot scheduleSlot = null;
        try{
            String sql = "SELECT * FROM ScheduleSlot WHERE serieId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                scheduleSlot = new ScheduleSlot();
                scheduleSlot.setSerieId(resultSet.getString(1));
                scheduleSlot.setBroadcasterId(resultSet.getString(2));
                scheduleSlot.setDuration(resultSet.getString(3));
                scheduleSlot.setTitle(resultSet.getString(4));
                scheduleSlot.setAiringTime(resultSet.getString(5));
                scheduleSlot.setSource(resultSet.getString(6));
            }
        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
        return scheduleSlot;
    }

    @Override
    public List<ScheduleSlot> findAll(List<String> filters) {
        List<ScheduleSlot> scheduleSlots = new ArrayList<ScheduleSlot>();
        try{
            String sql = "SELECT * FROM Season WHERE (seasonNumber = ? AND serieId = ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(filters.get(0)));
            statement.setString(2, filters.get(1));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                ScheduleSlot scheduleSlot = new ScheduleSlot();
                scheduleSlot.setSerieId(resultSet.getString(1));
                scheduleSlot.setBroadcasterId(resultSet.getString(2));
                scheduleSlot.setDuration(resultSet.getString(3));
                scheduleSlot.setTitle(resultSet.getString(4));
                scheduleSlot.setAiringTime(resultSet.getString(5));
                scheduleSlot.setSource(resultSet.getString(6));
                scheduleSlots.add(scheduleSlot);
            }
        }catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
        return scheduleSlots;
    }

}
