package by.hardziyevich.task2.entity;

import java.util.Arrays;

@FunctionalInterface
public interface Type {

    String getName();

    static Type convert(Type[] type,String data) {
        return Arrays.stream(type)
                .filter(x -> x.getName().equals(data))
                .findFirst()
                .orElse(null);
    }
}
