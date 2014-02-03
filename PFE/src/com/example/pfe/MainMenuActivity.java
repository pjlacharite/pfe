package com.example.pfe;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainMenuActivity extends Activity {

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
		Spinner seriesSpinner = (Spinner)findViewById(R.id.spinner1);
		List<String> seriesList = new ArrayList<String>();
		seriesList.add("How I Met Your Mother");
		seriesList.add("The Big Bang Theory");
		seriesList.add("Californication");
		seriesList.add("How I Met Your Mother");
		seriesList.add("The Big Bang Theory");
		seriesList.add("Californication");
		seriesList.add("How I Met Your Mother");
		seriesList.add("The Big Bang Theory");
		seriesList.add("Californication");
		seriesList.add("How I Met Your Mother");
		seriesList.add("The Big Bang Theory");
		seriesList.add("Californication");
		seriesList.add("How I Met Your Mother");
		seriesList.add("The Big Bang Theory");
		seriesList.add("Californication");
		seriesList.add("How I Met Your Mother");
		seriesList.add("The Big Bang Theory");
		seriesList.add("Californication");
		seriesList.add("How I Met Your Mother");
		seriesList.add("The Big Bang Theory");
		seriesList.add("Californication");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, seriesList);
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
				//intent.putExtra("series", seriesSpinner.getSelectedItem().toString());
				MainMenuActivity.this.startActivity(intent);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

}
