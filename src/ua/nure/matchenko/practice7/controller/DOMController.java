package ua.nure.matchenko.practice7.controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
import ua.nure.matchenko.practice7.util.Transformer;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class DOMController {
    private String xmlFileName;

    private SteelArm steelArm;

    public DOMController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public SteelArm getSteelArm() {
        return steelArm;
    }

    public void parse(boolean validate) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(
                Constants.CLASS_DOCUMENT_BUILDER_FACTORY_INTERNAL,
                this.getClass().getClassLoader());
        factory.setNamespaceAware(true);

        if (validate) {
            factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
            factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        }

        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setErrorHandler(new DefaultHandler() {
            @Override
            public void error(SAXParseException e) throws SAXException {
                throw e;
            }
        });

        Document document = builder.parse(xmlFileName);

        Element root = document.getDocumentElement();

        steelArm = new SteelArm();

        NodeList knifeNodes = root.getElementsByTagName(XML.KNIFE.value());

        for (int j = 0; j < knifeNodes.getLength(); j++) {
            Knife knife = getKnife(knifeNodes.item(j));
            steelArm.getKnives().add(knife);
        }
    }

    private static Knife getKnife(Node knifeNode) {
        Knife knife = new Knife();
        Element knifeElement = (Element) knifeNode;

        Node typeNode = knifeElement.getElementsByTagName(XML.TYPE.value())
                .item(0);
        knife.setType(typeNode.getTextContent());

        Node handyNode = knifeElement.getElementsByTagName(XML.HANDY.value())
                .item(0);
        knife.setHandy(handyNode.getTextContent());

        Node originNode = knifeElement.getElementsByTagName(XML.ORIGIN.value())
                .item(0);
        knife.setOrigin(originNode.getTextContent());

        NodeList visualNodeList = knifeElement.getElementsByTagName(XML.VISUAL.value());
        for (int j = 0; j < visualNodeList.getLength(); j++) {
            Visual visual = getVisual(visualNodeList.item(j));
            knife.getVisuals().add(visual);
        }

        Node valueNode = knifeElement.getElementsByTagName(XML.VALUE.value())
                .item(0);
        knife.setValue(Boolean.parseBoolean(valueNode.getTextContent()));

        return knife;
    }

    private static Visual getVisual(Node visualNode) {
        Visual visual = new Visual();
        Element visualElement = (Element) visualNode;

        Node bladeNode = visualElement.getElementsByTagName(XML.BLADE.value())
                .item(0);
        visual.setBlade(getBlade(bladeNode));

        Node materialNode = visualElement.getElementsByTagName(XML.MATERIAL.value())
                .item(0);
        visual.setMaterial(getMaterial(materialNode));

        Node bloodstreamNode = visualElement.getElementsByTagName(XML.BLOODSTREAM.value())
                .item(0);
        visual.setBloodstream(Boolean.parseBoolean(bloodstreamNode.getTextContent()));

        return visual;
    }

    private static Blade getBlade(Node bladeNode) {
        Blade blade = new Blade();
        Element bladeElement = (Element) bladeNode;

        Node lengthNode = bladeElement.getElementsByTagName(XML.LENGTH.value())
                .item(0);
        blade.setLength(Integer.parseInt(lengthNode.getTextContent()));

        Node widthNode = bladeElement.getElementsByTagName(XML.WIDTH.value())
                .item(0);
        blade.setWidth(Integer.parseInt(widthNode.getTextContent()));

        return blade;
    }

    private static Material getMaterial(Node materialNode) {
        Material material = new Material();
        Element materialElement = (Element) materialNode;

        Node bladeMaterialNode = materialElement.getElementsByTagName(XML.BLADE_MATERIAL.value())
                .item(0);
        material.setBladeMaterial(bladeMaterialNode.getTextContent());

        Node handleNode = materialElement.getElementsByTagName(XML.HANDLE.value())
                .item(0);
        if (handleNode != null) {
            material.setHandle(handleNode.getTextContent());
        } else {

            Node woodHandleNode = materialElement.getElementsByTagName(XML.WOOD_HANDLE.value())
                    .item(0);
            material.setWoodHandle(woodHandleNode.getTextContent());

            Node woodTypeNode = materialElement.getElementsByTagName(XML.WOOD_TYPE.value())
                    .item(0);
            material.setWoodType(woodTypeNode.getTextContent());
        }

        return material;
    }

    public static void saveXML(SteelArm steelArm, String xmlFileName)
            throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(
                Constants.CLASS_DOCUMENT_BUILDER_FACTORY_INTERNAL,
                DOMController.class.getClassLoader());
        factory.setNamespaceAware(true);

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        //createElementNS
        Element steelArmElement = document.createElementNS(
                Constants.MY_NS_URI, Constants.MY_NS_PREFIX +
                        ":" + XML.STEEL_ARM.value());
        steelArmElement.setAttributeNS(
                XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI,
                Constants.SCHEMA_LOCATION_ATTR_FQN,
                Constants.SCHEMA_LOCATION_URI);

        document.appendChild(steelArmElement);

        for (Knife knife : steelArm.getKnives()) {
            Element knifeElement = document.createElement(XML.KNIFE.value());
            steelArmElement.appendChild(knifeElement);

            Element typeElement = document.createElement(XML.TYPE.value());
            typeElement.setTextContent(knife.getType());
            knifeElement.appendChild(typeElement);

            Element handyElement = document.createElement(XML.HANDY.value());
            handyElement.setTextContent(knife.getHandy());
            knifeElement.appendChild(handyElement);

            Element originElement = document.createElement(XML.ORIGIN.value());
            originElement.setTextContent(knife.getOrigin());
            knifeElement.appendChild(originElement);

            for (Visual visual : knife.getVisuals()) {
                Element visualElement = document.createElement(XML.VISUAL.value());

                Element bladeElement = document.createElement(XML.BLADE.value());

                Element lengthElement = document.createElement(XML.LENGTH.value());
                lengthElement.setTextContent(String.valueOf(visual.getBlade().getLength()));
                bladeElement.appendChild(lengthElement);

                Element widthElement = document.createElement(XML.WIDTH.value());
                widthElement.setTextContent(String.valueOf(visual.getBlade().getWidth()));
                bladeElement.appendChild(widthElement);

                visualElement.appendChild(bladeElement);

                Element materialElement = document.createElement(XML.MATERIAL.value());

                Element bladeMaterialElement = document.createElement(XML.BLADE_MATERIAL.value());
                bladeMaterialElement.setTextContent(visual.getMaterial().getBladeMaterial());
                materialElement.appendChild(bladeMaterialElement);

                Element handleElement = document.createElement(XML.HANDLE.value());
                if (handleElement != null) {
                    handleElement.setTextContent(visual.getMaterial().getHandle());
                    materialElement.appendChild(handleElement);
                } else {

                    Element woodHandleElement = document.createElement(XML.WOOD_HANDLE.value());
                    woodHandleElement.setTextContent(visual.getMaterial().getWoodHandle());
                    materialElement.appendChild(woodHandleElement);

                    Element woodTypeElement = document.createElement(XML.WOOD_TYPE.value());
                    woodTypeElement.setTextContent(visual.getMaterial().getWoodType());
                    materialElement.appendChild(woodTypeElement);
                }

                visualElement.appendChild(materialElement);

                Element bloodstreamElement = document.createElement(XML.BLOODSTREAM.value());
                bloodstreamElement.setTextContent(String.valueOf(visual.isBloodstream()));
                visualElement.appendChild(bloodstreamElement);

                knifeElement.appendChild(visualElement);
            }

            Element valueElement = document.createElement(XML.VALUE.value());
            valueElement.setTextContent(String.valueOf(knife.isValue()));
            knifeElement.appendChild(valueElement);
        }

        Transformer.saveToXML(document, xmlFileName);
    }

    public static void main(String[] args)
            throws IOException, ParserConfigurationException, SAXException, TransformerException {
        DOMController controller = new DOMController(Constants.INVALID_XML_FILE);
        try {
            controller.parse(true);
        } catch (SAXException e) {
            System.err.println("====================================");
            System.err.println("XML not valid");
            System.err.println("Steel arm object --> " + controller.getSteelArm());
            System.err.println("====================================");
        }

        controller.parse(false);

        System.out.println("====================================");
        System.out.print("Here is the steel arm: \n" + controller.getSteelArm());
        System.out.println("====================================");

        SteelArm steelArm = controller.getSteelArm();
        DOMController.saveXML(steelArm, Constants.INVALID_XML_FILE + ".dom.xml");
    }
}
