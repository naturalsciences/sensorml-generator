/*
 * To change this license header, choose ILicense Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.naturalsciences.bmdc.sensormlgenerator;

import be.naturalsciences.bmdc.cruise.csr.CodeListRealm;
import be.naturalsciences.bmdc.cruise.model.ILicense;
import be.naturalsciences.bmdc.cruise.model.ICruise;
import be.naturalsciences.bmdc.cruise.model.IEvent;
import be.naturalsciences.bmdc.cruise.model.ILinkedDataTerm;
import be.naturalsciences.bmdc.cruise.model.IOrganisation;
import be.naturalsciences.bmdc.cruise.model.IPerson;
import be.naturalsciences.bmdc.cruise.model.IProgram;
import static be.naturalsciences.bmdc.metadata.iso.ISO19115DatasetBuilder.GEMET_INSPIRE_THEMES_PUBLICATION_DATE;
import static be.naturalsciences.bmdc.metadata.iso.ISO19115DatasetBuilder.GEMET_INSPIRE_THEMES_THESAURUS_URL;
import be.naturalsciences.bmdc.metadata.model.IKeyword;
import be.naturalsciences.bmdc.metadata.model.Thesaurus;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import net.opengis.sensorml.EventListPropertyType;
import net.opengis.sensorml.EventListType;
import net.opengis.sensorml.EventPropertyType;
import net.opengis.sensorml.EventType;
import net.opengis.sensorml.PhysicalSystemType;
import org.seadatanet.csr.org.isotc211._2005.gco.CharacterStringPropertyType;
import org.seadatanet.csr.org.isotc211._2005.gco.CodeListValueType;
import org.seadatanet.csr.org.isotc211._2005.gco.DatePropertyType;
import org.seadatanet.csr.org.isotc211._2005.gmd.CIAddressPropertyType;
import org.seadatanet.csr.org.isotc211._2005.gmd.CIAddressType;
import org.seadatanet.csr.org.isotc211._2005.gmd.CICitationPropertyType;
import org.seadatanet.csr.org.isotc211._2005.gmd.CICitationType;
import org.seadatanet.csr.org.isotc211._2005.gmd.CIContactPropertyType;
import org.seadatanet.csr.org.isotc211._2005.gmd.CIContactType;
import org.seadatanet.csr.org.isotc211._2005.gmd.CIDatePropertyType;
import org.seadatanet.csr.org.isotc211._2005.gmd.CIDateType;
import org.seadatanet.csr.org.isotc211._2005.gmd.CIDateTypeCodePropertyType;
import org.seadatanet.csr.org.isotc211._2005.gmd.CIOnlineResourcePropertyType;
import org.seadatanet.csr.org.isotc211._2005.gmd.CIOnlineResourceType;
import org.seadatanet.csr.org.isotc211._2005.gmd.CIResponsiblePartyPropertyType;
import org.seadatanet.csr.org.isotc211._2005.gmd.CIResponsiblePartyType;
import org.seadatanet.csr.org.isotc211._2005.gmd.CIRoleCodePropertyType;
import org.seadatanet.csr.org.isotc211._2005.gmd.CITelephonePropertyType;
import org.seadatanet.csr.org.isotc211._2005.gmd.CITelephoneType;
import org.seadatanet.csr.org.isotc211._2005.gmd.LanguageCodePropertyType;
import org.seadatanet.csr.org.isotc211._2005.gmd.MDCharacterSetCodePropertyType;
import org.seadatanet.csr.org.isotc211._2005.gmd.MDConstraintsPropertyType;
import org.seadatanet.csr.org.isotc211._2005.gmd.MDConstraintsType;
import org.seadatanet.csr.org.isotc211._2005.gmd.MDIdentificationPropertyType;
import org.seadatanet.csr.org.isotc211._2005.gmd.MDIdentifierPropertyType;
import org.seadatanet.csr.org.isotc211._2005.gmd.MDIdentifierType;
import org.seadatanet.csr.org.isotc211._2005.gmd.MDKeywordTypeCodePropertyType;
import org.seadatanet.csr.org.isotc211._2005.gmd.MDKeywordsPropertyType;
import org.seadatanet.csr.org.isotc211._2005.gmd.MDKeywordsType;
import org.seadatanet.csr.org.isotc211._2005.gmd.MDLegalConstraintsPropertyType;
import org.seadatanet.csr.org.isotc211._2005.gmd.MDLegalConstraintsType;
import org.seadatanet.csr.org.isotc211._2005.gmd.MDMetadataExtensionInformationPropertyType;
import org.seadatanet.csr.org.isotc211._2005.gmd.MDMetadataType;
import org.seadatanet.csr.org.isotc211._2005.gmd.MDRestrictionCodePropertyType;
import org.seadatanet.csr.org.isotc211._2005.gmd.MDScopeCodePropertyType;
import org.seadatanet.csr.org.isotc211._2005.gmd.MDTopicCategoryCodePropertyType;
import org.seadatanet.csr.org.isotc211._2005.gmd.MDTopicCategoryCodeType;
import org.seadatanet.csr.org.isotc211._2005.gmd.URLPropertyType;
import org.seadatanet.csr.org.isotc211._2005.gmi.MIMetadataType;
import org.seadatanet.csr.org.isotc211._2005.gmx.AnchorType;
import org.seadatanet.csr.org.isotc211._2005.gmx.ObjectFactory;
import org.seadatanet.csr.org.seadatanet.MDKeywordsPropertyTypeSDN;
import org.seadatanet.csr.org.seadatanet.MDKeywordsTypeSDN;
import org.seadatanet.csr.org.seadatanet.SDNCountryCodePropertyType;
import org.seadatanet.csr.org.seadatanet.SDNDataIdentificationType;
import org.seadatanet.csr.org.seadatanet.SDNDeviceCategoryCodePropertyType;
import org.seadatanet.csr.org.seadatanet.SDNEDMERPCodePropertyType;
import org.seadatanet.csr.org.seadatanet.SDNEDMOCodePropertyType;
import org.seadatanet.csr.org.seadatanet.SDNHierarchyLevelNameCodePropertyType;
import org.seadatanet.csr.org.seadatanet.SDNKeyword;
import org.seadatanet.csr.org.seadatanet.SDNParameterDiscoveryCodePropertyType;
import org.seadatanet.csr.org.seadatanet.SDNPlatformCategoryCodePropertyType;
import org.seadatanet.csr.org.seadatanet.SDNPlatformCodePropertyType;
import org.seadatanet.csr.org.seadatanet.SDNPortCodePropertyType;
import org.seadatanet.csr.org.seadatanet.SDNWaterBodyCodePropertyType;

/**
 *
 * @author thomas
 */
public class SensorMLBuilder {

    public enum Role {
        DISTRIBUTOR, RESOURCEPROVIDER, COAUTHOR, EDITOR, CONTRIBUTOR, OWNER, USER, STAKEHOLDER, RIGHTS_HOLDER, FUNDER, PUBLISHER, AUTHOR, POINT_OF_CONTACT, PRINCIPAL_INVESTIGATOR, MEDIATOR, PROCESSOR, ORIGINATOR, CUSTODIAN, COLLABORATOR, SPONSOR
    }

    public static String GMX_CODELISTS = "http://vocab.nerc.ac.uk/isoCodelists/sdnCodelists/gmxCodeLists.xml";
    public static String CDI_CSR_CODELISTS = "http://vocab.nerc.ac.uk/isoCodelists/sdnCodelists/cdicsrCodeList.xml";
    public static String EDMO_EDMERP_CODELISTS = "http://seadatanet.maris2.nl/isocodelists/sdncodelists/edmo-edmerp-Codelists.xml";

    public static CodeListRealm REALM_ISO = new CodeListRealm("ISOTC211/19115", GMX_CODELISTS);
    public static CodeListRealm REALM_SDN_EDMO_EDMERP = new CodeListRealm("SeaDataNet", EDMO_EDMERP_CODELISTS);
    public static CodeListRealm REALM_SDN_ISO = new CodeListRealm("SeaDataNet", GMX_CODELISTS);
    public static CodeListRealm REALM_SDN_CDI_CSR = new CodeListRealm("SeaDataNet", CDI_CSR_CODELISTS);

    public static Map<String, String> CODE_SPACES_URLS = new HashMap<>();
    public static Map<Role, CIRoleCodePropertyType> ROLES = new HashMap<>();

    private ICruise cruise;

    public ICruise getCruise() {
        return cruise;
    }

    public static net.opengis.sensorml.ObjectFactory sensorml = new net.opengis.sensorml.ObjectFactory();

    public SensorMLBuilder(ICruise cruise) {

        this.cruise = cruise;
        /*CIRoleCodePropertyType resourceProvider = new CIRoleCodePropertyType();
        resourceProvider.setCIRoleCode(getCodeListValue(REALM_ISO, "CI_RoleCode", "resourceProvider", "resourceProvider"));

        CIRoleCodePropertyType publisher = new CIRoleCodePropertyType();
        publisher.setCIRoleCode(getCodeListValue(REALM_ISO, "CI_RoleCode", "publisher", "publisher"));

        CIRoleCodePropertyType author = new CIRoleCodePropertyType();
        author.setCIRoleCode(getCodeListValue(REALM_ISO, "CI_RoleCode", "author", "author"));

        CIRoleCodePropertyType custodian = new CIRoleCodePropertyType();
        custodian.setCIRoleCode(getCodeListValue(REALM_ISO, "CI_RoleCode", "custodian", "custodian"));

        CIRoleCodePropertyType owner = new CIRoleCodePropertyType();
        owner.setCIRoleCode(getCodeListValue(REALM_ISO, "CI_RoleCode", "owner", "owner"));

        CIRoleCodePropertyType user = new CIRoleCodePropertyType();
        user.setCIRoleCode(getCodeListValue(REALM_ISO, "CI_RoleCode", "user", "user"));

        CIRoleCodePropertyType distributor = new CIRoleCodePropertyType();
        distributor.setCIRoleCode(getCodeListValue(REALM_ISO, "CI_RoleCode", "distributor", "distributor"));

        CIRoleCodePropertyType originator = new CIRoleCodePropertyType();
        originator.setCIRoleCode(getCodeListValue(REALM_ISO, "CI_RoleCode", "originator", "originator"));

        CIRoleCodePropertyType pointOfContact = new CIRoleCodePropertyType();
        pointOfContact.setCIRoleCode(getCodeListValue(REALM_ISO, "CI_RoleCode", "pointOfContact", "pointOfContact"));

        CIRoleCodePropertyType principalInvestigator = new CIRoleCodePropertyType();
        principalInvestigator.setCIRoleCode(getCodeListValue(REALM_ISO, "CI_RoleCode", "principalInvestigator", "principalInvestigator"));

        CIRoleCodePropertyType processor = new CIRoleCodePropertyType();
        processor.setCIRoleCode(getCodeListValue(REALM_ISO, "CI_RoleCode", "processor", "processor"));*/

 /*ROLES.put(Role.RESOURCEPROVIDER, resourceProvider);
        ROLES.put(Role.PUBLISHER, publisher);
        ROLES.put(Role.AUTHOR, author);
        ROLES.put(Role.CUSTODIAN, custodian);
        ROLES.put(Role.OWNER, owner);
        ROLES.put(Role.USER, user);
        ROLES.put(Role.DISTRIBUTOR, distributor);
        ROLES.put(Role.ORIGINATOR, originator);
        ROLES.put(Role.POINT_OF_CONTACT, pointOfContact);
        ROLES.put(Role.PRINCIPAL_INVESTIGATOR, principalInvestigator);
        ROLES.put(Role.PROCESSOR, processor);*/
    }

    public PhysicalSystemType getMetadata() throws JAXBException {
        if (cruise.getEvents() == null) {
            throw new IllegalArgumentException("Cruise must have an Event collection.");
        }
        PhysicalSystemType physicalSystem = sensorml.createPhysicalSystemType();

        EventListPropertyType eventList = sensorml.createEventListPropertyType();

        for (IEvent event : cruise.getEvents()) {
            EventPropertyType eventType = sensorml.createEventPropertyType();
            eventType.setEvent(getEvent(event));
            eventList.getEventList().getEvent().add(eventType);
        }

        physicalSystem.getHistory().add(eventList);

        return physicalSystem;
    }

    private EventType getEvent(IEvent e) {
        EventType event = sensorml.createEventType();
        event.setIdentifier(e.getIdentifier());
        return event;
    }
}
