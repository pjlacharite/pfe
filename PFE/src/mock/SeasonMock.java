package mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Season;

public class SeasonMock {

    public static List<Season> mockSeasons(){
        List<Season> seasonsList = new ArrayList<Season>();
        Season season1 = new Season("1", 1, new Date(), 24);
        Season season2 = new Season("2", 2, new Date(), 22);
        Season season3 = new Season("3", 3, new Date(), 23);
        seasonsList.add(season1);
        seasonsList.add(season2);
        seasonsList.add(season3);
        return seasonsList;
    }
}
