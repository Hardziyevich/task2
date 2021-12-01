package by.hardziyevich.task2.interpreter.impl;

import by.hardziyevich.task2.exeption.SomeException;
import by.hardziyevich.task2.interpreter.InterpreterCandies;
import by.hardziyevich.task2.validator.Validator;
import by.hardziyevich.task2.validator.ValidatorData;

public class NutritionalValueImpl implements InterpreterCandies {
    private int fat;
    private int protein;
    private int carb;
    private static final String REG_INTEGER = "\\d+";

    public int getFat() {
        return fat;
    }

    public int getProtein() {
        return protein;
    }

    public int getCarb() {
        return carb;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setCarb(int carb) {
        this.carb = carb;
    }

    @Override
    public void interpret(String tag, String data) throws SomeException {
        if(!Validator.of(tag).isCorrect()) {
            throw new SomeException(tag + "is null!");
        }
            switch (tag) {
                case "fat":
                    fat = ValidatorData.of(data).getInteger(REG_INTEGER, fat);
                    break;
                case "protein":
                    protein = ValidatorData.of(data).getInteger(REG_INTEGER, protein);
                    break;
                case "carb":
                    carb = ValidatorData.of(data).getInteger(REG_INTEGER, carb);
                    break;
            }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NutritionalValueImpl that = (NutritionalValueImpl) o;
        return fat == that.fat && protein == that.protein && carb == that.carb;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Integer.hashCode(fat);
        result = prime * result + Integer.hashCode(protein);
        result = prime * result + Integer.hashCode(carb);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("NutritionalValueImpl{");
        sb.append("fat=").append(fat);
        sb.append(", protein=").append(protein);
        sb.append(", carb=").append(carb);
        sb.append("}");
        return sb.toString();
    }
}
