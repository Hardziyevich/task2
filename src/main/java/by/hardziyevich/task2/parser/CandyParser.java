package by.hardziyevich.task2.parser;

import by.hardziyevich.task2.entity.Candy;
import by.hardziyevich.task2.exeption.SomeException;
import by.hardziyevich.task2.validator.Validator;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class CandyParser {
    private static final SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    private static final CandyHandler defaultHandler = new CandyHandler();

    public List<Candy> parse(Path path) throws SomeException {
        path = Validator.of(path)
                .validate(x -> !x.endsWith(".xml"),"It isn`t xml")
                .validate(x -> x.toFile().exists(),"File does not exists").get();
        try {
            SAXParser parser = saxParserFactory.newSAXParser();
            parser.parse(path.toFile(),defaultHandler);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new SomeException(e.getMessage());
        }
        return defaultHandler.getCandies();
    }
}
