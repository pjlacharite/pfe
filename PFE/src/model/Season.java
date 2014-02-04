package model;

import java.util.Date;
import java.util.List;

public class Season {
	private int seasonNumber;
	private Date dvdReleaseDate;
	private List<Episode> episodes;
	
	
	public Season(int seasonNumber, Date dvdReleaseDate, List<Episode> episodes){
		this.seasonNumber = seasonNumber;
		this.dvdReleaseDate = dvdReleaseDate;
		this.episodes = episodes;
	}
	
	public Season(int seasonNumber, Date dvdReleaseDate){
		this.seasonNumber = seasonNumber;
		this.dvdReleaseDate = dvdReleaseDate;
	}
	
	public Season(int seasonNumber){
		this.seasonNumber = seasonNumber;
	}

	public int getSeasonNumber() {
		return seasonNumber;
	}

	public void setSeasonNumber(int seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	public Date getDvdReleaseDate() {
		return dvdReleaseDate;
	}

	public void setDvdReleaseDate(Date dvdReleaseDate) {
		this.dvdReleaseDate = dvdReleaseDate;
	}

	public List<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<Episode> episodes) {
		this.episodes = episodes;
	}
}
