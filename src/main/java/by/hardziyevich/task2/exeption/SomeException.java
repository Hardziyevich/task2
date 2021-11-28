package by.hardziyevich.task2.exeption;

import by.hardziyevich.task2.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SomeException extends Exception {
    private static final Logger log = LoggerFactory.getLogger(SomeException.class);

    public SomeException(String message) {
        super(message);
        log.warn("Something happened {}",message);
    }

    public SomeException() {
        log.warn("Something happened");
    }
}
