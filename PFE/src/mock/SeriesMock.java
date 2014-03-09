package mock;

import java.util.ArrayList;
import java.util.List;

import model.Serie;

public class SeriesMock {
    
    public static List<Serie> mockSeries(){
        List<Serie> seriesList = new ArrayList<Serie>();
        Serie serie1 = new Serie("1", SeasonMock.mockSeasons().size(), "How I Met Your Mother", "The series revolves around Ted Mosby narrating the story of how he met his wife to his children.");
        Serie serie2 = new Serie("2", SeasonMock.mockSeasons().size(), "The Big Bang Theory", "The Big Bang Theory is centered on physicists Sheldon Cooper and Leonard Hofstadter, whose geeky and introverted lives are changed when Penny, an attractive waitress and aspiring actress, moves into the apartment across from theirs.");
        Serie serie3 = new Serie("3", SeasonMock.mockSeasons().size(), "Californication", "David Duchovny is the lead in this comedy about a book writer always running into trouble with sex, drugs and women, while struggling to maintain a relationship with his ex-girlfriend and trying to raise his daughter in a less than normal environment for a teenager.");
        seriesList.add(serie1);
        seriesList.add(serie2);
        seriesList.add(serie3);

        return seriesList;
    }
}
