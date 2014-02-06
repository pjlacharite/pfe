package controller;

import java.util.List;

import mock.SeriesMock;
import model.Season;
import model.Serie;

/**
 * Controller to retrieve information related to series
 * @author pjlacharite
 *
 */
public class SerieController implements Controller {
	
	private List<Serie> lastFetchedSerieList;
	
	/**
	 * Updates the series list and returns it
	 * @return
	 */
	public List<Serie> fetchAllSeries(){
		this.lastFetchedSerieList = SeriesMock.mockSeries();
		return this.lastFetchedSerieList;
	}
	
	/**
	 * Getter for a serie using its index in the List
	 * @param index
	 * @return
	 */
	public Serie getSerie(int index){
		if (index <= this.lastFetchedSerieList.size()-1){
			return this.lastFetchedSerieList.get(index);
		}
		return null;
	}
	
	/**
	 * Getter for a serie using its name
	 * @param name
	 * @return
	 */
	public Serie getSerie(String name){
		for(Serie serie : lastFetchedSerieList){
			if (serie.getName().equals(name)){
				return serie;
			}
		}
		return null;
	}
	
	/**
	 * Adds a list of Season to a serie
	 * @param index
	 * @param seasons
	 */
	public void addSeasons(int index, List<Season> seasons){
		this.lastFetchedSerieList.get(index).addSeasons(seasons);;
	}
	
	/**
	 * Adds a Season to a serie 
	 * @param index
	 * @param season
	 */
	public void addSeason(int index, Season season){
		this.lastFetchedSerieList.get(index).addSeason(season);
	}
}
