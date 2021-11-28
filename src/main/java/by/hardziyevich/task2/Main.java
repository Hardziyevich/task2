package by.hardziyevich.task2;

import by.hardziyevich.task2.exeption.SomeException;
import by.hardziyevich.task2.interpreter.InterpreterCandies;
import by.hardziyevich.task2.parser.CandyParser;
import by.hardziyevich.task2.validator.Validator;

import java.io.ObjectStreamClass;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class Main {
    static int water;
    static int fat;
    static int carb;
    static Predicate<String> test = "water"::equals;

    public static void main(String[] args) throws SomeException {
//        Test.of("water").validator(x -> x.equals("pasha")).getData(101);
        initilize("pasha","10");
        initilize("fat","15");
        initilize("pasha","10");
        initilize("carb","16");

        System.out.println("fat=" + fat);
        System.out.println("carb=" + carb);

//        CandyParser parser = new CandyParser();
//        parser.parse(Path.of("D:\\TaskForEpam\\task2\\src\\main\\resources\\test.xml"));
    }

    private static void initilize(String tag, String data) throws SomeException {
        fat = Test.of("fat",fat).validator(x -> x.equals(tag)).getData(Integer.parseInt(data));
//        carb = Test.of("carb").validator(x -> x.equals(tag)).getData(data).isEmpty() ? carb : Integer.parseInt(data);

    }

}

class Test<T, V> {

    private final T object;
    private final V data;
    private Boolean flag = false;

    public Test(T object, V data) {
        this.object = object;
        this.data = data;
    }

    public static <T, V> Test<T, V> of(T object,V data) {
        return new Test<>(object,data);
    }

    public Test<T, V> validator(Predicate<T> validation) {
        if (validation.test(object)) {
            flag = true;
        }
        return this;
    }

    public V getData(V newData) {
        return flag ? newData : data;
    }

//    public V setData(T object,V newData) throws SomeException {
//        return validator(x-> x.equals(object))
//                .getData(newData)
//                .isEmpty() ? data : Validator.of(newData).get();
//    }
}
