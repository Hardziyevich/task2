package by.hardziyevich.task2.interpreter;

import by.hardziyevich.task2.exeption.SomeException;

import java.util.Arrays;

@FunctionalInterface
public interface InterpreterCandies {

    void interpret(String tag, String data) throws SomeException;

    enum CandyXmlTag {
        //attribute
        INGREDIENT,
        CARAMEL,
        CHOCOLATE,
        //tag
        ID,
        ENERGY,
        NAME,
        PRODUCTION,
        VALUE,
        FAT,
        PROTEIN,
        CARB,
        DATA;

        public static CandyXmlTag convert(String value) {
            return Arrays.stream(CandyXmlTag.values())
                    .filter(x -> x.toString().equals(value))
                    .findFirst()
                    .orElse(null);
        }

        @Override
        public String toString() {
            String result = this.name();
            result = result.toLowerCase();
            return result;
        }

    }
}
