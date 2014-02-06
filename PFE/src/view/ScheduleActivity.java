package view;

import android.app.Activity;
import android.os.Bundle;

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
	}

}
