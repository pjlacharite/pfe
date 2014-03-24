package model;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * Class representing a Schedule
 * @author pjlacharite
 *
 */
@Root
public class Schedule {
    @ElementList
    private List<ScheduleSlot> scheduleSlots;

    public Schedule(){
        this.scheduleSlots = new ArrayList<ScheduleSlot>();
    }
    public List<ScheduleSlot> getScheduleSlots() {
        return scheduleSlots;
    }
    public void addScheduleSlots(ScheduleSlot scheduleSlot) {
        this.scheduleSlots.add(scheduleSlot);
    }
    public void addScheduleSlots(List<ScheduleSlot> scheduleSlots) {
        this.scheduleSlots.addAll(scheduleSlots);
    }
}
