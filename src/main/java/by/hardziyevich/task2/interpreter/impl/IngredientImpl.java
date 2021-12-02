package by.hardziyevich.task2.interpreter.impl;

import by.hardziyevich.task2.exeption.SomeException;
import by.hardziyevich.task2.interpreter.InterpreterCandies;
import by.hardziyevich.task2.validator.Validator;
import by.hardziyevich.task2.validator.ValidatorData;

public class IngredientImpl implements InterpreterCandies {
    private double water;
    private double sugar;
    private double fructose;
    private double vanilla;

    private static final String REG_DOUBLE = "0[.][0-9]+";

    public double getWater() {
        return water;
    }

    public double getSugar() {
        return sugar;
    }

    public double getFructose() {
        return fructose;
    }

    public double getVanilla() {
        return vanilla;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public void setFructose(double fructose) {
        this.fructose = fructose;
    }

    public void setVanilla(double vanilla) {
        this.vanilla = vanilla;
    }

    @Override
    public void interpret(String tag, String data) throws SomeException {
        if (!Validator.of(tag).isCorrect()) {
            throw new SomeException(tag + " is null!");
        }
        switch (tag) {
            case "water":
                water = ValidatorData.of(data).getDouble(REG_DOUBLE, water);
                break;
            case "sugar":
                sugar = ValidatorData.of(data).getDouble(REG_DOUBLE, sugar);
                break;
            case "fructose":
                fructose = ValidatorData.of(data).getDouble(REG_DOUBLE, fructose);
                break;
            case "vanilla":
                vanilla = ValidatorData.of(data).getDouble(REG_DOUBLE, vanilla);
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientImpl that = (IngredientImpl) o;
        return Double.compare(that.water, water) == 0 && Double.compare(that.sugar, sugar) == 0 && Double.compare(that.fructose, fructose) == 0 && Double.compare(that.vanilla, vanilla) == 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Double.hashCode(water);
        result = prime * result + Double.hashCode(sugar);
        result = prime * result + Double.hashCode(fructose);
        result = prime * result + Double.hashCode(vanilla);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("IngredientImpl{");
        sb.append("water=").append(water);
        sb.append(", sugar=").append(sugar);
        sb.append(", fructose=").append(fructose);
        sb.append(", vanilla=").append(vanilla);
        sb.append("}");
        return sb.toString();
    }
}
