package by.hardziyevich.task2.factory;

import by.hardziyevich.task2.exeption.SomeException;
import by.hardziyevich.task2.parser.CandyParser;
import by.hardziyevich.task2.parser.CandySAXParser;
import by.hardziyevich.task2.parser.CandyStAXParser;

public class ParserFactory {

    private ParserFactory(){}

    public enum TypeParser{
        StAX,
        SAX
    }

    public static CandyParser newCandyParser(TypeParser type) throws SomeException {
        if(type != null) {
            switch (type) {
                case SAX:
                    return new CandySAXParser();
                case StAX:
                    return new CandyStAXParser();
                default:
                    throw new SomeException("This type is not supported");
            }
        }else {
            throw new SomeException("This type is not supported");
        }
    }
}
