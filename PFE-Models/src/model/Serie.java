package model;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Class representing a Serie
 * @author pjlacharite
 *
 */
@Root
public class Serie {
    @Attribute
    private String id;
    @Attribute
    private List<Season> seasons;
    @Attribute
    private String name;
    @Attribute
    private String description;

    /**
     * Constructor for Serie
     * @param seasons
     * @param name
     * @param description
     */
    public Serie(String id, List<Season> seasons, String name, String description){
        this.id = id;
        this.seasons = seasons;
        this.name = name;
        this.description = description;
    }

    /**
     * Constructor for Serie
     * @param name
     * @param description
     */
    public Serie(String id, String name, String description){
        this.id = id;
        this.seasons = new ArrayList<Season>();
        this.name = name;
        this.description = description;
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
