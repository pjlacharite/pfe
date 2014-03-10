package main;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import persistence.DatabaseUtils;

@Path("/serieslistservice")
public class SeriesListService {

      // This method is called if XML is request
      @GET
      @Produces(MediaType.TEXT_XML)
      public String getSerieListXml() {
          
          /*THIS IS A REQUEST TEST*/
          Connection connection = DatabaseUtils.getConnection();
          try {
              String sql = "SELECT testField FROM test";
              PreparedStatement statement = connection.prepareStatement(sql);
              ResultSet resultSet = statement.executeQuery();
              System.out.println("Query somehow executed");
              while (resultSet.next()){
                  String id = resultSet.getString(1);
                  System.out.println(id);
              }
          } catch (SQLException e) {
              System.out.println(e.getLocalizedMessage());
          }
          

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
