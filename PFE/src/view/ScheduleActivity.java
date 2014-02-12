package view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.pfe.R;

/**
 * Activity to view the schedule of a broadcaster for a serie
 * @author pjlacharite
 *
 */
public class ScheduleActivity extends Activity {

    private static final String KEY_SERIE = "serie";
    private String serieId;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        serieId = String.valueOf(getIntent().getExtras().get(KEY_SERIE));
        System.out.println("Schedule " + serieId);
        
        TableLayout scheduleTableLayout = (TableLayout) findViewById(R.id.scheduleTableLayout);
        TableRow tableRow = new TableRow(this);
        TextView scheduleSlot = new TextView(this);
        scheduleSlot.setText("18h - How I Met Your Mother");
        tableRow.addView(scheduleSlot);
        scheduleTableLayout.addView(tableRow);
    }

}
