package view;

import java.util.ArrayList;
import java.util.List;

import model.Serie;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.pfe.R;

import controller.SerieController;

/**
 * Main Activity
 * @author pjlacharite
 *
 */
public class MainMenuActivity extends Activity {

	private static final String KEY_SERIE = "serie";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		SetupSeriesSpinner();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	public void SetupSeriesSpinner(){
		Spinner seriesSpinner = (Spinner)findViewById(R.id.mainSeriesSpinner);
		List<Serie> seriesList = new SerieController().fetchAllSeries();
		List<String> seriesNameList = new ArrayList<String>();
		for (Serie serie : seriesList){
			seriesNameList.add(serie.getName());
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, seriesNameList);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);;
		seriesSpinner.setAdapter(dataAdapter);

		seriesSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			private boolean firstPass = true;
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
					int position, long id) {
				if (firstPass){
					firstPass = false;
					return;
				}
				Intent intent = new Intent(MainMenuActivity.this, SerieDetailsActivity.class);
				System.out.println("Main Activity " + parentView.getSelectedItemId());
				intent.putExtra(KEY_SERIE, parentView.getSelectedItemId());
				MainMenuActivity.this.startActivity(intent);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// Do nothing
			}

		});
	}
}