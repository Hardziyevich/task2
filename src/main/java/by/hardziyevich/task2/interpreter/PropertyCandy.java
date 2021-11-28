package by.hardziyevich.task2.interpreter;

import by.hardziyevich.task2.exeption.SomeException;
import by.hardziyevich.task2.validator.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class PropertyCandy {
    private final long id;
    private final int energy;
    private final String nameCandy;
    private final String production;
    private final List<InterpreterCandies> property;
    private final Date shelfLife;

    private PropertyCandy(Builder builder) {
        this.id = builder.id;
        this.energy = builder.energy;
        this.nameCandy = builder.nameCandy;
        this.production = builder.production;
        this.property = builder.property;
        this.shelfLife = builder.shelfLife;
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

    public List<InterpreterCandies> getProperty() {
        return List.copyOf(property);
    }

    public Date getShelfLife() {
        return shelfLife;
    }

    public static class Builder implements InterpreterCandies {
        private long id;
        private int energy;
        private String nameCandy;
        private String production;
        private final List<InterpreterCandies> property = new ArrayList<>();
        private Date shelfLife;

        private static final String REG_DIGIT = "\\d+";

        public Builder setProperty(InterpreterCandies interpreterCandies) {
            property.add(interpreterCandies);
            return this;
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setEnergy(int energy) {
            this.energy = energy;
            return this;
        }

        public Builder setNameCandy(String nameCandy) {
            this.nameCandy = nameCandy;
            return this;
        }

        public Builder setProduction(String production) {
            this.production = production;
            return this;
        }

        public Builder setShelfLife(Date shelfLife) {
            this.shelfLife = shelfLife;
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
                    data = Validator.of(data).validate(x -> x.matches(REG_DIGIT), "It isn`t integer").get();
                    energy = Integer.parseInt(data);
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
                default:
                    for (InterpreterCandies candies : property) {
                        candies.interpret(tag, data);
                    }
            }
        }

        public Date stringToData(String string) throws SomeException {
            string = string.replaceAll("T", " ");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = sdf.parse(string);
            } catch (ParseException e) {
                throw new SomeException(e.getMessage());
            }
            return date;
        }
    }
}
