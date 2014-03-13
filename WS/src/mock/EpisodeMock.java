package mock;

import java.util.ArrayList;
import java.util.List;

import model.Episode;

public class EpisodeMock {
    
    public static List<Episode> mockEpisodes(){
        List<Episode> episodesList = new ArrayList<Episode>();
        Episode episode1 = new Episode("1", 1, "Episode 1 name", "Episode 1 synopsis", null, 190000);
        Episode episode2 = new Episode("2", 2, "Episode 2 name", "Episode 2 synopsis", null, 220000);
        Episode episode3 = new Episode("3", 3, "Episode 3 name", "Episode 3 synopsis", null, 310000);
        episodesList.add(episode1);
        episodesList.add(episode2);
        episodesList.add(episode3);
        
        return episodesList;
    }
}
