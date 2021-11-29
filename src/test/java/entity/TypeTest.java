package entity;

import by.hardziyevich.task2.entity.Type;
import by.hardziyevich.task2.entity.impl.CaramelCandyTypeImpl;
import by.hardziyevich.task2.entity.impl.ChocolateCandyTypeImpl;
import by.hardziyevich.task2.entity.impl.ChocolateFillingTypeImpl;
import org.junit.jupiter.api.Test;

import static by.hardziyevich.task2.entity.impl.CaramelCandyTypeImpl.CANDY;
import static by.hardziyevich.task2.entity.impl.CaramelCandyTypeImpl.GLAZED;
import static by.hardziyevich.task2.entity.impl.ChocolateCandyTypeImpl.CHOCOLATE;
import static by.hardziyevich.task2.entity.impl.ChocolateCandyTypeImpl.CHOCOLATE_FILLING;
import static by.hardziyevich.task2.entity.impl.ChocolateFillingTypeImpl.NOT_WHITE;
import static by.hardziyevich.task2.entity.impl.ChocolateFillingTypeImpl.WHITE;
import static org.junit.jupiter.api.Assertions.*;

class TypeTest {


    @Test
    void testConvert() {
        assertEquals(GLAZED, Type.convert(CaramelCandyTypeImpl.values(), "glaze"));
        assertEquals(CANDY, Type.convert(CaramelCandyTypeImpl.values(), "candy"));
        assertEquals(CHOCOLATE, Type.convert(ChocolateCandyTypeImpl.values(), "chocolate"));
        assertEquals(CHOCOLATE_FILLING, Type.convert(ChocolateCandyTypeImpl.values(), "chocolate-filling"));
        assertEquals(WHITE, Type.convert(ChocolateFillingTypeImpl.values(), "white"));
        assertEquals(NOT_WHITE, Type.convert(ChocolateFillingTypeImpl.values(), "not-white"));
        assertDoesNotThrow(() -> Type.convert(ChocolateFillingTypeImpl.values(), null));
    }
}
