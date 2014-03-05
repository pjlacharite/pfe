package model;

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
    private int seasonCount;
    @Attribute
    private String name;
    @Attribute
    private String description;

    public Serie(){
        //Empty constructor for simplexml
    }
    /**
     * Constructor for Serie
     * @param seasons
     * @param name
     * @param description
     */
    public Serie(String id, int seasonCount, String name, String description){
        this.id = id;
        this.seasonCount = seasonCount;
        this.name = name;
        this.description = description;
    }

    /**
     * Lazy constructor for Serie
     * @param name
     */
    public Serie(String id, String name){
        this.id = id;
        this.name = name;
    }

    /**
     * Constructor for Serie
     * @param name
     * @param description
     */
    public Serie(String id, String name, String description){
        this.id = id;
        this.seasonCount = 0;
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
     * Getter for name
     * @return
     */
    public String getName() {
        return name;
    }

    public int getSeasonCount() {
        return seasonCount;
    }

    public void setSeasonCount(int seasonCount) {
        this.seasonCount = seasonCount;
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

    /**Season season : currentSerie.getSeasons()
     * Setter for description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}