package main;

import java.io.StringWriter;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Broadcaster;
import model.BroadcasterList;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import persistence.BroadcasterDAO;

@Path("/broadcasterservice")
public class BroadcasterService {
    // This method is called if XML is request
    @GET
    @Produces(MediaType.TEXT_XML)
    public String getBroadcasterXml() {
        Serializer serializer = new Persister();
        StringWriter writer = new StringWriter();
        try {
          BroadcasterDAO broadcasterDAO = new BroadcasterDAO();
          List<Broadcaster> broadcasters = broadcasterDAO.findAll(null);
          BroadcasterList broadcasterList = new BroadcasterList();
          for (Broadcaster broadcaster: broadcasters){
              broadcasterList.addBroadcasters(broadcaster);
          }
          broadcasterDAO.releaseConnection();
          serializer.write(broadcasterList, writer);
          System.out.println(writer.getBuffer());
        } catch (Exception e) {
          e.printStackTrace();
        }
        return "<?xml version=\"1.0\"?>" + writer.getBuffer();
    }
}
