package ua.nure.matchenko.practice7.util;

import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Transformer {
    public static void saveToXML(Document document, String xmlFileName) throws TransformerException {
        StreamResult result = new StreamResult(new File(xmlFileName));

        TransformerFactory factory = TransformerFactory.newInstance();
        javax.xml.transform.Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        transformer.transform(new DOMSource(document), result);
    }

    public static void main(String[] args) {
        //main
    }
}
