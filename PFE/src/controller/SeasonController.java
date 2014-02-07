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

	private List<Season> lastFetchedSeasonList;
	
	public List<Season> fetchAllSeasons(String serieId){
		this.lastFetchedSeasonList = SeasonMock.mockSeasons();
		return this.lastFetchedSeasonList;
	}
	
	public Season getSeason(String serieId, String seasonId){
		this.lastFetchedSeasonList = fetchAllSeasons(serieId);
		for (Season season: this.lastFetchedSeasonList){
			if (season.getId().equals(seasonId)){
				return season;	
			}		
		}
		return null;
	}
}
