package model;

import java.util.List;

public class ModelCleaner {

    public ModelCleaner(){
    }

    public List<ScheduleSlot> cleanScheduleSlots(List<ScheduleSlot> scheduleSlots, List<Serie> series){
        for (ScheduleSlot scheduleSlot: scheduleSlots){
            for (Serie serie: series){
                if (scheduleSlot.getTitle().equals(serie.getName())){
                    scheduleSlot.setSerieId(serie.getId());
                }
            }
        }
        return scheduleSlots;
    }

}