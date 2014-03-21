package main;

import java.io.StringWriter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.Season;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import persistence.SeasonDAO;

@Path("/seasonservice")
public class SeasonService {
    // This method is called if XML is request
    @GET
    @Produces(MediaType.TEXT_XML)
    public String getSerieXml(@QueryParam("serieId") String serieId, @QueryParam("seasonNumber") String seasonNumber) {
        Serializer serializer = new Persister();
        StringWriter writer = new StringWriter();
        try {
          SeasonDAO seasonDAO = new SeasonDAO(); 
          Season season = seasonDAO.find(serieId + "-" + seasonNumber);
          seasonDAO.releaseConnection();
          System.out.println(season.getId());
          //Season season = SeasonMock.mockSeasons().get(Integer.parseInt(serieId));
          serializer.write(season, writer);
          System.out.println(writer.getBuffer());
        } catch (Exception e) {
          e.printStackTrace();
        }
        return "<?xml version=\"1.0\"?>" + writer.getBuffer();
    }
}