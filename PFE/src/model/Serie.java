package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a Serie
 * @author pjlacharite
 *
 */
public class Serie {
	private List<Season> seasons;
	private String name;
	private String description;
	
	/**
	 * Constructor for Serie
	 * @param seasons
	 * @param name
	 * @param description
	 */
	public Serie(List<Season> seasons, String name, String description){
		this.seasons = seasons;
		this.name = name;
		this.description = description;
	}
	
	/**
	 * Constructor for Serie
	 * @param name
	 * @param description
	 */
	public Serie(String name, String description){
		this.seasons = new ArrayList<Season>();
		this.name = name;
		this.description = description;
	}

	/**
	 * Getter for seasons
	 * @return
	 */
	public List<Season> getSeasons() {
		return seasons;
	}

	/**
	 * Adds a List of seasons
	 * @param seasons
	 */
	public void addSeasons(List<Season> seasons) {
		this.seasons.addAll(seasons);
	}

	/**
	 * Adds a season
	 * @param season
	 */
	public void addSeason(Season season){
		this.seasons.add(season);
	}
	
	/**
	 * Getter for name
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for description
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter for description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
