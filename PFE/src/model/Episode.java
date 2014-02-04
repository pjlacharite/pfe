package model;

import java.util.Date;

public class Episode {
	private int seasonNumber;
	private int episodeNumber;
	private String episodeName;
	private String episodeDescription;
	private Date originalAirDate;
	private int originalViewers;
	
	public Episode(int seasonNumber, int episodeNumber, String episodeName, String episodeDescription, Date originalAirDate, int originalViewers){
		this.seasonNumber = seasonNumber;
		this.episodeNumber = episodeNumber;
		this.episodeName = episodeName;
		this.episodeDescription = episodeDescription;
		this.originalAirDate = originalAirDate;
		this.originalViewers = originalViewers;
	}

	public int getSeasonNumber() {
		return seasonNumber;
	}

	public void setSeasonNumber(int seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	public int getEpisodeNumber() {
		return episodeNumber;
	}

	public void setEpisodeNumber(int episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	public String getEpisodeName() {
		return episodeName;
	}

	public void setEpisodeName(String episodeName) {
		this.episodeName = episodeName;
	}

	public String getEpisodeDescription() {
		return episodeDescription;
	}

	public void setEpisodeDescription(String episodeDescription) {
		this.episodeDescription = episodeDescription;
	}

	public Date getOriginalAirDate() {
		return originalAirDate;
	}

	public void setOriginalAirDate(Date originalAirDate) {
		this.originalAirDate = originalAirDate;
	}

	public int getOriginalViewers() {
		return originalViewers;
	}

	public void setOriginalViewers(int originalViewers) {
		this.originalViewers = originalViewers;
	}
}
