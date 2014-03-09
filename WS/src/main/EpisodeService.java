package main;

import java.io.StringWriter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import mock.EpisodeMock;
import model.Episode;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

@Path("/episodeservice")
public class EpisodeService {
    // This method is called if XML is request
    @GET
    @Produces(MediaType.TEXT_XML)
    public String getSerieXml(@QueryParam("serieId") String serieId, @QueryParam("seasonNumber") String seasonNumber, @QueryParam("episodeNumber") String episodeNumber) {
        Serializer serializer = new Persister();
        StringWriter writer = new StringWriter();
        try {
          Episode episode = EpisodeMock.mockEpisodes().get(Integer.parseInt(serieId));
          serializer.write(episode, writer);
          System.out.println(writer.getBuffer());
        } catch (Exception e) {
          e.printStackTrace();
        }
        return "<?xml version=\"1.0\"?>" + writer.getBuffer();
    }
}