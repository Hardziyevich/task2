package by.hardziyevich.task2.entity;

import by.hardziyevich.task2.entity.impl.ChocolateCandyTypeImpl;
import by.hardziyevich.task2.entity.impl.ChocolateFillingTypeImpl;
import by.hardziyevich.task2.interpreter.PropertyCandy;

public class ChocolateCandy extends Candy {

    private ChocolateCandyTypeImpl chocolateCandyTypeImpl;
    private ChocolateFillingTypeImpl chocolateFillingTypeImpl;

    public ChocolateCandy(PropertyCandy propertyCandy) {
        super(propertyCandy);
        chocolateCandyTypeImpl = propertyCandy.getChocolateCandyType();
        chocolateFillingTypeImpl = propertyCandy.getChocolateFillingType();
    }

    public ChocolateCandyTypeImpl getChocolateCandyType() {
        return chocolateCandyTypeImpl;
    }

    public void setChocolateCandyType(ChocolateCandyTypeImpl chocolateCandyTypeImpl) {
        this.chocolateCandyTypeImpl = chocolateCandyTypeImpl;
    }

    public ChocolateFillingTypeImpl getChocolateType() {
        return chocolateFillingTypeImpl;
    }

    public void setChocolateType(ChocolateFillingTypeImpl chocolateFillingTypeImpl) {
        this.chocolateFillingTypeImpl = chocolateFillingTypeImpl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ChocolateCandy that = (ChocolateCandy) o;
        return chocolateCandyTypeImpl == that.chocolateCandyTypeImpl && chocolateFillingTypeImpl == that.chocolateFillingTypeImpl;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + super.hashCode();
        result = prime * result + (chocolateCandyTypeImpl == null ? 0 : chocolateCandyTypeImpl.hashCode());
        result = prime * result + (chocolateFillingTypeImpl == null ? 0 : chocolateFillingTypeImpl.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", chocolateCandyType= ").append(chocolateCandyTypeImpl);
        sb.append(", chocolateFillingType= ").append(chocolateFillingTypeImpl);
        return sb.toString();
    }
}
