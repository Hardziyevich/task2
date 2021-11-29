package by.hardziyevich.task2.entity.impl;


import by.hardziyevich.task2.entity.Type;

public enum ChocolateFillingTypeImpl implements Type {
    WHITE("white"),
    NOT_WHITE("not-white");

    private final String name;

    ChocolateFillingTypeImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
