package by.hardziyevich.task2.interpreter;

import by.hardziyevich.task2.exeption.SomeException;

import java.util.Arrays;

@FunctionalInterface
public interface InterpreterCandies {

    void interpret(String tag, String data) throws SomeException;

    enum CandyXmlTag {
        //attribute
        INGREDIENT("ingredient"),
        CARAMEL("tns:Caramel"),
        CHOCOLATE("tns:Chocolate"),
        //tag
        ID("id"),
        ENERGY("energy"),
        NAME_CANDY("name-candy"),
        PRODUCTION("production"),
        VALUE("value"),
        FAT("fat"),
        PROTEIN("protein"),
        CARB("carb"),
        SHELF_LIFE("shelf-life");

        private final String tag;

        CandyXmlTag(final String tag) {
            this.tag = tag;
        }

        public String getTag() {
            return tag;
        }


        public static CandyXmlTag convert(String value) {
            return Arrays.stream(CandyXmlTag.values())
                    .filter(x -> x.getTag().equals(value))
                    .findFirst()
                    .orElse(null);
        }
    }
}
