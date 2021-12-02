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

public class CandyDOMParser extends CandyParser {
    private final List<Candy> candies;
    private DocumentBuilder docBuilder;
    private Builder builder;
    private InterpreterCandies interpreterCandies;
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
            elementsByTag(root, CHOCOLATE.toString());
            elementsByTag(root, CARAMEL.toString());

        } catch (SAXException | IOException e) {
            throw new SomeException(e.getMessage());
        }
        return candies;
    }

    private void elementsByTag(Element element, String tag) throws SomeException {
        NodeList candy = element.getElementsByTagName(tag);
        for (int i = 0; i < candy.getLength(); i++) {
            Element candyElement = (Element) candy.item(i);
            builder = new Builder();
            interpreterCandies = builder;
            setAttributesForBuilder(interpreterCandies, candy.item(i));
            interpreterCandies.interpret(ENERGY.toString(), getElementTextContent(candyElement, ENERGY.toString()));
            interpreterCandies.interpret(NAME.toString(), getElementTextContent(candyElement, NAME.toString()));
            interpreterCandies.interpret(PRODUCTION.toString(), getElementTextContent(candyElement, PRODUCTION.toString()));
            interpreterCandies.interpret(DATA.toString(), getElementTextContent(candyElement, DATA.toString()));

            NutritionalValueImpl nutritionalValue = new NutritionalValueImpl();
            nutritionalValue.interpret(FAT.toString(), getElementTextContent(candyElement, FAT.toString()));
            nutritionalValue.interpret(CARB.toString(), getElementTextContent(candyElement, CARB.toString()));
            nutritionalValue.interpret(PROTEIN.toString(), getElementTextContent(candyElement, PROTEIN.toString()));

            IngredientImpl ingredientCandy = new IngredientImpl();
            setIngredientAttributesForBuilder(ingredientCandy, candyElement);
            builder.ingredient(ingredientCandy);
            builder.nutritionalValue(nutritionalValue);

            switch (valueOf(tag.toUpperCase())) {
                case CHOCOLATE : candies.add(new ChocolateCandy(builder.build()));break;
                case CARAMEL: candies.add(new CaramelCandy(builder.build()));break;
                default: {
                    log.warn("Invalid type!");
                    throw new SomeException("Invalid type!");
                }
            }
        }
    }

    private void setAttributesForBuilder(InterpreterCandies interpreterCandies, Node item) throws SomeException {
        NamedNodeMap attributes = item.getAttributes();
        for (int j = 0; j < attributes.getLength(); j++) {
            interpreterCandies.interpret(attributes.item(j).getNodeName(), attributes.item(j).getNodeValue());
        }
    }

    private void setIngredientAttributesForBuilder(InterpreterCandies interpreterCandies, Element element) throws SomeException {
        NodeList nodeList = element.getElementsByTagName(INGREDIENT.toString());
        for (int j = 0; j < nodeList.getLength(); j++) {
            interpreterCandies.interpret("water", nodeList.item(j).getAttributes().getNamedItem("water").getTextContent());
            interpreterCandies.interpret("sugar", nodeList.item(j).getAttributes().getNamedItem("sugar").getTextContent());
            interpreterCandies.interpret("fructose", nodeList.item(j).getAttributes().getNamedItem("fructose").getTextContent());
            interpreterCandies.interpret("vanilla", nodeList.item(j).getAttributes().getNamedItem("vanilla").getTextContent());
        }
    }


    private String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
