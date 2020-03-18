//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.03.18 at 12:05:10 PM CET 
//


package net.opengis.swe;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AbstractSimpleComponentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AbstractSimpleComponentType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/swe/2.0}AbstractDataComponentType">
 *       &lt;sequence>
 *         &lt;element name="quality" type="{http://www.opengis.net/swe/2.0}QualityPropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="nilValues" type="{http://www.opengis.net/swe/2.0}NilValuesPropertyType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="referenceFrame" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="axisID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractSimpleComponentType", propOrder = {
    "quality",
    "nilValues"
})
@XmlSeeAlso({
    CategoryType.class,
    TimeType.class,
    CountType.class,
    CountRangeType.class,
    BooleanType.class,
    QuantityType.class,
    QuantityRangeType.class,
    TextType.class,
    CategoryRangeType.class,
    TimeRangeType.class
})
public abstract class AbstractSimpleComponentType
    extends AbstractDataComponentType
{

    protected List<QualityPropertyType> quality;
    protected NilValuesPropertyType nilValues;
    @XmlAttribute(name = "referenceFrame")
    @XmlSchemaType(name = "anyURI")
    protected String referenceFrame;
    @XmlAttribute(name = "axisID")
    protected String axisID;

    /**
     * Gets the value of the quality property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the quality property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQuality().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QualityPropertyType }
     * 
     * 
     */
    public List<QualityPropertyType> getQuality() {
        if (quality == null) {
            quality = new ArrayList<QualityPropertyType>();
        }
        return this.quality;
    }

    /**
     * Gets the value of the nilValues property.
     * 
     * @return
     *     possible object is
     *     {@link NilValuesPropertyType }
     *     
     */
    public NilValuesPropertyType getNilValues() {
        return nilValues;
    }

    /**
     * Sets the value of the nilValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link NilValuesPropertyType }
     *     
     */
    public void setNilValues(NilValuesPropertyType value) {
        this.nilValues = value;
    }

    /**
     * Gets the value of the referenceFrame property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceFrame() {
        return referenceFrame;
    }

    /**
     * Sets the value of the referenceFrame property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceFrame(String value) {
        this.referenceFrame = value;
    }

    /**
     * Gets the value of the axisID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAxisID() {
        return axisID;
    }

    /**
     * Sets the value of the axisID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAxisID(String value) {
        this.axisID = value;
    }

}
