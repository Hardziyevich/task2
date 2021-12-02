package parser;

import by.hardziyevich.task2.exeption.SomeException;
import by.hardziyevich.task2.parser.CandySAXParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CandySAXParserTest {
    private Path path;

    @BeforeAll
    void init(){
        URL is = getClass().getClassLoader().getResource("Candy.xml");
        File file = new File(is.getFile());
        path = file.toPath();
    }

    @Test
    void testParser(){
        CandySAXParser parser = new CandySAXParser();
        assertAll(()->{
            assertNotNull(parser.mainParseCandy(path));
            assertThrows(SomeException.class,()->parser.mainParseCandy(Path.of(".txt")));
            assertThrows(SomeException.class,()->parser.mainParseCandy(null));
        });
    }
}
