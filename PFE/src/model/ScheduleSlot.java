package model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Class representing a Schedule Slot
 * @author pjlacharite
 *
 */
@Root
public class ScheduleSlot {
    @Attribute
    private String serieId;
    @Attribute
    private String broadcasterId;
    @Attribute
    private String duration;
    @Attribute
    private String title;
    @Attribute
    private String episodeTitle;
    @Attribute
    private String airingTime;
    @Attribute
    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public ScheduleSlot(){
    }

    @Override
    public int hashCode(){
        return Integer.parseInt(broadcasterId);
    }

    public String getSerieId() {
        return serieId;
    }
    public void setSerieId(String serieId) {
        this.serieId = serieId;
    }
    public String getBroadcasterId() {
        return broadcasterId;
    }
    public void setBroadcasterId(String broadcasterId) {
        this.broadcasterId = broadcasterId;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getEpisodeTitle() {
        return episodeTitle;
    }
    public void setEpisodeTitle(String episodeTitle) {
        this.episodeTitle = episodeTitle;
    }
    public String getAiringTime() {
        return airingTime;
    }
    public void setAiringTime(String airingTime) {
        this.airingTime = airingTime;
    }
}
