//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.03.18 at 12:05:10 PM CET 
//


package net.opengis.swe;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for DataArrayType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataArrayType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/swe/2.0}AbstractDataComponentType">
 *       &lt;sequence>
 *         &lt;element name="elementCount" type="{http://www.opengis.net/swe/2.0}CountPropertyType"/>
 *         &lt;element name="elementType">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.opengis.net/swe/2.0}AbstractDataComponentPropertyType">
 *                 &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="encoding" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.opengis.net/swe/2.0}AbstractEncoding"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="values" type="{http://www.opengis.net/swe/2.0}EncodedValuesPropertyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataArrayType", propOrder = {
    "elementCount",
    "elementType",
    "encoding",
    "values"
})
@XmlSeeAlso({
    MatrixType.class
})
public class DataArrayType
    extends AbstractDataComponentType
{

    @XmlElement(required = true)
    protected CountPropertyType elementCount;
    @XmlElement(required = true)
    protected DataArrayType.ElementType elementType;
    protected DataArrayType.Encoding encoding;
    protected EncodedValuesPropertyType values;

    /**
     * Gets the value of the elementCount property.
     * 
     * @return
     *     possible object is
     *     {@link CountPropertyType }
     *     
     */
    public CountPropertyType getElementCount() {
        return elementCount;
    }

    /**
     * Sets the value of the elementCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link CountPropertyType }
     *     
     */
    public void setElementCount(CountPropertyType value) {
        this.elementCount = value;
    }

    /**
     * Gets the value of the elementType property.
     * 
     * @return
     *     possible object is
     *     {@link DataArrayType.ElementType }
     *     
     */
    public DataArrayType.ElementType getElementType() {
        return elementType;
    }

    /**
     * Sets the value of the elementType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataArrayType.ElementType }
     *     
     */
    public void setElementType(DataArrayType.ElementType value) {
        this.elementType = value;
    }

    /**
     * Gets the value of the encoding property.
     * 
     * @return
     *     possible object is
     *     {@link DataArrayType.Encoding }
     *     
     */
    public DataArrayType.Encoding getEncoding() {
        return encoding;
    }

    /**
     * Sets the value of the encoding property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataArrayType.Encoding }
     *     
     */
    public void setEncoding(DataArrayType.Encoding value) {
        this.encoding = value;
    }

    /**
     * Gets the value of the values property.
     * 
     * @return
     *     possible object is
     *     {@link EncodedValuesPropertyType }
     *     
     */
    public EncodedValuesPropertyType getValues() {
        return values;
    }

    /**
     * Sets the value of the values property.
     * 
     * @param value
     *     allowed object is
     *     {@link EncodedValuesPropertyType }
     *     
     */
    public void setValues(EncodedValuesPropertyType value) {
        this.values = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://www.opengis.net/swe/2.0}AbstractDataComponentPropertyType">
     *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class ElementType
        extends AbstractDataComponentPropertyType
    {

        @XmlAttribute(name = "name", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlSchemaType(name = "NCName")
        protected String name;

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element ref="{http://www.opengis.net/swe/2.0}AbstractEncoding"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "abstractEncoding"
    })
    public static class Encoding {

        @XmlElementRef(name = "AbstractEncoding", namespace = "http://www.opengis.net/swe/2.0", type = JAXBElement.class)
        protected JAXBElement<? extends AbstractEncodingType> abstractEncoding;

        /**
         * Gets the value of the abstractEncoding property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link AbstractEncodingType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryEncodingType }{@code >}
         *     {@link JAXBElement }{@code <}{@link TextEncodingType }{@code >}
         *     {@link JAXBElement }{@code <}{@link XMLEncodingType }{@code >}
         *     
         */
        public JAXBElement<? extends AbstractEncodingType> getAbstractEncoding() {
            return abstractEncoding;
        }

        /**
         * Sets the value of the abstractEncoding property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link AbstractEncodingType }{@code >}
         *     {@link JAXBElement }{@code <}{@link BinaryEncodingType }{@code >}
         *     {@link JAXBElement }{@code <}{@link TextEncodingType }{@code >}
         *     {@link JAXBElement }{@code <}{@link XMLEncodingType }{@code >}
         *     
         */
        public void setAbstractEncoding(JAXBElement<? extends AbstractEncodingType> value) {
            this.abstractEncoding = value;
        }

    }

}