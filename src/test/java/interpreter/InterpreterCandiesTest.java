package interpreter;

import org.junit.jupiter.api.Test;

import static by.hardziyevich.task2.interpreter.InterpreterCandies.CandyXmlTag.*;
import static by.hardziyevich.task2.interpreter.InterpreterCandies.CandyXmlTag.CARAMEL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

class InterpreterCandiesTest {
    @Test
    void testConvert() {
        assertAll(() -> {
            assertThat(CARAMEL,equalTo(convert("caramel")));
            assertThat(CHOCOLATE,equalTo(convert("chocolate")));
            assertThat(INGREDIENT,equalTo(convert("ingredient")));
            assertThat(ID,equalTo(convert("id")));
            assertThat(ENERGY,equalTo(convert("energy")));
            assertThat(NAME,equalTo(convert("name")));
            assertThat(PRODUCTION,equalTo(convert("production")));
            assertThat(VALUE,equalTo(convert("value")));
            assertThat(FAT,equalTo(convert("fat")));
            assertThat(PROTEIN,equalTo(convert("protein")));
            assertThat(CARB,equalTo(convert("carb")));
            assertThat(DATA,equalTo(convert("data")));
            assertNull(convert(null));
        });
    }
}
