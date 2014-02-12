package view;

import model.Episode;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pfe.R;

import controller.ControllerDispatcher;
import controller.EpisodeController;

/**
 * Activity to view the detail of an episode
 * @author pjlacharite
 *
 */
public class EpisodeDetailsActivity extends Activity {

    private static final String KEY_SERIE = "serie";
    private static final String KEY_SEASON = "season";
    private static final String KEY_EPISODE = "episode";
    private static final String CONTROLLER_EPISODE = "EpisodeController";
    private String episodeId;
    private Episode currentEpisode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode_details);
        episodeId = String.valueOf(getIntent().getExtras().get(KEY_EPISODE));
        System.out.println("EpisodeDetail " + episodeId);
        String seasonId = String.valueOf(getIntent().getExtras().get(KEY_SEASON));
        String serieId = String.valueOf(getIntent().getExtras().get(KEY_SERIE));
        ControllerDispatcher dispatcher = ControllerDispatcher.getDispatcher();
        EpisodeController episodeController = (EpisodeController)dispatcher.getController(CONTROLLER_EPISODE);
        currentEpisode= episodeController.getEpisode(serieId, seasonId, episodeId);
        if (currentEpisode != null){
            TextView episodeTitleTextView = (TextView)findViewById(R.id.episodeDetailTitle);
            TextView episodeDescriptionTextView = (TextView)findViewById(R.id.episodeDetailDescription);
            TextView originalAirDateTextView = (TextView)findViewById(R.id.episodeDetailOriginalAirDateValue);
            TextView originalViewersTextView = (TextView)findViewById(R.id.episodeDetailOriginalViewersValue);
            
            episodeTitleTextView.setText(currentEpisode.getEpisodeName());
            episodeDescriptionTextView.setText(currentEpisode.getEpisodeDescription());
            originalAirDateTextView.setText(currentEpisode.getOriginalAirDate().toString());
            originalViewersTextView.setText(String.valueOf(currentEpisode.getOriginalViewers()));
        }
    }

}
