//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation,
// v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.10.12 at 09:06:28 PM EEST 
//


package ua.nure.matchenko.practice7.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Knife complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Knife">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Handy">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="one-handed"/>
 *               &lt;enumeration value="two-handed"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Origin" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Visual" type="{http://nure.ua/matchenko/practice7}Visual" maxOccurs="unbounded"/>
 *         &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Knife", propOrder = {
    "type",
    "handy",
    "origin",
    "visual",
    "value"
})
public class Knife {

    @XmlElement(name = "Type", required = true)
    protected String type;
    @XmlElement(name = "Handy", required = true)
    protected String handy;
    @XmlElement(name = "Origin", required = true)
    protected String origin;
    @XmlElement(name = "Visual", required = true)
    protected List<Visual> visual;
    @XmlElement(name = "Value")
    protected boolean value;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the handy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandy() {
        return handy;
    }

    /**
     * Sets the value of the handy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandy(String value) {
        this.handy = value;
    }

    /**
     * Gets the value of the origin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the value of the origin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigin(String value) {
        this.origin = value;
    }

    /**
     * Gets the value of the visual property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the visual property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVisuals().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Visual }
     * 
     * 
     */
    public List<Visual> getVisuals() {
        if (visual == null) {
            visual = new ArrayList<>();
        }
        return this.visual;
    }

    /**
     * Gets the value of the value property.
     * 
     */
    public boolean isValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     */
    public void setValue(boolean value) {
        this.value = value;
    }

}
