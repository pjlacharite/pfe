package persistence;

import java.util.List;

import model.ScheduleSlot;

public class ScheduleSlotDAO extends AbstractDAO<ScheduleSlot>{

    public ScheduleSlotDAO(){
        this.connection = DatabaseUtils.getConnection();
    }
    @Override
    public ScheduleSlot create(ScheduleSlot object) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ScheduleSlot find(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ScheduleSlot> findAll(List<String> filters) {
        // TODO Auto-generated method stub
        return null;
    }

}
