package controller;

import java.util.List;

import mock.SeasonMock;
import model.Season;

/**
 * Controller to retrieve information related to series
 * @author pjlacharite
 *
 */
public class SeasonController implements Controller{

    
    /**
     * Fetches all seasons for a serieId
     * @param serieId
     * @return
     */
    public List<Season> fetchAllSeasons(String serieId){
        List<Season> seasonList = SeasonMock.mockSeasons();
        return seasonList;
    }
    
    /**
     * Gets the season matching a serieId and seasonId
     * @param serieId
     * @param seasonId
     * @return
     */
    public Season getSeason(String serieId, String seasonId){
        List<Season> seasonList = fetchAllSeasons(serieId);
        for (Season season: seasonList){
            if (season.getId().equals(seasonId)){
                return season;    
            }        
        }
        return null;
    }
}
