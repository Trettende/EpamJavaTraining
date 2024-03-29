//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation,
// v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.10.12 at 09:06:28 PM EEST 
//


package ua.nure.matchenko.practice7.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Visual complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Visual">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Blade" type="{http://nure.ua/matchenko/practice7}Blade"/>
 *         &lt;element name="Material" type="{http://nure.ua/matchenko/practice7}Material"/>
 *         &lt;element name="Bloodstream" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Visual", propOrder = {
    "blade",
    "material",
    "bloodstream"
})
public class Visual {

    @XmlElement(name = "Blade", required = true)
    protected Blade blade;
    @XmlElement(name = "Material", required = true)
    protected Material material;
    @XmlElement(name = "Bloodstream")
    protected boolean bloodstream;

    /**
     * Gets the value of the blade property.
     * 
     * @return
     *     possible object is
     *     {@link Blade }
     *     
     */
    public Blade getBlade() {
        return blade;
    }

    /**
     * Sets the value of the blade property.
     * 
     * @param value
     *     allowed object is
     *     {@link Blade }
     *     
     */
    public void setBlade(Blade value) {
        this.blade = value;
    }

    /**
     * Gets the value of the material property.
     * 
     * @return
     *     possible object is
     *     {@link Material }
     *     
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Sets the value of the material property.
     * 
     * @param value
     *     allowed object is
     *     {@link Material }
     *     
     */
    public void setMaterial(Material value) {
        this.material = value;
    }

    /**
     * Gets the value of the bloodstream property.
     * 
     */
    public boolean isBloodstream() {
        return bloodstream;
    }

    /**
     * Sets the value of the bloodstream property.
     * 
     */
    public void setBloodstream(boolean value) {
        this.bloodstream = value;
    }

}
