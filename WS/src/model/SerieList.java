package model;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class SerieList {
    @ElementList(name="seriesName")
    private List<String> seriesName = null;

    public SerieList() {
        seriesName = new ArrayList<String>();
    }

    public List<String> getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(List<String> seriesName) {
        this.seriesName = seriesName;
    }

    public SerieList(List<String> seriesName){
        this.seriesName = seriesName;
    }

    public void addSerieName(String serieName){
        seriesName.add(serieName);
    }
}
