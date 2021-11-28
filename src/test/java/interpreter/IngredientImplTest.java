package interpreter;

import by.hardziyevich.task2.exeption.SomeException;
import by.hardziyevich.task2.interpreter.impl.IngredientImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

class IngredientImplTest {

    @Test
    void testInterpret() throws SomeException {
        IngredientImpl test = new IngredientImpl();
        String[] tag = {"water","sugar","fructose","vanilla"};
        Arrays.stream(tag).forEach(x -> {
            try {
                test.interpret(x,"0.9");
            } catch (SomeException e) {
                e.printStackTrace();
            }
        });

        assertAll(()->{
            assertThrows(SomeException.class,()-> test.interpret("sugar","ASDASD"));
            assertThrows(SomeException.class,()-> test.interpret(null,"0.9"));
            assertThrows(SomeException.class,()-> test.interpret("sugar",null));
            assertDoesNotThrow(()-> test.interpret("`12`12`","kjlkj"));
            assertEquals(0.9,test.getFructose());
            assertEquals(0.9,test.getSugar());
            assertEquals(0.9,test.getVanilla());
            assertEquals(0.9,test.getWater());
        });

    }
}
