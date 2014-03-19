package fetchers;

import java.util.ArrayList;
import java.util.List;

public class FetcherFactory {
    private List<Fetcher> fetchers = null;
    
    public FetcherFactory(){
        fetchers = new ArrayList<Fetcher>();
        //fetchers.add(new TVDBFetcher());
        fetchers.add(new ROVIFetcher());
    }

    public List<Fetcher> getFetchers(){
        return fetchers;
    }
}
