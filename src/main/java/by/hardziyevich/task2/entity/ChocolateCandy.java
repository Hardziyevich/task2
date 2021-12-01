package by.hardziyevich.task2.entity;

import by.hardziyevich.task2.interpreter.PropertyCandy;

public class ChocolateCandy extends Candy {

    private ChocolateCandyType chocolateCandyType;
    private ChocolateFillingType chocolateFillingType;

    public ChocolateCandy(PropertyCandy propertyCandy) {
        super(propertyCandy);
        chocolateCandyType = propertyCandy.getChocolateCandyType();
        chocolateFillingType = propertyCandy.getChocolateFillingType();
    }

    public ChocolateCandyType getChocolateCandyType() {
        return chocolateCandyType;
    }

    public void setChocolateCandyType(ChocolateCandyType chocolateCandyType) {
        this.chocolateCandyType = chocolateCandyType;
    }

    public ChocolateFillingType getChocolateType() {
        return chocolateFillingType;
    }

    public void setChocolateType(ChocolateFillingType chocolateFillingType) {
        this.chocolateFillingType = chocolateFillingType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ChocolateCandy that = (ChocolateCandy) o;
        return chocolateCandyType == that.chocolateCandyType && chocolateFillingType == that.chocolateFillingType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + super.hashCode();
        result = prime * result + (chocolateCandyType == null ? 0 : chocolateCandyType.hashCode());
        result = prime * result + (chocolateFillingType == null ? 0 : chocolateFillingType.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", chocolateCandyType= ").append(chocolateCandyType);
        sb.append(", chocolateFillingType= ").append(chocolateFillingType);
        return sb.toString();
    }
}
