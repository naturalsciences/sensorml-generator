//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.03.18 at 12:05:10 PM CET 
//


package net.opengis.swe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TextEncodingPropertyByValueType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TextEncodingPropertyByValueType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/swe/2.0}TextEncoding"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TextEncodingPropertyByValueType", propOrder = {
    "textEncoding"
})
public class TextEncodingPropertyByValueType {

    @XmlElement(name = "TextEncoding", required = true)
    protected TextEncodingType textEncoding;

    /**
     * Gets the value of the textEncoding property.
     * 
     * @return
     *     possible object is
     *     {@link TextEncodingType }
     *     
     */
    public TextEncodingType getTextEncoding() {
        return textEncoding;
    }

    /**
     * Sets the value of the textEncoding property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextEncodingType }
     *     
     */
    public void setTextEncoding(TextEncodingType value) {
        this.textEncoding = value;
    }

}
