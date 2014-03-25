package view;

import model.Schedule;
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
        System.out.println("Schedule " + serieId);
        System.out.println("Schedule " + broadcasterId);
        ControllerDispatcher dispatcher = ControllerDispatcher.getDispatcher();
        ScheduleController scheduleController = (ScheduleController)dispatcher.getController(CONTROLLER_SCHEDULE);
        Schedule schedule = scheduleController.fetchSchedule(serieId, broadcasterId);
        System.out.println("SCHEDULE SIZE: " + schedule.getScheduleSlots().size());
        TableLayout scheduleTableLayout = (TableLayout) findViewById(R.id.scheduleTableLayout);
        TableRow tableRow = new TableRow(this);
        TextView scheduleSlot = new TextView(this);
        scheduleSlot.setText("18h - How I Met Your Mother");
        tableRow.addView(scheduleSlot);
        scheduleTableLayout.addView(tableRow);
    }

}
