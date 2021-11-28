package by.hardziyevich.task2.interpreter;

import by.hardziyevich.task2.exeption.SomeException;
import by.hardziyevich.task2.validator.Validator;

@FunctionalInterface
public interface InterpreterCandies {

    void interpret(String tag, String data) throws SomeException;

    enum CandyXmlTag {
        //attribute
        INGREDIENT("ingredient"),
        CARAMEL("tns:Caramel"),
        CHOCOLATE("tns:Chocolate"),
        CHOCOLATE_FILLING("tns:Chocolate-filling"),
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
            CandyXmlTag current = null;
            for (CandyXmlTag candy : CandyXmlTag.values()) {
                if (candy.getTag().equals(value)) {
                    current = candy;
                }
            }
            return current;
        }
    }
}
