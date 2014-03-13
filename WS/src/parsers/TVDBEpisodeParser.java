package parsers;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.Episode;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class TVDBEpisodeParser{

    public List<Episode> parse(String input) {
        List<Episode> episodes = new ArrayList<Episode>();
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(input));
            Document document = builder.parse(inputSource);
            NodeList nodes = document.getElementsByTagName("Episode");
            for (int i = 0; i < nodes.getLength(); i++){
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    Episode episode = new Episode();
                    episode.setId(element.getElementsByTagName("id").item(0).getTextContent());
                    episode.setEpisodeName(element.getElementsByTagName("EpisodeName").item(0).getTextContent());
                    episode.setEpisodeDescription(element.getElementsByTagName("Overview").item(0).getTextContent());
                    String date = element.getElementsByTagName("FirstAired").item(0).getTextContent();
                    if (!date.isEmpty()){
                        episode.setOriginalAirDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
                    }
                    
                    episode.setEpisodeNumber(Integer.parseInt(element.getElementsByTagName("EpisodeNumber").item(0).getTextContent()));
                    episode.setSeasonNumber(Integer.parseInt(element.getElementsByTagName("SeasonNumber").item(0).getTextContent()));
                    /*System.out.println(episode.getId());
                    System.out.println(episode.getEpisodeName());
                    System.out.println(episode.getEpisodeNumber());
                    System.out.println(episode.getSeasonNumber());*/
                    if (episode.getSeasonNumber() == 0){
                        continue;
                    }
                    episodes.add(episode);
                }
            }
        } catch (ParserConfigurationException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (SAXException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (DOMException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (ParseException e) {
            System.out.println(e.getLocalizedMessage());
        }
        System.out.println("Episode count: " + episodes.size());
        return episodes;
    }

}
