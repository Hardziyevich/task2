package interpreter;

import by.hardziyevich.task2.exeption.SomeException;
import by.hardziyevich.task2.interpreter.PropertyCandy;
import org.junit.jupiter.api.Test;

import static by.hardziyevich.task2.interpreter.PropertyCandy.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PropertyCandyTest {

    @Test
    void testInterpret() throws SomeException {
        Builder builder = new Builder();
        builder.interpret("id","14");
        builder.interpret("energy","114");
        builder.interpret("name-candy","pasha");
        builder.interpret("production","pasha");
        builder.interpret("shelf-life","2002-05-30T09:00:00");
        PropertyCandy build = builder.build();
        System.out.println(build.getShelfLife());
        assertAll(()->{
            assertEquals(14,build.getId());
            assertEquals(114,build.getEnergy());
            assertEquals("pasha",build.getNameCandy());
            assertEquals("pasha",build.getProduction());
            assertEquals("Thu May 30 09:00:00 MSD 2002",build.getShelfLife().toString());
            assertThrows(SomeException.class,()->{
                builder.interpret(null,"14");
                builder.interpret("energy",null);
                builder.interpret("id","randomData");
                builder.interpret("energy","randomData");
            });
            assertDoesNotThrow(()->builder.interpret("randomData","randomData"));
        });
    }
}
