package mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Season;

public class SeasonMock {

	public static List<Season> mockSeasons(){
		List<Season> seasonsList = new ArrayList<Season>();
		Season season1 = new Season(1, new Date(), EpisodeMock.mockEpisodes());
		Season season2 = new Season(2, new Date(), EpisodeMock.mockEpisodes());
		Season season3 = new Season(3, new Date(), EpisodeMock.mockEpisodes());
		seasonsList.add(season1);
		seasonsList.add(season2);
		seasonsList.add(season3);
		
		return seasonsList;
	}
}
