package by.hardziyevich.task2.interpreter;

import by.hardziyevich.task2.entity.impl.CaramelCandyTypeImpl;
import by.hardziyevich.task2.entity.impl.ChocolateCandyTypeImpl;
import by.hardziyevich.task2.entity.impl.ChocolateFillingTypeImpl;
import by.hardziyevich.task2.entity.Type;
import by.hardziyevich.task2.exeption.SomeException;
import by.hardziyevich.task2.interpreter.impl.IngredientImpl;
import by.hardziyevich.task2.interpreter.impl.NutritionalValueImpl;
import by.hardziyevich.task2.validator.Validator;
import by.hardziyevich.task2.validator.ValidatorData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class PropertyCandy {
    private final long id;
    private final int energy;
    private final String nameCandy;
    private final String production;
    private final IngredientImpl ingredient;
    private final NutritionalValueImpl nutritionalValue;
    private final CaramelCandyTypeImpl caramelCandyTypeImpl;
    private final ChocolateCandyTypeImpl chocolateCandyTypeImpl;
    private final ChocolateFillingTypeImpl chocolateFillingTypeImpl;
    private final Date shelfLife;

    private PropertyCandy(Builder builder) {
        this.id = builder.id;
        this.energy = builder.energy;
        this.nameCandy = builder.nameCandy;
        this.production = builder.production;
        this.ingredient = builder.ingredient;
        this.nutritionalValue = builder.nutritionalValue;
        this.shelfLife = builder.shelfLife;
        this.caramelCandyTypeImpl = builder.caramelCandyTypeImpl;
        this.chocolateCandyTypeImpl = builder.chocolateCandyTypeImpl;
        this.chocolateFillingTypeImpl = builder.chocolateFillingTypeImpl;
    }

    public long getId() {
        return id;
    }

    public int getEnergy() {
        return energy;
    }

    public String getNameCandy() {
        return nameCandy;
    }

    public String getProduction() {
        return production;
    }

    public IngredientImpl getIngredient() {
        return ingredient;
    }

    public NutritionalValueImpl getNutritionalValue() {
        return nutritionalValue;
    }

    public Date getShelfLife() {
        return shelfLife;
    }

    public CaramelCandyTypeImpl getCaramelCandyType() {
        return caramelCandyTypeImpl;
    }

    public ChocolateCandyTypeImpl getChocolateCandyType() {
        return chocolateCandyTypeImpl;
    }

    public ChocolateFillingTypeImpl getChocolateFillingType() {
        return chocolateFillingTypeImpl;
    }

    public static class Builder implements InterpreterCandies {
        private long id;
        private int energy;
        private String nameCandy;
        private String production;
        private CaramelCandyTypeImpl caramelCandyTypeImpl;
        private ChocolateCandyTypeImpl chocolateCandyTypeImpl;
        private ChocolateFillingTypeImpl chocolateFillingTypeImpl;
        private IngredientImpl ingredient;
        private NutritionalValueImpl nutritionalValue;
        private Date shelfLife;

        private static final String REG_DIGIT = "\\d+";

        public Builder ingredient(IngredientImpl ingredient) {
            this.ingredient = ingredient;
            return this;
        }

        public Builder nutritionalValue(NutritionalValueImpl nutritionalValue) {
            this.nutritionalValue = nutritionalValue;
            return this;
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder energy(int energy) {
            this.energy = energy;
            return this;
        }

        public Builder nameCandy(String nameCandy) {
            this.nameCandy = nameCandy;
            return this;
        }

        public Builder production(String production) {
            this.production = production;
            return this;
        }

        public Builder shelfLife(Date shelfLife) {
            this.shelfLife = shelfLife;
            return this;
        }

        public Builder caramelCandyType(CaramelCandyTypeImpl caramelCandyTypeImpl) {
            this.caramelCandyTypeImpl = caramelCandyTypeImpl;
            return this;
        }

        public Builder chocolateCandyType(ChocolateCandyTypeImpl chocolateCandyTypeImpl) {
            this.chocolateCandyTypeImpl = chocolateCandyTypeImpl;
            return this;
        }

        public Builder chocolateFillingType(ChocolateFillingTypeImpl chocolateFillingTypeImpl) {
            this.chocolateFillingTypeImpl = chocolateFillingTypeImpl;
            return this;
        }

        public PropertyCandy build() {
            return new PropertyCandy(this);
        }

        @Override
        public void interpret(String tag, String data) throws SomeException {
            tag = Validator.of(tag).get();
            switch (tag) {
                case "id":
                    data = Validator.of(data).validate(x -> x.matches(REG_DIGIT), "It isn`t long").get();
                    id = Long.parseLong(data);
                    break;
                case "energy":
                    energy = ValidatorData.of(data).getInteger(energy);
                    break;
                case "name-candy":
                    data = Validator.of(data).get();
                    nameCandy = data;
                    break;
                case "production":
                    data = Validator.of(data).get();
                    production = data;
                    break;
                case "shelf-life":
                    data = Validator.of(data).get();
                    shelfLife = stringToData(data);
                    break;
                case "chocolate-filling":
                    chocolateFillingTypeImpl = (ChocolateFillingTypeImpl)Type.convert(ChocolateFillingTypeImpl.values(),data);
                    break;
                case "chocolate":
                    chocolateCandyTypeImpl = (ChocolateCandyTypeImpl)Type.convert(ChocolateCandyTypeImpl.values(),data);
                    break;
                case "candy-type":
                    caramelCandyTypeImpl = (CaramelCandyTypeImpl)Type.convert(CaramelCandyTypeImpl.values(),data);
                    break;
                default:{
                    if(ingredient != null) {
                        ingredient.interpret(tag, data);
                    }
                    if(nutritionalValue != null){
                        nutritionalValue.interpret(tag, data);
                    }
                }
            }
        }

        public Date stringToData(String string) throws SomeException {
            string = string.replaceAll("T", " ");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date;
            try {
                date = sdf.parse(string);
            } catch (ParseException e) {
                throw new SomeException(e.getMessage());
            }
            return date;
        }
    }
}
