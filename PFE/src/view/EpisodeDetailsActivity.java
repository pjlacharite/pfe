package view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.example.pfe.R;

/**
 * Activity to view the detail of an episode
 * @author pjlacharite
 *
 */
public class EpisodeDetailsActivity extends Activity {

	private static final String KEY_EPISODE = "episode";
	private String episodeId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_episode_details);
		episodeId = String.valueOf(getIntent().getExtras().get(KEY_EPISODE));
		System.out.println("EpisodeDetail " + episodeId);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.episode_details, menu);
		return true;
	}

}
