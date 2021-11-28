package by.hardziyevich.task2.validator;

import by.hardziyevich.task2.exeption.SomeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Class representing Monad design pattern.
 *
 * @param <T>
 */
public class Validator<T> {

    private static final Logger log = LoggerFactory.getLogger(Validator.class);
    private final T object;
    private Collection<Throwable> exceptions = new LinkedList<>();

    public Validator(T object) {
        this.object = object;
    }

    public Validator(T object, Collection<Throwable> exceptions) {
        this.object = object;
        this.exceptions = exceptions;
    }

    public static <T> Validator<T> of(final T object) throws SomeException {
        try {
            return new Validator<>(Objects.requireNonNull(object));
        }catch (NullPointerException e){
            throw new SomeException(e.getMessage());
        }
    }

    public Validator<T> validate(final Predicate<T> validation, final String message) {
        if (!validation.test(object)) {
            exceptions.add(new SomeException(message));
        }
        return this;
    }

    public T get() throws SomeException  {
        if (exceptions.isEmpty()) {
            log.info("{} is correct", object);
            return object;
        }
        final var exception = new SomeException();
        exceptions.forEach(exception::addSuppressed);
        log.warn("Something happened {1}",exception);
        throw exception;
    }
}

