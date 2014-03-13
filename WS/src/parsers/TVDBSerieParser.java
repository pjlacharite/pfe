package parsers;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.Serie;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class TVDBSerieParser{

    public Serie parse(String input) {
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Serie serie = new Serie();
        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(input));
            Document document = builder.parse(inputSource);

            NodeList nodes = document.getElementsByTagName("seriesid");
            serie.setId(nodes.item(0).getTextContent());
            System.out.println(serie.getId());
            nodes = document.getElementsByTagName("SeriesName");
            serie.setName(nodes.item(0).getTextContent());
            System.out.println(serie.getName());
            nodes = document.getElementsByTagName("Overview");
            serie.setDescription(nodes.item(0).getTextContent());
            System.out.println(serie.getDescription());
            return serie;
        } catch (ParserConfigurationException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (SAXException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return null;
    }

}
