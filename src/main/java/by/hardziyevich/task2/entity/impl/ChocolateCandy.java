package by.hardziyevich.task2.entity.impl;

import by.hardziyevich.task2.entity.ChocolateCandyType;
import by.hardziyevich.task2.entity.ChocolateType;
import by.hardziyevich.task2.interpreter.PropertyCandy;

public class ChocolateCandy extends Candy {
    private ChocolateCandyType chocolateCandyType;
    private ChocolateType chocolateType;
    public ChocolateCandy(PropertyCandy propertyCandy) {
        super(propertyCandy);
    }
}
