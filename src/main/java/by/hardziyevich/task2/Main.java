package by.hardziyevich.task2;

import by.hardziyevich.task2.exeption.SomeException;
import by.hardziyevich.task2.parser.CandyParser;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws SomeException {
        CandyParser parser = new CandyParser();
        parser.parse(Path.of("D:\\TaskForEpam\\task2\\src\\main\\resources\\test.xml"));
    }
}
