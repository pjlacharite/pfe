package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Broadcaster;
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

import controller.BroadcasterController;
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
    private static final String KEY_BROADCASTER = "broadcaster";
    private static final String CONTROLLER_SERIE = "SerieController";
    private static final String CONTROLLER_SEASON = "SeasonController";
    private static final String CONTROLLER_BROADCASTER = "BroadcasterController";
    private String serieId;
    private Serie currentSerie;
    private Season currentSeason;
    private List<String> broadcasterIds = new ArrayList<String>();
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
        currentSerie = serieController.fetchSerie(serieId);
        Spinner episodeSpinner = (Spinner)findViewById(R.id.episodeSpinner);
        episodeSpinner.setVisibility(View.INVISIBLE);
        TextView dvdReleaseTextView = (TextView)findViewById(R.id.releaseDateTextView);
        dvdReleaseTextView.setVisibility(View.INVISIBLE);
        TextView broadcastSupportTextView = (TextView)findViewById(R.id.broadcastSupportDetail);
        broadcastSupportTextView.setVisibility(View.INVISIBLE);
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
        seasonsList.add("Pick a Season");
        for (int i = 1; i < currentSerie.getSeasonCount()+1; i++){
            String seasonNumber = String.valueOf(i);
            seasonMap.put(seasonNumber, seasonNumber);
            seasonsList.add(seasonNumber);
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
                }else if (!parentView.getSelectedItem().equals("Pick a Season")){
                    ControllerDispatcher dispatcher = ControllerDispatcher.getDispatcher();
                    SeasonController seasonController = (SeasonController)dispatcher.getController(CONTROLLER_SEASON);
                    currentSeason = seasonController.fetchSeason(serieId, seasonMap.get(parentView.getSelectedItem()));
                    if (currentSeason.getDvdReleaseDate() != null){
                        TextView dvdReleaseTextView = (TextView)findViewById(R.id.releaseDateTextView);
                        dvdReleaseTextView.setText("Sortie DVD: " + currentSeason.getDvdReleaseDate().toString());
                        dvdReleaseTextView.setVisibility(View.VISIBLE);
                    }
                    setupEpisodeSpinner();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });
    }

    public void setupEpisodeSpinner(){
        Spinner episodesSpinner = (Spinner)findViewById(R.id.episodeSpinner);
        episodesSpinner.setVisibility(View.VISIBLE);
        List<String> episodesList = new ArrayList<String>();
        episodesList.add("Pick an Episode");
        for (int i = 1; i < currentSeason.getEpisodeCount() +1; i++){
            String episodeNumber = String.valueOf(i);
            episodeMap.put(episodeNumber, episodeNumber);
            episodesList.add(episodeNumber);
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
                }else if (!parentView.getSelectedItem().equals("Pick an Episode")){
                    Intent intent = new Intent(SerieDetailsActivity.this, EpisodeDetailsActivity.class);
                    System.out.println("Detail Activity " + parentView.getSelectedItemId());
                    intent.putExtra(KEY_EPISODE, parentView.getSelectedItemId()+1);
                    intent.putExtra(KEY_SEASON, currentSeason.getSeasonNumber());
                    intent.putExtra(KEY_SERIE, serieId);
                    SerieDetailsActivity.this.startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
            
        });
    }

    public void setupSupportsSpinner(){
        Spinner broadcastSupportSpinner = (Spinner)findViewById(R.id.broadcastSupportSpinner);
        BroadcasterController broadcasterController = (BroadcasterController)ControllerDispatcher.getDispatcher().getController(CONTROLLER_BROADCASTER);
        List<Broadcaster> broadcasters = broadcasterController.fetchBroadcasters();
        List<String> broadcastSupportsList = new ArrayList<String>();
        broadcastSupportsList.add("Pick a Broadcasting Support");
        for (Broadcaster broadcaster: broadcasters){
            broadcastSupportsList.add(broadcaster.getName());
            broadcasterIds.add(broadcaster.getId());
        }
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
                }else if (!parentView.getSelectedItem().equals("Pick a Broadcasting Support")){
                    Intent intent = new Intent(SerieDetailsActivity.this, ScheduleActivity.class);
                    intent.putExtra(KEY_BROADCASTER, broadcasterIds.get((parentView.getSelectedItemPosition()-1)));
                    intent.putExtra(KEY_SERIE, currentSerie.getId());
                    SerieDetailsActivity.this.startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
            
        });
    }
}