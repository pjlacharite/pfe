package model;

import java.util.ArrayList;
import java.util.List;

public class Serie {
	private List<Season> seasons;
	private String name;
	private String description;
	
	public Serie(List<Season> seasons, String name, String description){
		this.seasons = seasons;
		this.name = name;
		this.description = description;
	}
	
	public Serie(String name, String description){
		this.seasons = new ArrayList<Season>();
		this.name = name;
		this.description = description;
	}

	public List<Season> getSeasons() {
		return seasons;
	}

	public void addSeasons(List<Season> seasons) {
		this.seasons.addAll(seasons);
	}

	public void addSeason(Season season){
		this.seasons.add(season);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
