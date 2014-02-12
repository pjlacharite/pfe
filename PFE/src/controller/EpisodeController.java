package controller;

import java.util.List;

import mock.EpisodeMock;
import model.Episode;

public class EpisodeController implements Controller{

    public List<Episode> fetchAllEpisodes(String serieId, String seasonId){
        List<Episode> episodeList = EpisodeMock.mockEpisodes();
        return episodeList;
    }
    
    public Episode getEpisode(String serieId, String seasonId, String episodeId){
        List<Episode> episodeList = fetchAllEpisodes(serieId, seasonId);
        for (Episode episode: episodeList){
            if (episode.getId().equals(episodeId)){
                return episode;
            }
        }
        return null;
    }
}
