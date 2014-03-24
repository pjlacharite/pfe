package model;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class BroadcasterList {
    @ElementList
    private List<Broadcaster> broadcasters = null;
    
    public BroadcasterList(){
        broadcasters = new ArrayList<Broadcaster>();
    }

    public List<Broadcaster> getBroadcasters() {
        return broadcasters;
    }

    public void addBroadcasters(Broadcaster broadcaster) {
        this.broadcasters.add(broadcaster);
    }
}
