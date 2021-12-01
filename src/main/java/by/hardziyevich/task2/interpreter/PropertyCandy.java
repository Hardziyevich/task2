package by.hardziyevich.task2.interpreter;

import by.hardziyevich.task2.entity.CaramelCandyType;
import by.hardziyevich.task2.entity.ChocolateCandyType;
import by.hardziyevich.task2.entity.ChocolateFillingType;
import by.hardziyevich.task2.exeption.SomeException;
import by.hardziyevich.task2.interpreter.impl.IngredientImpl;
import by.hardziyevich.task2.interpreter.impl.NutritionalValueImpl;
import by.hardziyevich.task2.validator.Validator;
import by.hardziyevich.task2.validator.ValidatorData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PropertyCandy {
    private final long id;
    private final int energy;
    private final String nameCandy;
    private final String production;
    private final IngredientImpl ingredient;
    private final NutritionalValueImpl nutritionalValue;
    private final CaramelCandyType caramelCandyType;
    private final ChocolateCandyType chocolateCandyType;
    private final ChocolateFillingType chocolateFillingType;
    private final Date shelfLife;

    private PropertyCandy(Builder builder) {
        this.id = builder.id;
        this.energy = builder.energy;
        this.nameCandy = builder.nameCandy;
        this.production = builder.production;
        this.ingredient = builder.ingredient;
        this.nutritionalValue = builder.nutritionalValue;
        this.shelfLife = builder.shelfLife;
        this.caramelCandyType = builder.caramelCandyType;
        this.chocolateCandyType = builder.chocolateCandyType;
        this.chocolateFillingType = builder.chocolateFillingType;
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

    public CaramelCandyType getCaramelCandyType() {
        return caramelCandyType;
    }

    public ChocolateCandyType getChocolateCandyType() {
        return chocolateCandyType;
    }

    public ChocolateFillingType getChocolateFillingType() {
        return chocolateFillingType;
    }

    public static class Builder implements InterpreterCandies {
        private long id;
        private int energy;
        private String nameCandy;
        private String production;
        private CaramelCandyType caramelCandyType;
        private ChocolateCandyType chocolateCandyType;
        private ChocolateFillingType chocolateFillingType;
        private IngredientImpl ingredient;
        private NutritionalValueImpl nutritionalValue;
        private Date shelfLife;

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

        public Builder caramelCandyType(CaramelCandyType caramelCandyType) {
            this.caramelCandyType = caramelCandyType;
            return this;
        }

        public Builder chocolateCandyType(ChocolateCandyType chocolateCandyType) {
            this.chocolateCandyType = chocolateCandyType;
            return this;
        }

        public Builder chocolateFillingType(ChocolateFillingType chocolateFillingType) {
            this.chocolateFillingType = chocolateFillingType;
            return this;
        }

        public PropertyCandy build() {
            return new PropertyCandy(this);
        }

        @Override
        public void interpret(String tag, String data) throws SomeException {
            if (!Validator.of(tag).isCorrect()) {
                throw new SomeException(tag + "is null!");
            }
            switch (tag) {
                case "id":
                    id = Long.parseLong(data);
                    break;
                case "energy":
                    energy = ValidatorData.of(data).getInteger(energy);
                    break;
                case "name":
                    nameCandy = data;
                    break;
                case "production":
                    production = data;
                    break;
                case "data":
                    shelfLife = stringToData(data);
                    break;
                case "chocolate-filling":
                    chocolateFillingType = ChocolateFillingType.valueOf(replaceIfPresent(data));
                    break;
                case "chocolate-type":
                    chocolateCandyType = ChocolateCandyType.valueOf(replaceIfPresent(data));
                    break;
                case "candy-type":
                    caramelCandyType = CaramelCandyType.valueOf(data.toUpperCase());
                    break;
                default: {
                    if (ingredient != null) {
                        ingredient.interpret(tag, data);
                    }
                    if (nutritionalValue != null) {
                        nutritionalValue.interpret(tag, data);
                    }
                }
            }
        }

        private String replaceIfPresent(String s) {
            final String HYPHEN = "-";
            final String UNDERSCORE = "_";
            Pattern pattern = Pattern.compile("-?");
            Matcher matcher = pattern.matcher(s);
            return matcher.replaceAll(MatchResult::group).replace(HYPHEN, UNDERSCORE).toUpperCase();
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
