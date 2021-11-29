package by.hardziyevich.task2.entity.impl;

import by.hardziyevich.task2.entity.Type;

public enum CaramelCandyTypeImpl implements Type {
    GLAZED("glaze"),
    CANDY("candy");

    private final String name;

    CaramelCandyTypeImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
