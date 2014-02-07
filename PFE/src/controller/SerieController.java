package controller;

import java.util.List;

import mock.SeriesMock;
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
	 * Getter for a serie using its name
	 * @param name
	 * @return
	 */
	public Serie getSerie(String id){
		for(Serie serie : lastFetchedSerieList){
			if (serie.getId().equals(id)){
				return serie;
			}
		}
		return null;
	}
}
