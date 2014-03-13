package parsers;

import java.util.ArrayList;
import java.util.List;

import model.Episode;
import model.Season;

public class TVDBSeasonParser {

    public List<Season> parse(List<Episode> episodes){
        List<Season> seasons = new ArrayList<Season>();
        Season season = new Season();
        int episodeCount = 0;
        int currentSeason = 1;
        for (Episode episode: episodes){
            if (currentSeason != episode.getSeasonNumber()){
                seasons.add(season);
            }
            if (episode.getEpisodeNumber() == 1){
                season = new Season();
                currentSeason = episode.getSeasonNumber();
                season.setSeasonNumber(currentSeason);
                season.setId(String.valueOf(currentSeason));
                episodeCount = 1;
            }
            season.setEpisodeCount(episodeCount);
            episodeCount++;
        }
        seasons.add(season);
        return seasons;
    }
}
