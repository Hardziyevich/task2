package interpreter;

import by.hardziyevich.task2.exeption.SomeException;
import by.hardziyevich.task2.interpreter.impl.IngredientImpl;
import by.hardziyevich.task2.interpreter.impl.NutritionalValueImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NutritionalValueImplTest {

    @Test
    void testInterpret(){
        NutritionalValueImpl test = new NutritionalValueImpl();
        String[] tag = {"fat","protein","carb"};
        Arrays.stream(tag).forEach(x -> {
            try {
                test.interpret(x,"1");
            } catch (SomeException e) {
                e.printStackTrace();
            }
        });

        assertAll(()->{
            assertDoesNotThrow(()-> test.interpret("fat","ASDASD"));
            assertThrows(SomeException.class,()-> test.interpret(null,"1"));
            assertDoesNotThrow(()-> test.interpret("fat",null));
            assertDoesNotThrow(()-> test.interpret("`12`12`","tyjty"));
            assertEquals(1,test.getCarb());
            assertEquals(1,test.getFat());
            assertEquals(1,test.getProtein());
        });

    }
}
