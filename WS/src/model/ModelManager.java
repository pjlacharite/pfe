package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.BroadcasterDAO;
import persistence.EpisodeDAO;
import persistence.ScheduleSlotDAO;
import persistence.SeasonDAO;
import persistence.SerieDAO;

public class ModelManager {
    private static ModelManager instance = null;
    private List<Serie> series;
    private List<Season> seasons;
    private List<Episode> episodes;
    private List<ScheduleSlot> scheduleSlots;
    private List<Broadcaster> broadcasters;

    private ModelManager(){
        series = new ArrayList<Serie>();
        seasons = new ArrayList<Season>();
        episodes = new ArrayList<Episode>();
        scheduleSlots = new ArrayList<ScheduleSlot>();
        broadcasters = new ArrayList<Broadcaster>();
    }

    public static ModelManager getInstance(){
        if (instance == null){
            instance = new ModelManager();
        }
        return instance;
    }

    public void processModels(){
        //Call the cleaning lady.
        ModelCleaner modelCleaner = new ModelCleaner();
        System.out.println("Cleaning models");
        modelCleaner.cleanScheduleSlots(scheduleSlots, series);
        //Call the DAOs
        SerieDAO serieDAO = new SerieDAO();
        System.out.println("Processing series into database");
        for (Serie serie: series){
            serieDAO.create(serie);
        }
        SeasonDAO seasonDAO = new SeasonDAO();
        System.out.println("Processing seasons into database");
        for (Season season: seasons){
            seasonDAO.create(season);
        }
        EpisodeDAO episodeDAO = new EpisodeDAO();
        System.out.println("Processing episodes into database");
        for (Episode episode: episodes){
            episodeDAO.create(episode);
        }
        BroadcasterDAO broadcasterDAO = new BroadcasterDAO();
        System.out.println("Processing broadcasters into database");
        for (Broadcaster broadcaster: broadcasters){
            broadcasterDAO.create(broadcaster);
        }
        ScheduleSlotDAO scheduleSlotDAO = new ScheduleSlotDAO();
        System.out.println("Processing scheduleSlots into database");
        for (ScheduleSlot scheduleSlot: scheduleSlots){
            scheduleSlotDAO.create(scheduleSlot);
        }
        try {
            serieDAO.releaseConnection();
            seasonDAO.releaseConnection();
            episodeDAO.releaseConnection();
            scheduleSlotDAO.releaseConnection();
            broadcasterDAO.releaseConnection();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

    public List<Serie> getSeries() {
        return series;
    }
    public void addSeries(List<Serie> series) {
        this.series.addAll(series);
    }
    public List<Season> getSeasons() {
        return seasons;
    }
    public void addSeasons(List<Season> seasons) {
        this.seasons.addAll(seasons);
    }
    public List<Episode> getEpisodes() {
        return episodes;
    }
    public void addEpisodes(List<Episode> episodes) {
        this.episodes.addAll(episodes);
    }
    public List<ScheduleSlot> getScheduleSlots() {
        return scheduleSlots;
    }
    public void addScheduleSlots(List<ScheduleSlot> scheduleSlots) {
        this.scheduleSlots.addAll(scheduleSlots);
    }
    public List<Broadcaster> getBroadcasters() {
        return broadcasters;
    }
    public void addBroadcasters(List<Broadcaster> broadcasters) {
        this.broadcasters.addAll(broadcasters);
    }
}
