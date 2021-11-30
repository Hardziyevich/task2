package by.hardziyevich.task2.parser;

import by.hardziyevich.task2.entity.Candy;
import by.hardziyevich.task2.entity.CaramelCandy;
import by.hardziyevich.task2.entity.ChocolateCandy;
import by.hardziyevich.task2.exeption.SomeException;
import by.hardziyevich.task2.interpreter.InterpreterCandies;
import by.hardziyevich.task2.interpreter.impl.IngredientImpl;
import by.hardziyevich.task2.interpreter.impl.NutritionalValueImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import static by.hardziyevich.task2.interpreter.InterpreterCandies.*;
import static by.hardziyevich.task2.interpreter.InterpreterCandies.CandyXmlTag.*;
import static by.hardziyevich.task2.interpreter.PropertyCandy.*;

public class CandyHandler extends DefaultHandler {
    private static final Logger log = LoggerFactory.getLogger(CandyHandler.class);
    private List<Candy> candies;
    private Builder builder;
    private InterpreterCandies interpreterCandies;
    private CandyXmlTag currentTag;

    public CandyHandler(){
        super();
        candies = new ArrayList<>();
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentTag = convert(qName);
        if (currentTag != null) {
            switch (currentTag) {
                case CARAMEL:
                case CHOCOLATE:
                    builder = new Builder();
                    interpreterCandies = builder;
                    readAttributes(attributes);
                    break;
                case INGREDIENT: {
                    builder.ingredient(new IngredientImpl());
                        readAttributes(attributes);
                    break;
                }
                case VALUE: {
                    builder.nutritionalValue(new NutritionalValueImpl());
                    break;
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        CandyXmlTag closeTag = convert(qName);
        if (closeTag != null) {
            switch (closeTag) {
                case CARAMEL:
                    candies.add(new CaramelCandy(builder.build()));
                    builder = null;
                    break;
                case CHOCOLATE:
                    candies.add(new ChocolateCandy(builder.build()));
                    builder = null;
                    break;
            }
        }
        currentTag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        if (currentTag != null) {
            try {
                interpreterCandies.interpret(currentTag.getTag(), data);
            } catch (SomeException e) {
                log.warn("Something happened ",e);
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    private void readAttributes(Attributes attributes){
        for (int i = 0; i < attributes.getLength(); i++) {
            try {
                interpreterCandies.interpret(attributes.getQName(i), attributes.getValue(i));
            } catch (SomeException e) {
                log.warn("Something happened ",e);
            }
        }
    }

    public List<Candy> getCandies() {
        return List.copyOf(candies);
    }
}
