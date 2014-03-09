package model;

import java.util.Date;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Class representing a Season
 * @author pjlacharite
 *
 */
@Root
public class Season {
    @Attribute
    private String id;
    @Attribute
    private int seasonNumber;
    @Attribute
    private Date dvdReleaseDate;
    @Attribute
    private int episodeCount;

    /**
     * Empty constructor for simpleXML
     */
    public Season(){
        
    }
    /**
     * Constructor for Season
     * @param seasonNumber
     * @param dvdReleaseDate
     * @param episodes
     */
    public Season(String id, int seasonNumber, Date dvdReleaseDate, int episodeCount){
        this.id = id;
        this.seasonNumber = seasonNumber;
        this.dvdReleaseDate = dvdReleaseDate;
        this.setEpisodeCount(episodeCount);
    }
    
    /**
     * Constructor for Season
     * @param seasonNumber
     * @param dvdReleaseDate
     */
    public Season(String id, int seasonNumber, Date dvdReleaseDate){
        this.id = id;
        this.seasonNumber = seasonNumber;
        this.dvdReleaseDate = dvdReleaseDate;
    }
    
    /**
     * Constructor for Season
     * @param seasonNumber
     */
    public Season(String id, int seasonNumber){
        this.id = id;
        this.seasonNumber = seasonNumber;
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

    public int getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
        this.episodeCount = episodeCount;
    }
}
