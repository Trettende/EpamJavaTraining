package ua.nure.matchenko.practice7.controller;

import org.xml.sax.helpers.DefaultHandler;
import ua.nure.matchenko.practice7.constants.Constants;
import ua.nure.matchenko.practice7.constants.XML;
import ua.nure.matchenko.practice7.entity.Material;
import ua.nure.matchenko.practice7.entity.Blade;
import ua.nure.matchenko.practice7.entity.Knife;
import ua.nure.matchenko.practice7.entity.SteelArm;
import ua.nure.matchenko.practice7.entity.Visual;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

public class STAXController extends DefaultHandler {
    private String xmlFileName;

    private String currentElement;

    private SteelArm steelArm;
    private Knife knife;
    private Visual visual;
    private Blade blade;
    private Material material;


    public SteelArm getSteelArm() {
        return steelArm;
    }

    public STAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public void parse() throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

        XMLEventReader reader = factory.createXMLEventReader(
                new StreamSource(xmlFileName));
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
                continue;
            }

            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                currentElement = startElement.getName().getLocalPart();

                if (XML.STEEL_ARM.value().equals(currentElement)) {
                    steelArm = new SteelArm();
                    continue;
                }
                if (XML.KNIFE.value().equals(currentElement)) {
                    knife = new Knife();
                    continue;
                }
                if (XML.VISUAL.value().equals(currentElement)) {
                    visual = new Visual();
                    continue;
                }
                if (XML.BLADE.value().equals(currentElement)) {
                    blade = new Blade();
                    continue;
                }
                if (XML.MATERIAL.value().equals(currentElement)) {
                    material = new Material();
                    continue;
                }
            }

            if (event.isCharacters()) {
                Characters characters = event.asCharacters();

                if (XML.TYPE.value().equals(currentElement)) {
                    knife.setType(characters.getData());
                    continue;
                }
                if (XML.HANDY.value().equals(currentElement)) {
                    knife.setHandy(characters.getData());
                    continue;
                }
                if (XML.ORIGIN.value().equals(currentElement)) {
                    knife.setOrigin(characters.getData());
                    continue;
                }
                if (XML.LENGTH.value().equals(currentElement)) {
                    blade.setLength(Integer.valueOf(characters.getData()));
                    continue;
                }
                if (XML.WIDTH.value().equals(currentElement)) {
                    blade.setWidth(Integer.valueOf(characters.getData()));
                    continue;
                }
                if (XML.BLADE_MATERIAL.value().equals(currentElement)) {
                    material.setBladeMaterial(characters.getData());
                    continue;
                }
                if (currentElement.equals(XML.HANDLE.value())) {
                    material.setHandle(characters.getData());
                    continue;
                }
                if (currentElement.equals(XML.WOOD_HANDLE.value())) {
                    material.setWoodHandle(characters.getData());
                    continue;
                }
                if (currentElement.equals(XML.WOOD_TYPE.value())) {
                    material.setWoodType(characters.getData());
                    continue;
                }
                if (XML.BLOODSTREAM.value().equals(currentElement)) {
                    visual.setBloodstream(Boolean.parseBoolean(characters.getData()));
                    continue;
                }
                if (XML.VALUE.value().equals(currentElement)) {
                    knife.setValue(Boolean.parseBoolean(characters.getData()));
                    continue;
                }
            }

            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                String localName = endElement.getName().getLocalPart();

                if (XML.KNIFE.value().equals(localName)) {
                    steelArm.getKnives().add(knife);
                    continue;
                }
                if (XML.VISUAL.value().equals(localName)) {
                    knife.getVisuals().add(visual);
                    continue;
                }
                if (XML.BLADE.value().equals(localName)) {
                    visual.setBlade(blade);
                    continue;
                }
                if (XML.MATERIAL.value().equals(localName)) {
                    visual.setMaterial(material);
                    continue;
                }
            }
        }
        reader.close();
    }

    public static void main(String[] args) throws XMLStreamException {
        STAXController controller = new STAXController(Constants.VALID_XML_FILE);
        controller.parse();

        SteelArm steelArm = controller.getSteelArm();

        System.out.println("====================================");
        System.out.print("Here is the steelArm: \n" + steelArm);
        System.out.println("====================================");
    }
}
