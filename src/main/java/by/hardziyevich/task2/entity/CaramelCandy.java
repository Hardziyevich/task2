package by.hardziyevich.task2.entity;

import by.hardziyevich.task2.interpreter.PropertyCandy;


public class CaramelCandy extends Candy {

    private CaramelCandyType caramelType;

    public CaramelCandy(PropertyCandy propertyCandy) {
        super(propertyCandy);
        caramelType = propertyCandy.getCaramelCandyType();
    }

    public CaramelCandyType getCaramelType() {
        return caramelType;
    }

    public void setCaramelType(CaramelCandyType caramelType) {
        this.caramelType = caramelType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CaramelCandy that = (CaramelCandy) o;
        return caramelType == that.caramelType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + super.hashCode();
        result = prime * result + (caramelType == null ? 0 : caramelType.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", caramelType= ").append(caramelType);
        return sb.toString();
    }
}
