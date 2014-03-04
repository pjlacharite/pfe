package main;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/serieslistservice")
public class SeriesListService {

      //This method is called if JSON is request
      @GET
      @Produces(MediaType.APPLICATION_JSON)
      public String getSerieListJson() {
        return "Hello Jersey";
      }

      // This method is called if XML is request
      @GET
      @Produces(MediaType.TEXT_XML)
      public String getSerieListXml() {
          return "<?xml version=\"1.0\"?>"/*+ writer.getBuffer()*/;
      }

    }
