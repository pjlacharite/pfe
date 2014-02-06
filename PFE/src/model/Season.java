package model;

import java.util.Date;
import java.util.List;

/**
 * Class representing a Season
 * @author pjlacharite
 *
 */
public class Season {
	private int seasonNumber;
	private Date dvdReleaseDate;
	private List<Episode> episodes;
	
	/**
	 * Constructor for Season
	 * @param seasonNumber
	 * @param dvdReleaseDate
	 * @param episodes
	 */
	public Season(int seasonNumber, Date dvdReleaseDate, List<Episode> episodes){
		this.seasonNumber = seasonNumber;
		this.dvdReleaseDate = dvdReleaseDate;
		this.episodes = episodes;
	}
	
	/**
	 * Constructor for Season
	 * @param seasonNumber
	 * @param dvdReleaseDate
	 */
	public Season(int seasonNumber, Date dvdReleaseDate){
		this.seasonNumber = seasonNumber;
		this.dvdReleaseDate = dvdReleaseDate;
	}
	
	/**
	 * Constructor for Season
	 * @param seasonNumber
	 */
	public Season(int seasonNumber){
		this.seasonNumber = seasonNumber;
	}

	/**
	 * Getter for seasonNumber
	 * @return
	 */
	public int getSeasonNumber() {
		return seasonNumber;
	}

	/**
	 * Setter for seasonNumber
	 * @param seasonNumber
	 */
	public void setSeasonNumber(int seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	/**
	 * Getter for dvdReleaseDate
	 * @return
	 */
	public Date getDvdReleaseDate() {
		return dvdReleaseDate;
	}

	/**
	 * Setter for dvdReleaseDate
	 * @param dvdReleaseDate
	 */
	public void setDvdReleaseDate(Date dvdReleaseDate) {
		this.dvdReleaseDate = dvdReleaseDate;
	}

	/**
	 * Getter for episodes
	 * @return
	 */
	public List<Episode> getEpisodes() {
		return episodes;
	}

	/**
	 * Setter for episodes
	 * @param episodes
	 */
	public void setEpisodes(List<Episode> episodes) {
		this.episodes = episodes;
	}
}
