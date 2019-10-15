package ua.nure.matchenko.practice7.controller;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.matchenko.practice7.constants.Constants;
import ua.nure.matchenko.practice7.constants.XML;
import ua.nure.matchenko.practice7.entity.Material;
import ua.nure.matchenko.practice7.entity.Blade;
import ua.nure.matchenko.practice7.entity.Knife;
import ua.nure.matchenko.practice7.entity.SteelArm;
import ua.nure.matchenko.practice7.entity.Visual;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SAXController extends DefaultHandler {
    private String xmlFileName;

    public SAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public void parse(boolean validate) throws SAXException, ParserConfigurationException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance(
                Constants.CLASS_SAX_PARSER_FACTORY_INTERNAL,
                this.getClass().getClassLoader());
        factory.setNamespaceAware(true);

        if (validate) {
            factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
            factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        }

        SAXParser parser = factory.newSAXParser();
        parser.parse(xmlFileName, this);
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        throw e;
    }

    private String currentElement;

    private SteelArm steelArm;
    private Knife knife;
    private Visual visual;
    private Blade blade;
    private Material material;

    public SteelArm getSteelArm() {
        return steelArm;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = localName;

        // WARNING!!!
        // here and below we use '==' operation to compare two INTERNED STRINGS

        if (XML.STEEL_ARM.value().equals(currentElement)) {
            steelArm = new SteelArm();
            return;
        }

        if (XML.KNIFE.value().equals(currentElement)) {
            knife = new Knife();
            return;
        }

        if (XML.VISUAL.value().equals(currentElement)) {
            visual = new Visual();
            return;
        }

        if (XML.BLADE.value().equals(currentElement)) {
            blade = new Blade();
            return;
        }

        if (XML.MATERIAL.value().equals(currentElement)) {
            material = new Material();
            return;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String elementText = new String(ch, start, length).trim();

        if (elementText.isEmpty()) {
            return;
        }

        if (XML.TYPE.value().equals(currentElement)) {
            knife.setType(elementText);
            return;
        }

        if (XML.HANDY.value().equals(currentElement)) {
            knife.setHandy(elementText);
            return;
        }

        if (XML.ORIGIN.value().equals(currentElement)) {
            knife.setOrigin(elementText);
            return;
        }

        if (XML.LENGTH.value().equals(currentElement)) {
            blade.setLength(Integer.valueOf(elementText));
            return;
        }

        if (XML.WIDTH.value().equals(currentElement)) {
            blade.setWidth(Integer.valueOf(elementText));
            return;
        }

        if (XML.BLADE_MATERIAL.value().equals(currentElement)) {
            material.setBladeMaterial(elementText);
            return;
        }

        if (XML.HANDLE.value().equals(currentElement)) {
            material.setHandle(elementText);
            return;
        }

        if (XML.WOOD_HANDLE.value().equals(currentElement)) {
            material.setWoodHandle(elementText);
            return;
        }

        if (XML.WOOD_TYPE.value().equals(currentElement)) {
            material.setWoodType(elementText);
            return;
        }

        if (XML.BLOODSTREAM.value().equals(currentElement)) {
            visual.setBloodstream(Boolean.parseBoolean(elementText));
            return;
        }

        if (XML.ORIGIN.value().equals(currentElement)) {
            knife.setOrigin(elementText);
            return;
        }

        if (XML.VALUE.value().equals(currentElement)) {
            knife.setValue(Boolean.parseBoolean(elementText));
            return;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (XML.KNIFE.value().equals(localName)) {
            steelArm.getKnives().add(knife);
            return;
        }

        if (XML.VISUAL.value().equals(localName)) {
            knife.getVisuals().add(visual);
            return;
        }

        if (XML.BLADE.value().equals(localName)) {
            visual.setBlade(blade);
            return;
        }

        if (XML.MATERIAL.value().equals(localName)) {
            visual.setMaterial(material);
            return;
        }
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        SAXController controller = new SAXController(Constants.VALID_XML_FILE);
        controller.parse(true);
        SteelArm steelArm = controller.getSteelArm();

        System.out.println("====================================");
        System.out.print("Here is the steelArm: \n" + steelArm);
        System.out.println("====================================");

        controller = new SAXController(Constants.INVALID_XML_FILE);
        try {
            controller.parse(true);
        } catch (Exception e) {
            System.err.println("====================================");
            System.err.println("Validation is failed:\n" + e.getMessage());
            System.err
                    .println("Try to print steelArm object:" + controller.getSteelArm());
            System.err.println("====================================");
        }

        controller.parse(false);

        System.out.println("====================================");
        System.out.print("Here is the steelArm: \n" + controller.getSteelArm());
        System.out.println("====================================");
    }
}