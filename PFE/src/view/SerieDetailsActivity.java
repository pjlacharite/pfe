package view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Serie;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pfe.R;

import controller.ControllerDispatcher;
import controller.SerieController;

/**
 * Activity to view the Details of a Serie
 * @author pjlacharite
 *
 */
public class SerieDetailsActivity extends Activity {

	private static final String KEY_SERIE = "serie";
	private static final String KEY_EPISODE = "episode";
	private static final String CONTROLLER_SERIE = "SerieController";
	private static final int TV = 0;
	private static final int STREAM = 1;
	private static final int DVD = 2;
	private String serieId;
	private Serie serie;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_serie_details);
		serieId = String.valueOf(getIntent().getExtras().get(KEY_SERIE));
		System.out.println("SerieDetail " + serieId);
		ControllerDispatcher dispatcher = ControllerDispatcher.getDispatcher();
		SerieController serieController = (SerieController)dispatcher.getController(CONTROLLER_SERIE);
		List<Serie> series = serieController.fetchAllSeries();
		Log.e("lol", String.valueOf(series.size()));
		serie = serieController.getSerie(Integer.parseInt(serieId));
		if (serie != null){
			TextView titleTextView = (TextView)findViewById(R.id.seriesDetailTitle);
			titleTextView.setText(serie.getName());
			TextView descriptionTextView = (TextView)findViewById(R.id.seriesDetailDescription);
			descriptionTextView.setText(serie.getDescription());
		}
		setupSeasonSpinner();
		setupSupportsSpinner();
	}

	public void setupSeasonSpinner(){
		Spinner seasonSpinner = (Spinner)findViewById(R.id.seasonSpinner);
		List<String> seasonsList = new ArrayList<String>();
		seasonsList.add("1");
		seasonsList.add("2");
		seasonsList.add("3");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, seasonsList);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);;
		seasonSpinner.setAdapter(dataAdapter);
		
		seasonSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			private boolean firstPass = true;
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
					int position, long id) {
				if (firstPass){
					firstPass = false;
					return;
				}
				setupEpisodeSpinner(parentView.getSelectedItemPosition());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// Do nothing
			}
		});
	}

	public void setupEpisodeSpinner(int seasonNumber){
		Spinner episodesSpinner = (Spinner)findViewById(R.id.episodeSpinner);
		List<String> episodesList = new ArrayList<String>();
		episodesList.add("1");
		episodesList.add("2");
		episodesList.add("3");
		episodesList.add("4");
		episodesList.add("5");
		episodesList.add("6");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, episodesList);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);;
		episodesSpinner.setAdapter(dataAdapter);
		
		episodesSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			private boolean firstPass = true;
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
					int position, long id) {
				if (firstPass){
					firstPass = false;
					return;
				}
				Intent intent = new Intent(SerieDetailsActivity.this, EpisodeDetailsActivity.class);
				System.out.println("Detail Activity " + parentView.getSelectedItemId());
				intent.putExtra(KEY_EPISODE, parentView.getSelectedItemId());
				SerieDetailsActivity.this.startActivity(intent);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// Do nothing
			}
			
		});
	}

	public void setupSupportsSpinner(){
		Spinner broadcastSupportSpinner = (Spinner)findViewById(R.id.broadcastSupportSpinner);
		List<String> broadcastSupportsList = new ArrayList<String>();
		broadcastSupportsList.add("Television");
		broadcastSupportsList.add("Web Streaming");
		broadcastSupportsList.add("DVD");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, broadcastSupportsList);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);;
		broadcastSupportSpinner.setAdapter(dataAdapter);
		
		broadcastSupportSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			private boolean firstPass = true;
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
					int position, long id) {
				if (firstPass){
					firstPass = false;
					return;
				}
				if (parentView.getSelectedItemPosition() == TV){
					Intent intent = new Intent(SerieDetailsActivity.this, ScheduleActivity.class);
					System.out.println("Detail Activity " + parentView.getSelectedItemId());
					intent.putExtra(KEY_SERIE, parentView.getSelectedItemId());
					SerieDetailsActivity.this.startActivity(intent);
				}else{
					displayResults(parentView.getSelectedItemPosition());
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// Do nothing
			}
			
		});
	}

	public void displayResults(int support){
		TextView resultTextView = (TextView)findViewById(R.id.broadcastSupportDetail);
		if (support == DVD){
			resultTextView.setText(new Date().toString());
		}else if (support == STREAM){
			resultTextView.setText("Netflix\nHBO");
		}
	}
}