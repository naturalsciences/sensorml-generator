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
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DataArrayPropertyByValueType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataArrayPropertyByValueType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/swe/2.0}DataArray"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataArrayPropertyByValueType", propOrder = {
    "dataArray"
})
public class DataArrayPropertyByValueType {

    @XmlElementRef(name = "DataArray", namespace = "http://www.opengis.net/swe/2.0", type = JAXBElement.class)
    protected JAXBElement<? extends DataArrayType> dataArray;

    /**
     * Gets the value of the dataArray property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link MatrixType }{@code >}
     *     {@link JAXBElement }{@code <}{@link DataArrayType }{@code >}
     *     
     */
    public JAXBElement<? extends DataArrayType> getDataArray() {
        return dataArray;
    }

    /**
     * Sets the value of the dataArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link MatrixType }{@code >}
     *     {@link JAXBElement }{@code <}{@link DataArrayType }{@code >}
     *     
     */
    public void setDataArray(JAXBElement<? extends DataArrayType> value) {
        this.dataArray = value;
    }

}
