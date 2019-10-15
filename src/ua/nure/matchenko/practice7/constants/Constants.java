package ua.nure.matchenko.practice7.constants;

public final class Constants {

    private Constants() {}

    public static final String VALID_XML_FILE = "input.xml";
    public static final String INVALID_XML_FILE = "input-invalid.xml";
    public static final String XSD_FILE = "input.xsd";

    public static final String MY_NS_URI = "http://nure.ua/matchenko/practice7";
    public static final String MY_NS_PREFIX = "p7";

    public static final String SCHEMA_LOCATION_URI =
            "http://nure.ua/matchenko/practice7 input.xsd";
    public static final String SCHEMA_LOCATION_ATTR_NAME = "schemaLocation";
    public static final String SCHEMA_LOCATION_ATTR_FQN = "xsi:schemaLocation";
    public static final String XSI_SPACE_PREFIX = "xsi";

    public static final String FEATURE_TURN_VALIDATION_ON = "http://xml.org/sax/features/validation";
    public static final String FEATURE_TURN_SCHEMA_VALIDATION_ON = "http://apache.org/xml/features/validation/schema";

    public static final String CLASS_XERCES_SAX_PARSER =
            "org.apache.xerces.parsers.SAXParser";
    public static final String CLASS_DOCUMENT_BUILDER_FACTORY_INTERNAL =
            "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl";
    public static final String CLASS_SAX_PARSER_FACTORY_INTERNAL =
            "com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl";
    public static final String CLASS_XML_SCHEMA_FACTORY_INTERNAL =
            "com.sun.org.apache.xerces.internal.jaxp.validation.XMLSchemaFactory";
}

