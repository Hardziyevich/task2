package by.hardziyevich.task2.entity.impl;

import by.hardziyevich.task2.entity.Type;

public enum ChocolateCandyTypeImpl implements Type {
    CHOCOLATE("chocolate"),
    CHOCOLATE_FILLING("chocolate-filling");

    private final String name;

    ChocolateCandyTypeImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
