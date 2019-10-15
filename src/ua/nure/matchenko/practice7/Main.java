package ua.nure.matchenko.practice7;

import org.xml.sax.SAXException;
import ua.nure.matchenko.practice7.controller.DOMController;
import ua.nure.matchenko.practice7.controller.SAXController;
import ua.nure.matchenko.practice7.controller.STAXController;
import ua.nure.matchenko.practice7.entity.SteelArm;
import ua.nure.matchenko.practice7.util.Sorter;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Main {

    public static void usage() {
        System.out.println("Usage: java -jar practice7.jar xmlFileName xsdFileName [xslFileName]");
    }

    public static void main(String[] args)
            throws IOException, SAXException, ParserConfigurationException, TransformerException, XMLStreamException {
        if (args.length < 2 || args.length > 3) {
            usage();
            return;
        }

        String xmlFileName = args[0];
        /*String xsdFileName = args[1];
        String xslFileName = null;
        if (args.length == 3) {
            xslFileName = args[2];
        }*/

        DOMController domController = new DOMController(xmlFileName);
        domController.parse(true);
        SteelArm steelArm = domController.getSteelArm();

        Sorter.sortKnivesByType(steelArm);

        String outputXmlFile = "output.dom.xml";
        DOMController.saveXML(steelArm, outputXmlFile);

        SAXController saxController = new SAXController(xmlFileName);
        saxController.parse(true);
        steelArm = saxController.getSteelArm();

        Sorter.sortKnivesByVisualsNumber(steelArm);

        outputXmlFile = "output.sax.xml";
        DOMController.saveXML(steelArm, outputXmlFile);

        STAXController staxController = new STAXController(xmlFileName);
        staxController.parse();
        steelArm = staxController.getSteelArm();

        Sorter.sortKnivesByValue(steelArm);

        outputXmlFile = "output.stax.xml";
        DOMController.saveXML(steelArm, outputXmlFile);
    }
}
