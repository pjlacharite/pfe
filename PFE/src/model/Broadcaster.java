package model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Class representing a Broadcaster
 * @author pjlacharite
 *
 */
@Root
public class Broadcaster {
    @Attribute
    private String id;
    @Attribute
    private String name;

    public Broadcaster(){
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
