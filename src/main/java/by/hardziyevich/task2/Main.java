package by.hardziyevich.task2;

import by.hardziyevich.task2.exeption.SomeException;
import by.hardziyevich.task2.parser.CandyParser;

import java.nio.file.Path;

import static by.hardziyevich.task2.factory.ParserFactory.*;
import static by.hardziyevich.task2.factory.ParserFactory.TypeParser.*;

public class Main {

    public static void main(String[] args) throws SomeException {
        CandyParser parser = newCandyParser(SAX);
        parser.mainParser(Path.of("D:\\TaskForEpam\\task2\\src\\main\\resources\\Candy.xml")).forEach(System.out::println);
        parser = newCandyParser(StAX);
        parser.mainParser(Path.of("D:\\TaskForEpam\\task2\\src\\main\\resources\\Candy.xml")).forEach(System.out::println);
    }
}