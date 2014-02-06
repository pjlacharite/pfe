package model;

import java.util.Date;

/**
 * Class representing an episode
 * @author pjlacharite
 *
 */
public class Episode {
	private int episodeNumber;
	private String episodeName;
	private String episodeDescription;
	private Date originalAirDate;
	private int originalViewers;
	
	/**
	 * Constructor for Episode
	 * @param seasonNumber
	 * @param episodeNumber
	 * @param episodeName
	 * @param episodeDescription
	 * @param originalAirDate
	 * @param originalViewers
	 */
	public Episode(int episodeNumber, String episodeName, String episodeDescription, Date originalAirDate, int originalViewers){
		this.episodeNumber = episodeNumber;
		this.episodeName = episodeName;
		this.episodeDescription = episodeDescription;
		this.originalAirDate = originalAirDate;
		this.originalViewers = originalViewers;
	}

	/**
	 * Getter for episodeNumber
	 * @return
	 */
	public int getEpisodeNumber() {
		return episodeNumber;
	}

	/**
	 * Setter for episodeNumber
	 * @param episodeNumber
	 */
	public void setEpisodeNumber(int episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	/**
	 * Getter for episodeName
	 * @return
	 */
	public String getEpisodeName() {
		return episodeName;
	}

	/**
	 * Setter for episodeName
	 * @param episodeName
	 */
	public void setEpisodeName(String episodeName) {
		this.episodeName = episodeName;
	}

	/**
	 * Getter for episodeDescription
	 * @return
	 */
	public String getEpisodeDescription() {
		return episodeDescription;
	}

	/**
	 * Setter for episodeDescription
	 * @param episodeDescription
	 */
	public void setEpisodeDescription(String episodeDescription) {
		this.episodeDescription = episodeDescription;
	}

	/**
	 * Getter for originalAirDate
	 * @return
	 */
	public Date getOriginalAirDate() {
		return originalAirDate;
	}

	/**
	 * Setter for originalAirDate
	 * @param originalAirDate
	 */
	public void setOriginalAirDate(Date originalAirDate) {
		this.originalAirDate = originalAirDate;
	}

	/**
	 * Getter for originalViewers
	 * @return
	 */
	public int getOriginalViewers() {
		return originalViewers;
	}

	/**
	 * Setter for originalViewers
	 * @param originalViewers
	 */
	public void setOriginalViewers(int originalViewers) {
		this.originalViewers = originalViewers;
	}
}
