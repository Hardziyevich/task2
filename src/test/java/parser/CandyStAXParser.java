package parser;

import by.hardziyevich.task2.exeption.SomeException;
import by.hardziyevich.task2.factory.ParserBuilderFactory;
import by.hardziyevich.task2.parser.CandyParser;
import by.hardziyevich.task2.parser.CandySAXParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CandyStAXParser {
    private Path path;
    @BeforeAll
    void init(){
        URL is = getClass().getClassLoader().getResource("Candy.xml");
        File file = new File(is.getFile());
        path = file.toPath();
    }

    @Test
    void testParser() throws SomeException {
        CandyParser parser = ParserBuilderFactory.newParserBuilder(ParserBuilderFactory.TypeParser.StAX);
        assertAll(()->{
            assertNotNull(parser.parse(path));
            assertNotNull(parser.mainParseCandy(path));
            assertThrows(SomeException.class,()->parser.mainParseCandy(Path.of(".txt")));
            assertThrows(SomeException.class,()->parser.mainParseCandy(null));
        });
    }

}
