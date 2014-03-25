package view;

import model.Schedule;
import model.ScheduleSlot;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.pfe.R;

import controller.ControllerDispatcher;
import controller.ScheduleController;

/**
 * Activity to view the schedule of a broadcaster for a serie
 * @author pjlacharite
 *
 */
public class ScheduleActivity extends Activity {

    private static final String KEY_SERIE = "serie";
    private static final String KEY_BROADCASTER = "broadcaster";
    private static final String CONTROLLER_SCHEDULE = "ScheduleController";
    private String serieId;
    private String broadcasterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        serieId = String.valueOf(getIntent().getExtras().get(KEY_SERIE));
        broadcasterId = String.valueOf(getIntent().getExtras().get(KEY_BROADCASTER));
        ControllerDispatcher dispatcher = ControllerDispatcher.getDispatcher();
        ScheduleController scheduleController = (ScheduleController)dispatcher.getController(CONTROLLER_SCHEDULE);
        Schedule schedule = scheduleController.fetchSchedule(serieId, broadcasterId);
        TableLayout scheduleTableLayout = (TableLayout) findViewById(R.id.scheduleTableLayout);
        TextView scheduleTitle = (TextView)findViewById(R.id.scheduleTitle);
        if (schedule.getScheduleSlots().size() > 0){
            scheduleTitle.setText(schedule.getScheduleSlots().get(0).getTitle());
        }else{
            scheduleTitle.setText("No upcoming broadcast");
        }
        for (int i = 0; i < schedule.getScheduleSlots().size(); i++){
            ScheduleSlot scheduleSlot = schedule.getScheduleSlots().get(i);
            TableRow tableRow = new TableRow(this);
            TextView scheduleTextView = new TextView(this);
            String scheduleSlotText = fixDate(scheduleSlot.getAiringTime()) + " - " + scheduleSlot.getEpisodeTitle();
            scheduleTextView.setText(scheduleSlotText);
            tableRow.addView(scheduleTextView);
            scheduleTableLayout.addView(tableRow);
        }
    }

    private String fixDate(String date){
        return date.substring(0, 10) + " - " + date.substring(11, 19);
        
    }

}
