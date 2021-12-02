package by.hardziyevich.task2.parser;

import by.hardziyevich.task2.entity.Candy;
import by.hardziyevich.task2.exeption.SomeException;
import by.hardziyevich.task2.validator.Validator;

import java.nio.file.Path;
import java.util.List;

/**
 * Class representing Template Method design pattern.
 */
public abstract class CandyParser {

    public abstract List<Candy> parse(Path path) throws SomeException;

    public List<Candy> mainParseCandy(Path path) throws SomeException {
        if(!validate(path)){
            throw new SomeException();
        }
        return parse(path);
    }

    public boolean validate(Path path) throws SomeException {
        return Validator.of(path)
                .validate(x -> !x.endsWith(".xml"),"It isn`t xml")
                .validate(x -> x.toFile().exists(),"File does not exists").isCorrect();
    }
}
