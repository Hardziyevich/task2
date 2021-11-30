package by.hardziyevich.task2.parser;

import by.hardziyevich.task2.entity.Candy;
import by.hardziyevich.task2.exeption.SomeException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class CandySAXParser extends CandyParser{
    private final SAXParserFactory saxParserFactory;
    private final CandyHandler defaultHandler;

    public CandySAXParser(){
        super();
        saxParserFactory = SAXParserFactory.newInstance();
        defaultHandler = new CandyHandler();
    }
    public List<Candy> parse(Path path) throws SomeException {
        try {
            SAXParser parser = saxParserFactory.newSAXParser();
            parser.parse(path.toFile(),defaultHandler);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new SomeException(e.getMessage());
        }
        return defaultHandler.getCandies();
    }
}
