package main;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.Episode;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import persistence.EpisodeDAO;

@Path("/episodeservice")
public class EpisodeService {
    // This method is called if XML is request
    @GET
    @Produces(MediaType.TEXT_XML)
    public String getEpisodeXml(@QueryParam("serieId") String serieId, @QueryParam("seasonNumber") String seasonNumber, @QueryParam("episodeNumber") String episodeNumber) {
        Serializer serializer = new Persister();
        StringWriter writer = new StringWriter();
        try {
          EpisodeDAO episodeDAO = new EpisodeDAO();
          List<String> parameters = Arrays.asList(serieId, seasonNumber, episodeNumber);
          Episode episode = episodeDAO.findAll(parameters).get(0);
          //Episode episode = EpisodeMock.mockEpisodes().get(Integer.parseInt(serieId));
          episodeDAO.releaseConnection();
          serializer.write(episode, writer);
          System.out.println(writer.getBuffer());
        } catch (Exception e) {
          e.printStackTrace();
        }
        return "<?xml version=\"1.0\"?>" + writer.getBuffer();
    }
}