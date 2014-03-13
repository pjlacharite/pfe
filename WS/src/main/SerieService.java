package main;
import java.io.StringWriter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.Serie;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import persistence.SerieDAO;

@Path("/serieservice")
public class SerieService {
    // This method is called if XML is request
    @GET
    @Produces(MediaType.TEXT_XML)
    public String getSerieXml(@QueryParam("serieId") String serieId) {
        Serializer serializer = new Persister();
        StringWriter writer = new StringWriter();
        try {
          Serie serie = new SerieDAO().find(serieId);
          serializer.write(serie, writer);
          System.out.println(writer.getBuffer());
        } catch (Exception e) {
          e.printStackTrace();
        }
        return "<?xml version=\"1.0\"?>" + writer.getBuffer();
    }
}
