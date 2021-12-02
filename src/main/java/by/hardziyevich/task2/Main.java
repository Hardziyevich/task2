package by.hardziyevich.task2;

import by.hardziyevich.task2.exeption.SomeException;
import by.hardziyevich.task2.parser.CandyParser;

import java.nio.file.Path;

import static by.hardziyevich.task2.factory.ParserBuilderFactory.*;
import static by.hardziyevich.task2.factory.ParserBuilderFactory.TypeParser.*;

public class Main {

    public static void main(String[] args) throws SomeException {
        CandyParser parser = newParserBuilder(SAX);
        parser.mainParseCandy(Path.of("C:\\work\\task2\\src\\main\\resources\\Candy.xml")).forEach(System.out::println);
        parser = newParserBuilder(StAX);
        parser.mainParseCandy(Path.of("C:\\work\\task2\\src\\main\\resources\\Candy.xml")).forEach(System.out::println);
        parser = newParserBuilder(DOM);
        parser.mainParseCandy(Path.of("C:\\work\\task2\\src\\main\\resources\\Candy.xml")).forEach(System.out::println);
    }
}