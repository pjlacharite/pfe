package model;

import java.util.Date;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Class representing an episode
 * @author pjlacharite
 *
 */
@Root
public class Episode {
    @Attribute
    private String id;
    @Attribute
    private int episodeNumber;
    @Attribute
    private String episodeName;
    @Attribute
    private String episodeDescription;
    @Attribute
    private Date originalAirDate;
    @Attribute
    private int originalViewers;

    /**
     * Empty constructor for simpleXML
     */
    public Episode(){
        
    }
    /**
     * Constructor for Episode
     * @param seasonNumber
     * @param episodeNumber
     * @param episodeName
     * @param episodeDescription
     * @param originalAirDate
     * @param originalViewers
     */
    public Episode(String id, int episodeNumber, String episodeName, String episodeDescription, Date originalAirDate, int originalViewers){
        this.id = id;
        this.episodeNumber = episodeNumber;
        this.episodeName = episodeName;
        this.episodeDescription = episodeDescription;
        this.originalAirDate = originalAirDate;
        this.originalViewers = originalViewers;
    }

    /**
     * Getter for id
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
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
