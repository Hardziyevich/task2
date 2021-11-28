package by.hardziyevich.task2.interpreter;

import by.hardziyevich.task2.exeption.SomeException;
import by.hardziyevich.task2.validator.Validator;

@FunctionalInterface
public interface InterpreterCandies {

    void interpret(String tag,String data) throws SomeException;

    enum CandyXmlTag{
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

        CandyXmlTag(final String tag){
            this.tag = tag;
        }

        public String getTag() {
            return tag;
        }


        public static CandyXmlTag convert(String value){
            CandyXmlTag current = null;
            if(CARAMEL.getTag().equalsIgnoreCase(value)){
                current = CARAMEL;
            }
            else if(CHOCOLATE.getTag().equalsIgnoreCase(value)){
                current = CHOCOLATE;
            }
            else if(CHOCOLATE_FILLING.getTag().equalsIgnoreCase(value)){
                current = CHOCOLATE_FILLING;
            }
            else if(INGREDIENT.getTag().equalsIgnoreCase(value)){
                current = INGREDIENT;
            }
            else if(ID.getTag().equalsIgnoreCase(value)){
                current = ID;
            }
            else if(ENERGY.getTag().equalsIgnoreCase(value)){
                current = ENERGY;
            }
            else if(PRODUCTION.getTag().equalsIgnoreCase(value)){
                current = PRODUCTION;
            }
            else if(NAME_CANDY.getTag().equalsIgnoreCase(value)){
                current = NAME_CANDY;
            }
            else if(VALUE.getTag().equalsIgnoreCase(value)){
                current = VALUE;
            }
            else if(FAT.getTag().equalsIgnoreCase(value)){
                current = FAT;
            }
            else if(PROTEIN.getTag().equalsIgnoreCase(value)){
                current = PROTEIN;
            }
            else if(CARB.getTag().equalsIgnoreCase(value)){
                current = CARB;
            }
            else if(SHELF_LIFE.getTag().equalsIgnoreCase(value)){
                current = SHELF_LIFE;
            }
            return current;
        }
    }
}
