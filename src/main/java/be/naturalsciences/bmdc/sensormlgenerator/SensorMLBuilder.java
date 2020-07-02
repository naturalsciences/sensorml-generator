/*
 * To change this license header, choose ILicense Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.naturalsciences.bmdc.sensormlgenerator;

import be.naturalsciences.bmdc.cruise.model.IEvent;
import be.naturalsciences.bmdc.cruise.model.ILinkedDataTerm;
import be.naturalsciences.bmdc.cruise.model.IPerson;
import be.naturalsciences.bmdc.cruise.model.IPlatform;
import be.naturalsciences.bmdc.cruise.model.IProperty;
import be.naturalsciences.bmdc.cruise.model.ITool;
import java.math.BigInteger;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import net.opengis.gml.CodeWithAuthorityType;
import net.opengis.gml.TimeInstantType;
import net.opengis.gml.TimePositionType;
import net.opengis.sensorml.ClassifierListPropertyType;
import net.opengis.sensorml.ClassifierListType;
import net.opengis.sensorml.ClassifierListType.Classifier;
import net.opengis.sensorml.ComponentListPropertyType;
import net.opengis.sensorml.ComponentListType;
import net.opengis.sensorml.ContactListPropertyType;
import net.opengis.sensorml.ContactListType;
import net.opengis.sensorml.EventListPropertyType;
import net.opengis.sensorml.EventListType;
import net.opengis.sensorml.EventPropertyType;
import net.opengis.sensorml.EventType;
import net.opengis.sensorml.EventType.Time;
import net.opengis.sensorml.IdentifierListPropertyType;
import net.opengis.sensorml.IdentifierListType;
import net.opengis.sensorml.IdentifierListType.Identifier;
import net.opengis.sensorml.PhysicalComponentType;
import net.opengis.sensorml.PhysicalSystemType;
import net.opengis.sensorml.TermType;
import net.opengis.swe.AbstractDataComponentPropertyType;
import net.opengis.swe.CategoryType;
import net.opengis.swe.CountPropertyType;
import net.opengis.swe.CountType;
import net.opengis.swe.TextPropertyType;
import net.opengis.swe.TextType;
import net.opengis.swe.QuantityPropertyType;
import net.opengis.swe.CategoryPropertyType;
import net.opengis.swe.QuantityType;
import net.opengis.swe.UnitReference;
import org.isotc211._2005.gco.CharacterStringPropertyType;
import org.isotc211._2005.gmd.CIResponsiblePartyPropertyType;
import org.isotc211._2005.gmd.CIResponsiblePartyType;

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

  /*  public static CodeListRealm REALM_ISO = new CodeListRealm("ISOTC211/19115", GMX_CODELISTS);
    public static CodeListRealm REALM_SDN_EDMO_EDMERP = new CodeListRealm("SeaDataNet", EDMO_EDMERP_CODELISTS);
    public static CodeListRealm REALM_SDN_ISO = new CodeListRealm("SeaDataNet", GMX_CODELISTS);
    public static CodeListRealm REALM_SDN_CDI_CSR = new CodeListRealm("SeaDataNet", CDI_CSR_CODELISTS);*/

    public static Map<String, String> CODE_SPACES_URLS = new HashMap<>();
  //  public static Map<Role, CIRoleCodePropertyType> ROLES = new HashMap<>();

    private IPlatform platform;
    private String server;

    public IPlatform getPlatform() {
        return platform;
    }

    public static net.opengis.sensorml.ObjectFactory sml = new net.opengis.sensorml.ObjectFactory();
    public static net.opengis.swe.ObjectFactory swe = new net.opengis.swe.ObjectFactory();
    public static net.opengis.gml.ObjectFactory gml = new net.opengis.gml.ObjectFactory();
    public static org.isotc211._2005.gmd.ObjectFactory gmd = new org.isotc211._2005.gmd.ObjectFactory();
    public static org.isotc211._2005.gco.ObjectFactory gco = new org.isotc211._2005.gco.ObjectFactory();

    public SensorMLBuilder(IPlatform platform, String server) {

        this.platform = platform;
        this.server = server;
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
        processor.setCIRoleCode(getCodeListValue(REALM_ISO, "CI_RoleCode", "processor", "processor"));

        ROLES.put(Role.RESOURCEPROVIDER, resourceProvider);
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

    public static <K, V> Map<K, List<V>> upsertMapOfList(Map<K, List<V>> map, K key, V element) {
        List<V> l = map.get(key);

        if (l != null) {
            l.add(element);
            map.put(key, l);
        } else {
            map.put(key, new ArrayList<>(Arrays.asList(element)));
        }
        return map;
    }

    /**
     * *
     * Create a physical component for a single tool and its associated events.
     * Please only provide the events associated to this tool!
     *
     * @param tool
     * @param events
     * @return
     */
    public PhysicalComponentType getPhysicalComponent(ITool tool, Collection<? extends IEvent> events) {
        /*    Map<ITool, List<IEvent>> toolEvent = new HashMap<>();
        for (IEvent event : events) {
            upsertMapOfList(toolEvent, event.getTool(), event);
        }*/

        PhysicalComponentType physicalComponent = sml.createPhysicalComponentType();
        /*IDENTIFIER*/
        CodeWithAuthorityType identifier = gml.createCodeWithAuthorityType();
        String sensorId = tool.getTerm().getIdentifier();
        identifier.setValue(sensorId);
        physicalComponent.setIdentifier(identifier);
        physicalComponent.setId(sensorId);

        /*CLASSIFICATION*/
        ClassifierListPropertyType classifierListPropertyType = sml.createClassifierListPropertyType();
        ClassifierListType classifierList = getClassifierList(classifierListPropertyType);
        Set<ILinkedDataTerm> toolCategories = events.stream().map(e -> e.getToolCategory()).collect(Collectors.toSet());
        for (ILinkedDataTerm toolCategory : toolCategories) {
            addClassifier(classifierList, getLinkedDataTerm("http://vocab.nerc.ac.uk/collection/W06/current/CLSS0002", "Instrument type"), toolCategory);
        }

        physicalComponent.getClassification().add(classifierListPropertyType);
        /*EVENT HISTORY*/
        EventListPropertyType eventListProperty = sml.createEventListPropertyType();
        EventListType eventList = sml.createEventListType();

        for (IEvent event : events) {
            EventPropertyType eventTypeProperty = sml.createEventPropertyType();
            eventTypeProperty.setEvent(getEvent(event));
            eventList.getEvent().add(eventTypeProperty);
            eventListProperty.setEventList(eventList);
        }
        physicalComponent.getHistory().add(eventListProperty);
        return physicalComponent;
    }

    public PhysicalSystemType getPhysicalSystem() throws JAXBException {
        /*if (cruise.getEvents() == null) {
            throw new IllegalArgumentException("Cruise must have an Event collection.");
        }*/
        PhysicalSystemType physicalSystem = sml.createPhysicalSystemType();

        /*COMPONENT LIST*/
        ComponentListPropertyType componentsType = sml.createComponentListPropertyType();
        ComponentListType componentListType = sml.createComponentListType();
        componentsType.setComponentList(componentListType);
        List<ComponentListType.Component> componentList = componentListType.getComponent();
        physicalSystem.setComponents(componentsType);
        Set<? extends ITool> instruments = this.platform.getInstruments();
        for (ITool instrument : instruments) {
            ComponentListType.Component component = sml.createComponentListTypeComponent();
            component.setName(instrument.getTerm().getName());
            component.setTitle(instrument.getTerm().getTransitiveIdentifier());
            component.setHref(server + "/sml?deviceUrn=" + ILinkedDataTerm.getUrnFromUrl(instrument.getTerm().getIdentifier()));
            componentList.add(component);
        }

        return physicalSystem;
    }

    private TermType getSmlTerm(ILinkedDataTerm key, ILinkedDataTerm value) {
        TermType termType = sml.createTermType();
        termType.setDefinition(key.getIdentifier());
        termType.setLabel(key.getName());
        termType.setValue(value.getIdentifier());
        termType.setId(value.getName());
        return termType;
    }

    private TermType getTerm(ILinkedDataTerm key, String value) {
        TermType termType = sml.createTermType();
        termType.setDefinition(key.getTransitiveIdentifier());
        termType.setLabel(key.getName());
        termType.setValue(value);

        return termType;
    }

    private ILinkedDataTerm getLinkedDataTerm(String identifier, String name) {
        return new ILinkedDataTerm() {
            @Override
            public String getIdentifier() {
                return identifier;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public void setIdentifier(String identifier) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setName(String name) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getTransitiveIdentifier() {
                return identifier;
            }

            @Override
            public void setTransitiveIdentifier(String transitiveIdentifier) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getUrn() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setUrn(String urn) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getTransitiveUrn() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setTransitiveUrn(String transitiveUrn) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public ILinkedDataTerm getTerm() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setTerm(ILinkedDataTerm country) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    private ClassifierListType getClassifierList(ClassifierListPropertyType classifierListProperty) {
        ClassifierListType classifierList = sml.createClassifierListType();
        classifierListProperty.setClassifierList(classifierList);
        return classifierList;
    }

    private void addClassifier(ClassifierListType classifierList, ILinkedDataTerm key, ILinkedDataTerm value) {
        Classifier cl1 = sml.createClassifierListTypeClassifier();
        cl1.setTerm(getSmlTerm(key, value));
        classifierList.getClassifier().add(cl1);
    }

    private EventType getEvent(IEvent e) {
        EventType event = sml.createEventType();

        /*LABEL*/
        event.setLabel(e.toString());

        /*DESCRIPTION*/
        event.setDescription(e.toString());

        /*IDENTIFIER*/
        IdentifierListPropertyType identifierListProperty = sml.createIdentifierListPropertyType();
        IdentifierListType identifierList = sml.createIdentifierListType();
        Identifier id = sml.createIdentifierListTypeIdentifier();
        id.setTerm(getTerm(getLinkedDataTerm("http://vocab.nerc.ac.uk/collection/W07/current/IDEN0007", "UUID"), e.getIdentifier()));
        identifierList.getRest().add(sml.createIdentifierListTypeIdentifier(id));
        identifierListProperty.setIdentifierList(identifierList);
        event.getIdentification().add(identifierListProperty);

        /*CLASSIFICATION*/
        ClassifierListPropertyType classifierListPropertyType = sml.createClassifierListPropertyType();
        ClassifierListType classifierList = getClassifierList(classifierListPropertyType);

        addClassifier(classifierList, getLinkedDataTerm("http://vocab.nerc.ac.uk/collection/W09/current/OOO1", "Event process"), e.getProcess());
        addClassifier(classifierList, getLinkedDataTerm("http://vocab.nerc.ac.uk/collection/W09/current/OOO2", "Event action"), e.getAction());
        addClassifier(classifierList, getLinkedDataTerm("http://vocab.nerc.ac.uk/collection/W09/current/OOO3", "Event subject"), e.getSubject());

        event.getClassification().add(classifierListPropertyType);

        /*CONTACTS*/
        ContactListPropertyType contactListProperty = sml.createContactListPropertyType();
        ContactListType contactList = sml.createContactListType();
        contactList.getContact().add(createContact(e.getActor()));
        if (e.getProgram() != null && e.getProgram().getPrincipalInvestigators() != null) {
            for (IPerson principalInvestigator : e.getProgram().getPrincipalInvestigators()) {
                contactList.getContact().add(createContact(principalInvestigator));
            }
        }

        contactListProperty.setContactList(contactList);
        event.getContacts().add(contactListProperty);
        /*TIME*/
        Time time = sml.createEventTypeTime();
        TimeInstantType timeInstant = gml.createTimeInstantType();
        time.setTimeInstant(timeInstant);
        TimePositionType timePosition = gml.createTimePositionType();
        timeInstant.setTimePosition(timePosition);
        timePosition.getValue().add(e.getTimeStamp().format(DateTimeFormatter.ISO_INSTANT));
        time.setTimeInstant(timeInstant);
        event.setTime(time);

        /*PROPERTIES*/
        for (IProperty property : e.getProperties()) {
            TextPropertyType textProperty = createTextProperty(property);
            QuantityPropertyType quantityProperty = createQuantityProperty(property);
            CountPropertyType countProperty = createCountProperty(property);
            if (textProperty != null) {
                event.getProperty().add(textProperty);
            }
            if (quantityProperty != null) {
                event.getProperty().add(quantityProperty);
            }
            if (countProperty != null) {
                event.getProperty().add(countProperty);
            }
        }

        return event;
    }

    private TextPropertyType createTextProperty(IProperty property) {
        String valueString = property.getValue();
        if (Utils.isText(valueString) || (Utils.isDouble(valueString) && property.getUom() == null)) {
            TextPropertyType smlProperty = swe.createTextPropertyType();
            TextType value = swe.createTextType();
            smlProperty.setText(value);
            value.setDefinition(property.getKey().getIdentifier());
            value.setValue(property.getValue());
            value.setLabel(property.getKey().getName());
            return smlProperty;
        }
        return null;
    }

    private CountPropertyType createCountProperty(IProperty property) {
        if (Utils.isInteger(property.getValue()) && property.getUom() == null) {
            CountPropertyType smlProperty = swe.createCountPropertyType();
            CountType value = swe.createCountType();
            smlProperty.setCount(value);
            value.setDefinition(property.getKey().getIdentifier());
            value.setValue(new BigInteger(property.getValue()));
            value.setLabel(property.getKey().getName());
            return smlProperty;
        }
        return null;
    }

    private QuantityPropertyType createQuantityProperty(IProperty property) {
        if ((Utils.isInteger(property.getValue()) || Utils.isDouble(property.getValue())) && property.getUom() != null) {
            QuantityPropertyType smlProperty = swe.createQuantityPropertyType();
            QuantityType value = swe.createQuantityType();
            smlProperty.setQuantity(value);
            value.setDefinition(property.getKey().getIdentifier());
            value.setValue(new Double(property.getValue()));
            value.setLabel(property.getKey().getName());
            UnitReference unit = swe.createUnitReference();
            unit.setCode(property.getUom());
            value.setUom(unit);
            return smlProperty;
        }
        return null;
    }

    private CIResponsiblePartyPropertyType createContact(IPerson actor/*, Role role*/) {

        CIResponsiblePartyPropertyType respPartyProperty = gmd.createCIResponsiblePartyPropertyType();
        CIResponsiblePartyType respParty = gmd.createCIResponsiblePartyType();

        respPartyProperty.setCIResponsibleParty(respParty);
        respParty.setIndividualName(createString(actor.getFirstName() + " " + actor.getLastName()));
        // if (role==)
        // CIRoleCodePropertyType roleProperty;
        // respParty.setRole();
        return respPartyProperty;

    }

    private CharacterStringPropertyType createString(String string) {
        JAXBElement<String> jaxbElement = gco.createCharacterString(string);
        CharacterStringPropertyType c = gco.createCharacterStringPropertyType();
        c.setCharacterString(jaxbElement);

        return c;

    }

}
