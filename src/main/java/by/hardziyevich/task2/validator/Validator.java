package by.hardziyevich.task2.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Predicate;

/**
 * Class representing Monad design pattern.
 *
 * @param <T>
 */
public class Validator<T> {

    private static final Logger log = LoggerFactory.getLogger(Validator.class);
    private T object;
    public boolean flagNull = false;
    public Map<String,Boolean> flags = new HashMap<>();

    public Validator(T object) {
        if (object != null) {
            this.object = object;
        } else {
            flagNull = true;
            flags.put("Object is null",!flagNull);
        }
    }


    public static <T> Validator<T> of(final T object){
        return new Validator<>(object);
    }

    public Validator<T> validate(final Predicate<T> validation, final String message) {
        if (!flagNull && !validation.test(object)) {
            flags.put(message,Boolean.FALSE);
        }
        return this;
    }

    public boolean isCorrect() {
        flags.forEach((key, value) -> log.warn(key));
        return flags.isEmpty();
    }
}

