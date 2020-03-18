//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.03.18 at 12:05:10 PM CET 
//


package org.isotc211._2005.gco;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.isotc211._2005.gmd.AbstractDQElementType;
import org.isotc211._2005.gmd.AbstractDQResultType;
import org.isotc211._2005.gmd.AbstractDSAggregateType;
import org.isotc211._2005.gmd.AbstractEXGeographicExtentType;
import org.isotc211._2005.gmd.AbstractMDContentInformationType;
import org.isotc211._2005.gmd.AbstractMDIdentificationType;
import org.isotc211._2005.gmd.AbstractMDSpatialRepresentationType;
import org.isotc211._2005.gmd.AbstractRSReferenceSystemType;
import org.isotc211._2005.gmd.CIAddressType;
import org.isotc211._2005.gmd.CICitationType;
import org.isotc211._2005.gmd.CIContactType;
import org.isotc211._2005.gmd.CIDateType;
import org.isotc211._2005.gmd.CIOnlineResourceType;
import org.isotc211._2005.gmd.CIResponsiblePartyType;
import org.isotc211._2005.gmd.CISeriesType;
import org.isotc211._2005.gmd.CITelephoneType;
import org.isotc211._2005.gmd.DQDataQualityType;
import org.isotc211._2005.gmd.DQScopeType;
import org.isotc211._2005.gmd.DSAssociationType;
import org.isotc211._2005.gmd.DSDataSetType;
import org.isotc211._2005.gmd.EXExtentType;
import org.isotc211._2005.gmd.EXTemporalExtentType;
import org.isotc211._2005.gmd.EXVerticalExtentType;
import org.isotc211._2005.gmd.LILineageType;
import org.isotc211._2005.gmd.LIProcessStepType;
import org.isotc211._2005.gmd.LISourceType;
import org.isotc211._2005.gmd.MDAggregateInformationType;
import org.isotc211._2005.gmd.MDApplicationSchemaInformationType;
import org.isotc211._2005.gmd.MDBrowseGraphicType;
import org.isotc211._2005.gmd.MDConstraintsType;
import org.isotc211._2005.gmd.MDDigitalTransferOptionsType;
import org.isotc211._2005.gmd.MDDimensionType;
import org.isotc211._2005.gmd.MDDistributionType;
import org.isotc211._2005.gmd.MDDistributorType;
import org.isotc211._2005.gmd.MDExtendedElementInformationType;
import org.isotc211._2005.gmd.MDFormatType;
import org.isotc211._2005.gmd.MDGeometricObjectsType;
import org.isotc211._2005.gmd.MDIdentifierType;
import org.isotc211._2005.gmd.MDKeywordsType;
import org.isotc211._2005.gmd.MDMaintenanceInformationType;
import org.isotc211._2005.gmd.MDMediumType;
import org.isotc211._2005.gmd.MDMetadataExtensionInformationType;
import org.isotc211._2005.gmd.MDMetadataType;
import org.isotc211._2005.gmd.MDPortrayalCatalogueReferenceType;
import org.isotc211._2005.gmd.MDRangeDimensionType;
import org.isotc211._2005.gmd.MDReferenceSystemType;
import org.isotc211._2005.gmd.MDRepresentativeFractionType;
import org.isotc211._2005.gmd.MDStandardOrderProcessType;
import org.isotc211._2005.gmd.MDUsageType;
import org.isotc211._2005.gmd.PTFreeTextType;
import org.isotc211._2005.gmd.PTLocaleType;


/**
 * <p>Java class for AbstractObject_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AbstractObject_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.isotc211.org/2005/gco}ObjectIdentification"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractObject_Type")
@XmlSeeAlso({
    CICitationType.class,
    EXExtentType.class,
    MDDimensionType.class,
    LILineageType.class,
    DQDataQualityType.class,
    CIContactType.class,
    MDApplicationSchemaInformationType.class,
    MDPortrayalCatalogueReferenceType.class,
    MDBrowseGraphicType.class,
    MDFormatType.class,
    CIDateType.class,
    DSAssociationType.class,
    AbstractRSReferenceSystemType.class,
    MDUsageType.class,
    MDMaintenanceInformationType.class,
    EXTemporalExtentType.class,
    MDReferenceSystemType.class,
    MDIdentifierType.class,
    MDMetadataType.class,
    MDDistributionType.class,
    CITelephoneType.class,
    MDRangeDimensionType.class,
    CISeriesType.class,
    LIProcessStepType.class,
    MDDistributorType.class,
    CIResponsiblePartyType.class,
    DQScopeType.class,
    AbstractEXGeographicExtentType.class,
    EXVerticalExtentType.class,
    CIOnlineResourceType.class,
    AbstractDQResultType.class,
    AbstractMDSpatialRepresentationType.class,
    MDDigitalTransferOptionsType.class,
    PTLocaleType.class,
    PTFreeTextType.class,
    MDGeometricObjectsType.class,
    MDMediumType.class,
    MDKeywordsType.class,
    MDExtendedElementInformationType.class,
    MDMetadataExtensionInformationType.class,
    LISourceType.class,
    AbstractMDIdentificationType.class,
    AbstractDSAggregateType.class,
    MDConstraintsType.class,
    CIAddressType.class,
    AbstractMDContentInformationType.class,
    MDAggregateInformationType.class,
    DSDataSetType.class,
    MDStandardOrderProcessType.class,
    AbstractDQElementType.class,
    MDRepresentativeFractionType.class,
    TypeNameType.class,
    MultiplicityRangeType.class,
    MultiplicityType.class,
    MemberNameType.class
})
public abstract class AbstractObjectType {

    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "uuid")
    protected String uuid;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the uuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets the value of the uuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUuid(String value) {
        this.uuid = value;
    }

}
