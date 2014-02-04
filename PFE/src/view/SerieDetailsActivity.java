package view;

import com.example.pfe.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SerieDetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_serie_details);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.serie_details, menu);
		return true;
	}

}