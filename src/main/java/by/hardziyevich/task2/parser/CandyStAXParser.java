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

import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static by.hardziyevich.task2.interpreter.InterpreterCandies.*;
import static by.hardziyevich.task2.interpreter.InterpreterCandies.CandyXmlTag.convert;
import static by.hardziyevich.task2.interpreter.PropertyCandy.*;

public class CandyStAXParser extends CandyParser{
    private final List<Candy> candies;
    private final XMLInputFactory inputFactory;
    private Builder builder;
    private InterpreterCandies interpreterCandies;
    private CandyXmlTag currentTag;
    private static final Logger log = LoggerFactory.getLogger(CandyHandler.class);

    public CandyStAXParser() {
        super();
        this.candies = new ArrayList<>();
        inputFactory = XMLInputFactory.newInstance();
    }

    public List<Candy> parse(Path path) throws SomeException {
        XMLEventReader reader;
        try(FileInputStream inputStream = new FileInputStream(path.toFile())) {
            reader = inputFactory.createXMLEventReader(inputStream);
            XMLEvent event;
            while (reader.hasNext()){
                event = reader.nextEvent();
                if(event.isStartElement()){
                    StartElement startElement = event.asStartElement();
                    currentTag = convert((startElement.getName().getLocalPart()));
                    if (currentTag != null) {
                        switch (currentTag) {
                            case CARAMEL:
                            case CHOCOLATE:
                                builder = new Builder();
                                interpreterCandies = builder;
                                readAttributes(startElement);
                                break;
                            case INGREDIENT: {
                                builder.ingredient(new IngredientImpl());
                                readAttributes(startElement);
                                break;
                            }
                            case VALUE: {
                                builder.nutritionalValue(new NutritionalValueImpl());
                                break;
                            }
                            default:
                                if (currentTag != null) {
                                    try {
                                        XMLEvent dataEvent = reader.nextEvent();
                                        interpreterCandies.interpret(currentTag.getTag(), dataEvent.asCharacters().getData());
                                    } catch (SomeException e) {
                                        log.warn("Something happened ",e);
                                    }
                                }
                                break;
                        }
                    }
                } else if(event.isEndElement()){
                    EndElement endElement = event.asEndElement();
                    CandyXmlTag closeTag = convert(endElement.getName().getLocalPart());
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

            }
        } catch (IOException | XMLStreamException e) {
            throw new SomeException(e.getMessage());
        }
        return candies;
    }

    private void readAttributes(StartElement startElement){
        for (Iterator<Attribute> it = startElement.getAttributes(); it.hasNext(); ) {
            Attribute attribute = it.next();
            System.out.println(attribute.getName().getLocalPart());
            try {
                interpreterCandies.interpret(attribute.getName().getLocalPart(), attribute.getValue());
            } catch (SomeException e) {
                log.warn("Something happened ",e);
            }
        }
    }
}
