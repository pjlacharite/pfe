package main;
import java.io.StringWriter;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mock.SeriesMock;
import model.Serie;
import model.SerieList;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

@Path("/serieslistservice")
public class SeriesListService {

      // This method is called if XML is request
      @GET
      @Produces(MediaType.TEXT_XML)
      public String getSerieListXml() {
          Serializer serializer = new Persister();
          StringWriter writer = new StringWriter();
          SerieList serieList = new SerieList();
          try {
            List<Serie> series = SeriesMock.mockSeries();
            for (Serie serie: series){
                serieList.addSerieName(serie.getName());
            }
            serializer.write(serieList, writer);
            System.out.println(writer.getBuffer());
          } catch (Exception e) {
            e.printStackTrace();
          }
          return "<?xml version=\"1.0\"?>" + writer.getBuffer();
      }
    }
