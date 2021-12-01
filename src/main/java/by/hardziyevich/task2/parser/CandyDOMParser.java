package by.hardziyevich.task2.parser;

import by.hardziyevich.task2.entity.Candy;
import by.hardziyevich.task2.entity.ChocolateCandy;
import by.hardziyevich.task2.exeption.SomeException;
import by.hardziyevich.task2.interpreter.InterpreterCandies;
import by.hardziyevich.task2.interpreter.PropertyCandy;
import by.hardziyevich.task2.interpreter.impl.IngredientImpl;
import by.hardziyevich.task2.interpreter.impl.NutritionalValueImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static by.hardziyevich.task2.interpreter.InterpreterCandies.CandyXmlTag.*;
import static by.hardziyevich.task2.interpreter.PropertyCandy.Builder;

public class CandyDOMParser extends CandyParser{
    private final List<Candy> candies;
    private DocumentBuilder docBuilder;
    private Builder builder;
    private InterpreterCandies interpreterCandies;
    private InterpreterCandies.CandyXmlTag currentTag;
    private static final Logger log = LoggerFactory.getLogger(CandyHandler.class);

    public CandyDOMParser() throws SomeException {
        super();
        this.candies = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new SomeException(e.getMessage());
        }
    }

    @Override
    public List<Candy> parse(Path path) throws SomeException {
        Document document;
        try {
            document = docBuilder.parse(path.toFile());
            Element root = document.getDocumentElement();
            elementsByTag(root,CHOCOLATE.toString());

        } catch (SAXException | IOException e) {
            throw new SomeException(e.getMessage());
        }
        return candies;
    }

    private void elementsByTag(Element element,String tag) throws SomeException {
        NodeList candy = element.getElementsByTagName(tag);
        for (int i = 0; i < candy.getLength(); i++) {
            builder = new Builder();
            interpreterCandies = builder;
            Element candyElement = (Element) candy.item(i);
            getAttributesForBuilder(interpreterCandies,candy.item(i));
            System.out.println(VALUE.toString() + " "+getElementTextContent(candyElement, VALUE.toString()));
            interpreterCandies.interpret(ENERGY.toString(),getElementTextContent(candyElement,ENERGY.toString()));
            interpreterCandies.interpret(NAME.toString(),getElementTextContent(candyElement,NAME.toString()));
            interpreterCandies.interpret(PRODUCTION.toString(),getElementTextContent(candyElement,PRODUCTION.toString()));
            interpreterCandies.interpret(DATA.toString(),getElementTextContent(candyElement,DATA.toString()));
            NutritionalValueImpl nutritionalValue = new NutritionalValueImpl();
            nutritionalValue.interpret(FAT.toString(), getElementTextContent(candyElement, FAT.toString()));
            nutritionalValue.interpret(CARB.toString(), getElementTextContent(candyElement, CARB.toString()));
            nutritionalValue.interpret(PROTEIN.toString(), getElementTextContent(candyElement, PROTEIN.toString()));
            InterpreterCandies ingredient = new IngredientImpl();
            builder.nutritionalValue(nutritionalValue);
            candies.add(new ChocolateCandy(builder.build()));
        }
    }

    private void getAttributesForBuilder(InterpreterCandies interpreterCandies,Node item) throws SomeException {
        NamedNodeMap attributes = item.getAttributes();
        for (int j = 0; j < attributes.getLength(); j++) {
            interpreterCandies.interpret(attributes.item(j).getNodeName(),attributes.item(j).getNodeValue());
        }
    }

    private void test(InterpreterCandies interpreterCandies,Element element, String elementName) throws SomeException {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        interpreterCandies.interpret(node.getNodeName(),node.getTextContent());
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
