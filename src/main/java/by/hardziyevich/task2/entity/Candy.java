package by.hardziyevich.task2.entity;

import by.hardziyevich.task2.interpreter.PropertyCandy;
import by.hardziyevich.task2.interpreter.impl.IngredientImpl;
import by.hardziyevich.task2.interpreter.impl.NutritionalValueImpl;

import java.util.Date;

public abstract class Candy {
    private final long id;
    private final int energy;
    private final String nameCandy;
    private final String production;
    private final NutritionalValueImpl nutritionalValue;
    private final IngredientImpl ingredient;
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

    public Date getShelfLife() {
        return shelfLife;
    }

    public Candy(PropertyCandy propertyCandy){
        id = propertyCandy.getId();
        energy = propertyCandy.getEnergy();
        nameCandy = propertyCandy.getNameCandy();
        production = propertyCandy.getProduction();
        nutritionalValue = propertyCandy.getNutritionalValue();
        ingredient = propertyCandy.getIngredient();
        shelfLife = propertyCandy.getShelfLife();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candy candy = (Candy) o;
        return id == candy.id
                && energy == candy.energy
                && nameCandy.equals(candy.nameCandy)
                && production.equals(candy.production)
                && nutritionalValue.equals(candy.nutritionalValue)
                && ingredient.equals(candy.ingredient)
                && shelfLife.equals(candy.shelfLife);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(id);
        result = prime * result + Integer.hashCode(energy);
        result = prime * result + (nameCandy == null ? 0 : nameCandy.hashCode());
        result = prime * result + (production == null ? 0 : production.hashCode());
        result = prime * result + (nutritionalValue == null ? 0 : nutritionalValue.hashCode());
        result = prime * result + (ingredient == null ? 0 : ingredient.hashCode());
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
        sb.append(", ingredient= ").append(ingredient);
        sb.append(", nutritionalValue= ").append(nutritionalValue);
        sb.append(", shelfLife= ").append(shelfLife);
        sb.append("}");
        return sb.toString();
    }
}
