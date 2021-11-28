package parser;

import by.hardziyevich.task2.exeption.SomeException;
import by.hardziyevich.task2.parser.CandyParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CandyParserTest {
    private Path path;

    @BeforeAll
    void init(){
        URL is = getClass().getClassLoader().getResource("test.xml");
        File file = new File(is.getFile());
        path = file.toPath();
    }

    @Test
    void testParser() throws SomeException {
        CandyParser parser = new CandyParser();
        assertAll(()->{
            assertNotNull(parser.parse(path));
            assertThrows(IllegalStateException.class,()->parser.parse(Path.of(".txt")));
            assertThrows(NullPointerException.class,()->parser.parse(null));
        });
    }
}
