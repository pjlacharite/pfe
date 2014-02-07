package view;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Episode;
import model.Season;
import model.Serie;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pfe.R;

import controller.ControllerDispatcher;
import controller.SeasonController;
import controller.SerieController;

/**
 * Activity to view the Details of a Serie
 * @author pjlacharite
 *
 */
public class SerieDetailsActivity extends Activity {

	private static final String KEY_SERIE = "serie";
	private static final String KEY_SEASON = "season";
	private static final String KEY_EPISODE = "episode";
	private static final String CONTROLLER_SERIE = "SerieController";
	private static final String CONTROLLER_SEASON = "SeasonController";
	private static final int TV = 0;
	private static final int STREAM = 1;
	private static final int DVD = 2;
	private String serieId;
	private Serie currentSerie;
	private Season currentSeason;
	private Map<String, String> seasonMap = new HashMap<String, String>();
	private Map<String, String> episodeMap = new HashMap<String, String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_serie_details);
		serieId = String.valueOf(getIntent().getExtras().get(KEY_SERIE));
		System.out.println("SerieDetail " + serieId);
		ControllerDispatcher dispatcher = ControllerDispatcher.getDispatcher();
		SerieController serieController = (SerieController)dispatcher.getController(CONTROLLER_SERIE);
		currentSerie = serieController.getSerie(serieId);
		if (currentSerie != null){
			TextView titleTextView = (TextView)findViewById(R.id.seriesDetailTitle);
			titleTextView.setText(currentSerie.getName());
			TextView descriptionTextView = (TextView)findViewById(R.id.seriesDetailDescription);
			descriptionTextView.setText(currentSerie.getDescription());
			setupSeasonSpinner();
			setupSupportsSpinner();
		}
	}

	public void setupSeasonSpinner(){
		Spinner seasonSpinner = (Spinner)findViewById(R.id.seasonSpinner);
		List<String> seasonsList = new ArrayList<String>();
		for (Season season : currentSerie.getSeasons()){
			seasonMap.put(String.valueOf(season.getSeasonNumber()), season.getId());
			seasonsList.add(String.valueOf(season.getSeasonNumber()));
		}
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
				ControllerDispatcher dispatcher = ControllerDispatcher.getDispatcher();
				SeasonController seasonController = (SeasonController)dispatcher.getController(CONTROLLER_SEASON);
				
				currentSeason = seasonController.getSeason(serieId, seasonMap.get(parentView.getSelectedItem()));
				setupEpisodeSpinner();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// Do nothing
			}
		});
	}

	public void setupEpisodeSpinner(){
		Spinner episodesSpinner = (Spinner)findViewById(R.id.episodeSpinner);
		List<String> episodesList = new ArrayList<String>();
		for (Episode episode : currentSeason.getEpisodes()){
			episodeMap.put(String.valueOf(episode.getEpisodeNumber()), episode.getId());
			episodesList.add(String.valueOf(episode.getEpisodeNumber()));
		}
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
				intent.putExtra(KEY_SEASON, ((Spinner)findViewById(R.id.seasonSpinner)).getSelectedItemPosition());
				intent.putExtra(KEY_SERIE, Integer.parseInt(serieId));
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