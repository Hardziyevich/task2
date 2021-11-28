package by.hardziyevich.task2.entity.impl;

import by.hardziyevich.task2.interpreter.InterpreterCandies;
import by.hardziyevich.task2.interpreter.PropertyCandy;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public abstract class Candy {
    private final long id;
    private final int energy;
    private final String nameCandy;
    private final String production;
    private final List<InterpreterCandies> property;
    private final Date shelfLife;

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

    public Candy(PropertyCandy propertyCandy){
        id = propertyCandy.getId();
        energy = propertyCandy.getEnergy();
        nameCandy = propertyCandy.getNameCandy();
        production = propertyCandy.getProduction();
        property = propertyCandy.getProperty();
        shelfLife = propertyCandy.getShelfLife();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candy candy = (Candy) o;
        return id == candy.id && energy == candy.energy && Objects.equals(nameCandy, candy.nameCandy) && Objects.equals(production, candy.production) && Objects.equals(property, candy.property) && Objects.equals(shelfLife, candy.shelfLife);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(id);
        result = prime * result + Integer.hashCode(energy);
        result = prime * result + (nameCandy == null ? 0 : nameCandy.hashCode());
        result = prime * result + (production == null ? 0 : production.hashCode());
        if (property == null) {
            result = prime * result;
        } else {
            for (InterpreterCandies candies : property) {
                result = prime * result + (candies == null ? 0 : candies.hashCode());
            }
        }
        result = prime * result + (shelfLife == null ? 0 : shelfLife.hashCode());

        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Candy{");
        sb.append("id= ").append(id);
        sb.append(", energy= ").append(energy);
        sb.append(", nameCandy= ").append(nameCandy);
        sb.append(", production= ").append(production);
        sb.append(", property= ");
        property.forEach(p -> sb.append(p).append(", "));
        sb.delete(sb.length() - 2, sb.length());
        sb.append(", shelfLife= ").append(shelfLife);
        sb.append("}");
        return sb.toString();
    }
}
