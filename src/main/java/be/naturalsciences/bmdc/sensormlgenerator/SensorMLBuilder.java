/*
 * To change this license header, choose ILicense Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.naturalsciences.bmdc.sensormlgenerator;

import be.naturalsciences.bmdc.cruise.model.IEvent;
import be.naturalsciences.bmdc.cruise.model.ILinkedDataTerm;
import be.naturalsciences.bmdc.cruise.model.IOrganisation;
import be.naturalsciences.bmdc.cruise.model.IPerson;
import be.naturalsciences.bmdc.cruise.model.IPlatform;
import be.naturalsciences.bmdc.cruise.model.IProperty;
import be.naturalsciences.bmdc.cruise.model.ITool;
import be.naturalsciences.bmdc.sensormlgenerator.EarsTermUtils.ProcessAction;
import java.math.BigInteger;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import net.opengis.gml.CodeType;
import net.opengis.gml.CodeWithAuthorityType;
import net.opengis.gml.CoordinateSystemAxisPropertyType;
import net.opengis.gml.LocationPropertyType;
import net.opengis.gml.StringOrRefType;
import net.opengis.gml.TimeIndeterminateValueType;
import net.opengis.gml.TimeInstantType;
import net.opengis.gml.TimeIntervalLengthType;
import net.opengis.gml.TimePeriodType;
import net.opengis.gml.TimePositionType;
import net.opengis.gml.TimePrimitivePropertyType;
import net.opengis.sensorml.AbstractPhysicalProcessType;
import net.opengis.sensorml.CapabilityListPropertyType;
import net.opengis.sensorml.CapabilityListType;
import net.opengis.sensorml.CharacteristicListType;
import net.opengis.sensorml.ClassifierListPropertyType;
import net.opengis.sensorml.ClassifierListType;
import net.opengis.sensorml.ClassifierListType.Classifier;
import net.opengis.sensorml.ComponentListPropertyType;
import net.opengis.sensorml.ComponentListType;
import net.opengis.sensorml.ContactListPropertyType;
import net.opengis.sensorml.ContactListType;
import net.opengis.sensorml.DescribedObjectType.Capabilities;
import net.opengis.sensorml.DescribedObjectType.Characteristics;
import net.opengis.sensorml.DescribedObjectType.ValidTime;
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
import net.opengis.sensorml.SpatialFrameType;
import net.opengis.sensorml.TermType;
import net.opengis.swe.AbstractDataComponentPropertyType;
import net.opengis.swe.AbstractDataComponentType;
import net.opengis.swe.AbstractSimpleComponentType;
import net.opengis.swe.CountPropertyType;
import net.opengis.swe.CountType;
import net.opengis.swe.TextPropertyType;
import net.opengis.swe.TextType;
import net.opengis.swe.QuantityPropertyType;
import net.opengis.swe.QuantityType;
import net.opengis.swe.UnitReference;
import org.isotc211._2005.gco.CharacterStringPropertyType;
import org.isotc211._2005.gco.CodeListValueType;
import org.isotc211._2005.gmd.CIAddressPropertyType;
import org.isotc211._2005.gmd.CIAddressType;
import org.isotc211._2005.gmd.CIContactPropertyType;
import org.isotc211._2005.gmd.CIContactType;
import org.isotc211._2005.gmd.CIResponsiblePartyPropertyType;
import org.isotc211._2005.gmd.CIResponsiblePartyType;
import org.isotc211._2005.gmd.CIRoleCodePropertyType;

/**
 *
 * @author thomas
 */
public class SensorMLBuilder {

    private static final String REALM_ISO = "http://www.isotc211.org/2005/resources/Codelist/gmxCodelists.xml";

    public enum Role {
        DISTRIBUTOR, RESOURCEPROVIDER, COAUTHOR, EDITOR, CONTRIBUTOR, OWNER, USER, STAKEHOLDER, RIGHTS_HOLDER, FUNDER, PUBLISHER, AUTHOR, POINT_OF_CONTACT, PRINCIPAL_INVESTIGATOR, MEDIATOR, PROCESSOR, ORIGINATOR, CUSTODIAN, COLLABORATOR, SPONSOR
    }

    public static String GMX_CODELISTS = "http://vocab.nerc.ac.uk/isoCodelists/sdnCodelists/gmxCodeLists.xml";
    public static String CDI_CSR_CODELISTS = "http://vocab.nerc.ac.uk/isoCodelists/sdnCodelists/cdicsrCodeList.xml";
    public static String EDMO_EDMERP_CODELISTS = "http://seadatanet.maris2.nl/isocodelists/sdncodelists/edmo-edmerp-Codelists.xml";

    public static Map<Role, CIRoleCodePropertyType> ROLES = new HashMap<>();

    private IPlatform platform;
    private String server;
    private final IOrganisation metadataResponsible;

    public IPlatform getPlatform() {
        return platform;
    }

    public static net.opengis.sensorml.ObjectFactory sml = new net.opengis.sensorml.ObjectFactory();
    public static net.opengis.swe.ObjectFactory swe = new net.opengis.swe.ObjectFactory();
    public static net.opengis.gml.ObjectFactory gml = new net.opengis.gml.ObjectFactory();
    public static org.isotc211._2005.gmd.ObjectFactory gmd = new org.isotc211._2005.gmd.ObjectFactory();
    public static org.isotc211._2005.gco.ObjectFactory gco = new org.isotc211._2005.gco.ObjectFactory();

    public CodeListValueType getCodeListValue(String codeSpace, String codeList, String codeListValue, String value) {
        CodeListValueType r = gco.createCodeListValueType();
        r.setCodeList(codeList + "#" + codeSpace);
        r.setCodeListValue(codeListValue);

        r.setValue(value);
        return r;
    }

    public SensorMLBuilder(IPlatform platform, String server, IOrganisation metadataResponsible) {

        this.platform = platform;
        this.server = server;
        this.metadataResponsible = metadataResponsible;
        CIRoleCodePropertyType resourceProvider = new CIRoleCodePropertyType();
        resourceProvider.setCIRoleCode(getCodeListValue("CI_RoleCode", REALM_ISO, "publisher", "publisher"));

        CIRoleCodePropertyType publisher = new CIRoleCodePropertyType();
        publisher.setCIRoleCode(getCodeListValue("CI_RoleCode", REALM_ISO, "publisher", "publisher"));

        CIRoleCodePropertyType author = new CIRoleCodePropertyType();
        author.setCIRoleCode(getCodeListValue("CI_RoleCode", REALM_ISO, "author", "author"));

        CIRoleCodePropertyType custodian = new CIRoleCodePropertyType();
        custodian.setCIRoleCode(getCodeListValue("CI_RoleCode", REALM_ISO, "custodian", "custodian"));

        CIRoleCodePropertyType owner = new CIRoleCodePropertyType();
        owner.setCIRoleCode(getCodeListValue("CI_RoleCode", REALM_ISO, "owner", "owner"));

        CIRoleCodePropertyType user = new CIRoleCodePropertyType();
        user.setCIRoleCode(getCodeListValue("CI_RoleCode", REALM_ISO, "user", "user"));

        CIRoleCodePropertyType distributor = new CIRoleCodePropertyType();
        distributor.setCIRoleCode(getCodeListValue("CI_RoleCode", REALM_ISO, "distributor", "distributor"));

        CIRoleCodePropertyType originator = new CIRoleCodePropertyType();
        originator.setCIRoleCode(getCodeListValue("CI_RoleCode", REALM_ISO, "originator", "originator"));

        CIRoleCodePropertyType pointOfContact = new CIRoleCodePropertyType();
        pointOfContact.setCIRoleCode(getCodeListValue("CI_RoleCode", REALM_ISO, "pointOfContact", "pointOfContact"));

        CIRoleCodePropertyType principalInvestigator = new CIRoleCodePropertyType();
        principalInvestigator.setCIRoleCode(getCodeListValue("CI_RoleCode", REALM_ISO, "principalInvestigator", "principalInvestigator"));

        CIRoleCodePropertyType processor = new CIRoleCodePropertyType();
        processor.setCIRoleCode(getCodeListValue("CI_RoleCode", REALM_ISO, "processor", "processor"));

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
        ROLES.put(Role.PROCESSOR, processor);
    }

    public static <K, V> Map<K, List<V>> upsertMapOfList(Map<K, List<V>> map, K key, V element) {
        List<V> l = map.get(key);

        if (l != null) {
            boolean add = l.add(element);
            map.put(key, l);
        } else {
            map.put(key, new ArrayList<>(Arrays.asList(element)));
        }
        return map;
    }
    static int componentCounter = 0;

    /**
     * *
     * Enrich either a PhysicalSystem or PhysicalComponent with SensorML
     * elements.
     *
     * @param component
     */
    private void decoratePhysicalComponentType(AbstractPhysicalProcessType component, ILinkedDataTerm componentTerm, Collection<? extends IEvent> events) {

        /*IDENTIFIER*/
        CodeWithAuthorityType identifier = gml.createCodeWithAuthorityType();
        String sensorInstanceUrl = componentTerm.getIdentifier(); //get the identifier of the sensor instance

        identifier.setValue(sensorInstanceUrl);
        component.setIdentifier(identifier);
        CodeType name = gml.createCodeType();
        name.setValue(componentTerm.getName());
        component.getName().add(name);
        //physicalComponent.setDescription(tool.getTerm().getDescription());
        component.setId("component_" + componentCounter);
        componentCounter++;
        /*CONTACTS*/
        ContactListPropertyType contactListProperty = sml.createContactListPropertyType();
        ContactListType contactList = sml.createContactListType();
        contactList.getContact().add(createContact(metadataResponsible, Role.POINT_OF_CONTACT));
        contactList.getContact().add(createContact(this.platform.getVesselOperator(), Role.POINT_OF_CONTACT));
        contactListProperty.setContactList(contactList);
        component.getContacts().add(contactListProperty);
        /*EVENT HISTORY*/
        if (events != null && !events.isEmpty()) {
            EventListPropertyType eventListProperty = sml.createEventListPropertyType();
            EventListType eventList = sml.createEventListType();

            for (IEvent event : events) {
                EventPropertyType eventTypeProperty = sml.createEventPropertyType();
                eventTypeProperty.setEvent(getEvent(event));
                eventList.getEvent().add(eventTypeProperty);
                eventListProperty.setEventList(eventList);
            }
            component.getHistory().add(eventListProperty);
        }
    }

    private void decoratePlatform(PhysicalSystemType system, IPlatform platform) {
    }

    private void decorateInstrumentOrSensor(AbstractPhysicalProcessType systemOrComponent, ITool tool, Collection<? extends IEvent> events) {
        /*IDENTIFICATION*/
        IdentifierListPropertyType identifierListPropertyType = sml.createIdentifierListPropertyType();
        IdentifierListType identifierList = getIdentifierList(identifierListPropertyType);
        addIdentifier(identifierList, getLinkedDataTerm("http://vocab.nerc.ac.uk/collection/W07/current/IDEN0003", "Model name"), tool.getTerm()); //value with name and url
        addIdentifier(identifierList, getLinkedDataTerm("http://vocab.nerc.ac.uk/collection/W07/current/IDEN0003", "Model name"), tool.getTerm().getName()); //value with just name
        addIdentifier(identifierList, getLinkedDataTerm("http://vocab.nerc.ac.uk/collection/W07/current/IDEN0005/", "Serial number"), tool.getSerialNumber());

        systemOrComponent.getIdentification().add(identifierListPropertyType);

        /*CLASSIFICATION*/
        ClassifierListPropertyType classifierListPropertyType = sml.createClassifierListPropertyType();
        ClassifierListType classifierList = getClassifierList(classifierListPropertyType);
        Set<ILinkedDataTerm> toolCategories = events.stream().map(e -> e.getToolCategory()).collect(Collectors.toSet());
        for (ILinkedDataTerm toolCategory : toolCategories) {
            addClassifier(classifierList, getLinkedDataTerm("http://vocab.nerc.ac.uk/collection/W06/current/CLSS0002", "Instrument type"), toolCategory); // add the L05
        }
        //addClassifier(classifierList, "Instrument model", tool.getTerm()); // add the L22

        systemOrComponent.getClassification().add(classifierListPropertyType);

        /*VALID TIME*/
        EarsTermUtils.Period period = EarsTermUtils.findEventTimerangeEager(new ArrayList(events), new ProcessAction[]{new ProcessAction("Installation", "Start"), new ProcessAction("Installation", "End")}, new ProcessAction[]{new ProcessAction("Removal", "Start"), new ProcessAction("Removal", "End")});
        TimePeriodType timePeriod = gml.createTimePeriodType();
        timePeriod.setBeginPosition(getTimePosition(period.start, false));
        timePeriod.setEndPosition(getTimePosition(period.end, true));
        timePeriod.setId("deploymentDates");
        ValidTime validTime = sml.createDescribedObjectTypeValidTime();
        validTime.setTimePeriod(timePeriod);
        systemOrComponent.getValidTime().add(validTime);

        /*CHARACTERISTICS*/
        Characteristics characteristics = sml.createDescribedObjectTypeCharacteristics();
        CharacteristicListType characteristicsList = sml.createCharacteristicListType();
        if (tool.getCharacteristics() != null && !tool.getCharacteristics().isEmpty()) {
            for (IProperty prop : tool.getCharacteristics()) {
                String identifier = prop.getKey().getIdentifier();
                if (!identifier.equals("http://ontologies.ef-ears.eu/ears2/1#pry_25") && !identifier.equals("http://ontologies.ef-ears.eu/ears2/1#pry_26") && !identifier.equals("http://ontologies.ef-ears.eu/ears2/1#pry_1") && !identifier.equals("http://ontologies.ef-ears.eu/ears2/1#pry_27")) {
                    CharacteristicListType.Characteristic characteristic = sml.createCharacteristicListTypeCharacteristic();
                    characteristicsList.getCharacteristic().add(characteristic);
                    characteristic = (CharacteristicListType.Characteristic) decorateCharacteristicOrCapability(characteristic, prop);
                }
            }
            characteristics.setCharacteristicList(characteristicsList);
            systemOrComponent.getCharacteristics().add(characteristics);
        }

        /*CAPABILITIES*/
        Capabilities capabilities = sml.createDescribedObjectTypeCapabilities();
        CapabilityListType capabilityList = sml.createCapabilityListType();
        if (tool.getCapabilities() != null && !tool.getCapabilities().isEmpty()) {
            for (IProperty prop : tool.getCapabilities()) {
                CapabilityListType.Capability capability = sml.createCapabilityListTypeCapability();
                capabilityList.getCapability().add(capability);
                capability = (CapabilityListType.Capability) decorateCharacteristicOrCapability(capability, prop);
            }

            capabilities.setCapabilityList(capabilityList);
            systemOrComponent.getCapabilities().add(capabilities);
        }

        /*LOCATION*/
        IProperty location = tool.getCharacteristics().stream().filter(t -> t.getKey().getIdentifier().equals("http://ontologies.ef-ears.eu/ears2/1#pry_27")).findFirst().orElse(null);
        LocationPropertyType lpt = gml.createLocationPropertyType();
        lpt.setLocationString(createString2(location.getValue()));
        systemOrComponent.setLocation(gml.createLocation(lpt));
        /*LOCAL REFERENCE FRAME*/
        AbstractPhysicalProcessType.LocalReferenceFrame f = new AbstractPhysicalProcessType.LocalReferenceFrame();
        SpatialFrameType spatialFrame = sml.createSpatialFrameType();
        f.setSpatialFrame(spatialFrame);
        spatialFrame.setOrigin("Starboard GPS Dome");
        IProperty xOffset = tool.getCharacteristics().stream().filter(t -> t.getKey().getIdentifier().equals("http://ontologies.ef-ears.eu/ears2/1#pry_25")).findFirst().orElse(null);
        IProperty yOffset = tool.getCharacteristics().stream().filter(t -> t.getKey().getIdentifier().equals("http://ontologies.ef-ears.eu/ears2/1#pry_26")).findFirst().orElse(null);
        IProperty zOffset = tool.getCharacteristics().stream().filter(t -> t.getKey().getIdentifier().equals("http://ontologies.ef-ears.eu/ears2/1#pry_1")).findFirst().orElse(null);

        if (xOffset != null && yOffset != null && zOffset != null) {
            SpatialFrameType.Axis xAxis = sml.createSpatialFrameTypeAxis();
            xAxis.setName("Starboard-Port axis (X)");
            xAxis.setValue(xOffset.getValue() + " " + xOffset.getUom());
            SpatialFrameType.Axis yAxis = sml.createSpatialFrameTypeAxis();
            yAxis.setName("Stern-bow axis (Y)");
            xAxis.setValue(yOffset.getValue() + " " + yOffset.getUom());
            SpatialFrameType.Axis zAxis = sml.createSpatialFrameTypeAxis();
            zAxis.setName("Vertical axis (Z)");
            zAxis.setValue(zOffset.getValue() + " " + zOffset.getUom());
            spatialFrame.getAxis().add(xAxis);
            spatialFrame.getAxis().add(yAxis);
            spatialFrame.getAxis().add(zAxis);
            systemOrComponent.getLocalReferenceFrame().add(f);
        }
    }

    public AbstractDataComponentPropertyType decorateCharacteristicOrCapability(AbstractDataComponentPropertyType charOrCapa, IProperty prop) {
        charOrCapa.setTitle(prop.getKey().getName());
        charOrCapa.setHref(prop.getKey().getIdentifier());

        TextType textProperty = createText(prop);
        QuantityType quantityProperty = createQuantity(prop);
        CountType countProperty = createCount(prop);
        if (textProperty != null) {
            QName qname = new QName("http://www.opengis.net/swe/2.0", "Text");
            JAXBElement jaxbElement = new JAXBElement(qname, TextType.class, textProperty);
            charOrCapa.setAbstractDataComponent(jaxbElement);
        } else if (quantityProperty != null) {
            QName qname = new QName("http://www.opengis.net/swe/2.0", "Quantity");
            JAXBElement jaxbElement = new JAXBElement(qname, QuantityType.class, quantityProperty);
            charOrCapa.setAbstractDataComponent(jaxbElement);
        } else if (countProperty != null) {
            QName qname = new QName("http://www.opengis.net/swe/2.0", "Count");
            JAXBElement jaxbElement = new JAXBElement(qname, CountType.class, countProperty);
            charOrCapa.setAbstractDataComponent(jaxbElement);
        }

        return charOrCapa;
    }

    /**
     * *
     * Create a physical system. A PhysicalSystem is an aggregate system that
     * can include multiple components, such as a platform or a group of sensors
     * in a single housing or a functional unit.
     *
     * @return
     * @throws JAXBException
     */
    public PhysicalSystemType getPhysicalSystem() throws JAXBException {
        PhysicalSystemType physicalSystem = sml.createPhysicalSystemType();
        decoratePhysicalComponentType(physicalSystem, this.platform.getTerm(), null);
        decoratePlatform(physicalSystem, this.platform);
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
            String deviceUrn = ILinkedDataTerm.getUrnFromUrl(instrument.getTerm().getIdentifier());
            String platformUrn = ILinkedDataTerm.getUrnFromUrl(platform.getTerm().getIdentifier());
            component.setHref(server + "/sml?deviceUrn=" + deviceUrn + "&platformUrn=" + platformUrn);
            componentList.add(component);
        }

        return physicalSystem;
    }

    /**
     * *
     * Create a physical component for a single tool and its associated events.
     * Only provide the events associated to this tool!
     *
     * @param tool
     * @param events
     * @return
     */
    public PhysicalComponentType getPhysicalComponent(ITool tool, Collection<? extends IEvent> events) {
        PhysicalComponentType physicalComponent = sml.createPhysicalComponentType();
        decoratePhysicalComponentType(physicalComponent, tool.getTerm(), events);
        decorateInstrumentOrSensor(physicalComponent, tool, events);
        return physicalComponent;
    }

    /**
     * *
     * Return an sml:Term with a key that has both a label and an url (=a
     * linkeddataterm). The value is a linkeddataterm.
     *
     * @param key
     * @param value
     * @param useTransitive
     * @return
     */
    private TermType getSmlTerm(ILinkedDataTerm key, ILinkedDataTerm value, boolean useTransitive) {
        return getSmlTerm(key, "|" + value.getName() + "|" + (useTransitive ? value.getTransitiveIdentifier() : value.getIdentifier()));
    }

    /**
     * *
     * Return an sml:Term with a key that has both a label and an url (=a
     * linkeddataterm). The value is just a string.
     *
     * @param key
     * @param value
     * @param useTransitive
     * @return
     */
    private TermType getSmlTerm(ILinkedDataTerm key, String value) {
        return getSmlTerm(key.getIdentifier(), value);
    }

    /**
     * *
     * Return an sml:Term with a key that has only a label. The value is a
     * linkeddataterm.
     *
     * @param key
     * @param value
     * @param useTransitive
     * @return
     */
    private TermType getSmlTerm(String key, ILinkedDataTerm value, boolean useTransitive) {
        return getSmlTerm(key, "|" + value.getName() + "|" + (useTransitive ? value.getTransitiveIdentifier() : value.getIdentifier()));
    }

    /**
     * *
     * Return an sml:Term with a value that has only a label
     *
     * @param key
     * @param value
     * @return
     */
    private TermType getSmlTerm(String key, String value) {
        TermType termType = sml.createTermType();
        // termType.setDefinition(null);
        termType.setLabel(key);
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

    /**
     * *
     * Add to the provided classifierList a ILinkedDataTerm key and a
     * ILinkedDataTerm value. Best practice!
     *
     * @param classifierList
     * @param key
     * @param value
     */
    private void addClassifier(ClassifierListType classifierList, ILinkedDataTerm key, ILinkedDataTerm value) {
        Classifier cl1 = sml.createClassifierListTypeClassifier();
        cl1.setTerm(getSmlTerm(key, value, false));
        //TODO should be true cl1.setTerm(getSmlTerm(key, value, true));
        classifierList.getClassifier().add(cl1);
    }

    /**
     * *
     * Add to the provided classifierList a String key and a ILinkedDataTerm
     * value. ONly to be used if the key is not represented in a linked data
     * vocabulary.
     *
     * @param classifierList
     * @param key
     * @param value
     */
    private void addClassifier(ClassifierListType classifierList, String key, ILinkedDataTerm value) {
        Classifier cl1 = sml.createClassifierListTypeClassifier();
        cl1.setTerm(getSmlTerm(key, value, false));
        //TODO should be true cl1.setTerm(getSmlTerm(key, value, true));
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
        id.setTerm(getSmlTerm(getLinkedDataTerm("http://vocab.nerc.ac.uk/collection/W07/current/IDEN0007", "UUID"), e.getIdentifier()));
        identifierList.getRest().add(sml.createIdentifierListTypeIdentifier(id));
        identifierListProperty.setIdentifierList(identifierList);
        event.getIdentification().add(identifierListProperty);

        /*CLASSIFICATION*/
        ClassifierListPropertyType classifierListPropertyType = sml.createClassifierListPropertyType();
        ClassifierListType classifierList = getClassifierList(classifierListPropertyType);

        addClassifier(classifierList, getLinkedDataTerm("http://vocab.nerc.ac.uk/collection/W11/current/ECLS0001", "Event process"), e.getProcess());
        addClassifier(classifierList, getLinkedDataTerm("http://vocab.nerc.ac.uk/collection/W11/current/ECLS0003", "Event action"), e.getAction());
        addClassifier(classifierList, getLinkedDataTerm("http://vocab.nerc.ac.uk/collection/W11/current/ECLS0002", "Event subject"), e.getSubject());

        event.getClassification().add(classifierListPropertyType);

        /*CONTACTS*/
        ContactListPropertyType contactListProperty = sml.createContactListPropertyType();
        ContactListType contactList = sml.createContactListType();
        contactList.getContact().add(createContact(e.getActor(), Role.POINT_OF_CONTACT));
        if (e.getProgram() != null && e.getProgram().getPrincipalInvestigators() != null) {
            for (IPerson principalInvestigator : e.getProgram().getPrincipalInvestigators()) {
                CIResponsiblePartyPropertyType contact = createContact(principalInvestigator, Role.PRINCIPAL_INVESTIGATOR);
                if (contact != null) {
                    contactList.getContact().add(contact);
                }

            }
        }

        contactListProperty.setContactList(contactList);
        event.getContacts().add(contactListProperty);
        /*TIME*/

        Time time = sml.createEventTypeTime();
        TimeInstantType timeInstant = gml.createTimeInstantType();
        timeInstant.setTimePosition(getTimePosition(e.getTimeStamp(), false));
        timeInstant.setId(UUID.randomUUID().toString());
        time.setTimeInstant(timeInstant);
        event.setTime(time);

        /*PROPERTIES*/
        if (e.getProperties() != null && !e.getProperties().isEmpty()) {
            for (IProperty property : e.getProperties()) {
                TextPropertyType textProperty = createTextProperty(property);
                QuantityPropertyType quantityProperty = createQuantityProperty(property);
                CountPropertyType countProperty = createCountProperty(property);
                if (textProperty != null) {
                    event.getProperty().add(textProperty);
                } else if (quantityProperty != null) {
                    event.getProperty().add(quantityProperty);
                } else if (countProperty != null) {
                    event.getProperty().add(countProperty);
                }
            }
        }

        return event;
    }

    private TimePositionType getTimePosition(OffsetDateTime date, boolean openEnded) {
        TimePositionType timePosition = gml.createTimePositionType();
        if (date != null) {
            timePosition.getValue().add(date.format(DateTimeFormatter.ISO_INSTANT));
        } else if (openEnded) {
            timePosition.setIndeterminatePosition(TimeIndeterminateValueType.NOW);
        } else {
            timePosition.setIndeterminatePosition(TimeIndeterminateValueType.UNKNOWN);
        }
        return timePosition;
    }

    private IdentifierListType getIdentifierList(IdentifierListPropertyType identifierListPropertyType) {
        IdentifierListType identifierList = sml.createIdentifierListType();
        identifierListPropertyType.setIdentifierList(identifierList);
        return identifierList;
    }

    /**
     * *
     * Add a LinkedDataTerm as a sml:value, another LinkedDataTerm as a sml:key
     * to an sml:term
     *
     * @param identifierList
     * @param key
     * @param value
     */
    private void addIdentifier(IdentifierListType identifierList, ILinkedDataTerm key, ILinkedDataTerm value) {
        Identifier i = sml.createIdentifierListTypeIdentifier();
        i.setTerm(getSmlTerm(key, value, false));
        JAXBElement<Identifier> jaxbi = sml.createIdentifierListTypeIdentifier(i);
        identifierList.getRest().add(jaxbi);
    }

    /**
     * *
     * Add a String as a sml:value, another LinkedDataTerm as a sml:key to an
     * sml:term
     *
     * @param identifierList
     * @param key
     * @param value
     */
    private void addIdentifier(IdentifierListType identifierList, ILinkedDataTerm key, String value) {
        if (key != null && value != null) {
            Identifier i = sml.createIdentifierListTypeIdentifier();
            i.setTerm(getSmlTerm(key, value));
            JAXBElement<Identifier> jaxbi = sml.createIdentifierListTypeIdentifier(i);
            identifierList.getRest().add(jaxbi);
        }
    }

    private TextPropertyType createTextProperty(IProperty property) {
        String valueString = property.getValue();
        if (Utils.isText(valueString) || (Utils.isDouble(valueString) && property.getUom() == null)) {
            TextPropertyType smlProperty = swe.createTextPropertyType();
            smlProperty.setText(createText(property));
            return smlProperty;
        }
        return null;
    }

    private TextType createText(IProperty property) {
        String valueString = property.getValue();
        if (Utils.isText(valueString) || (Utils.isDouble(valueString) && property.getUom() == null)) {
            TextType smlProperty = swe.createTextType();
            smlProperty.setDefinition(property.getKey().getIdentifier());
            smlProperty.setValue(property.getValue());
            smlProperty.setLabel(property.getKey().getName());
            return smlProperty;
        }
        return null;
    }

    private CountPropertyType createCountProperty(IProperty property) {
        if (Utils.isInteger(property.getValue()) && property.getUom() == null) {
            CountPropertyType smlProperty = swe.createCountPropertyType();
            smlProperty.setCount(createCount(property));
            return smlProperty;
        }
        return null;
    }

    private CountType createCount(IProperty property) {
        if (Utils.isInteger(property.getValue()) && property.getUom() == null) {
            CountType smlProperty = swe.createCountType();
            smlProperty.setDefinition(property.getKey().getIdentifier());
            smlProperty.setValue(new BigInteger(property.getValue()));
            smlProperty.setLabel(property.getKey().getName());
            return smlProperty;
        }
        return null;
    }

    private QuantityPropertyType createQuantityProperty(IProperty property) {
        if ((Utils.isInteger(property.getValue()) || Utils.isDouble(property.getValue())) && property.getUom() != null) {
            QuantityPropertyType smlProperty = swe.createQuantityPropertyType();
            QuantityType value = swe.createQuantityType();
            smlProperty.setQuantity(createQuantity(property));
            return smlProperty;
        }
        return null;
    }

    private QuantityType createQuantity(IProperty property) {
        if ((Utils.isInteger(property.getValue()) || Utils.isDouble(property.getValue())) && property.getUom() != null) {
            QuantityType smlProperty = swe.createQuantityType();
            smlProperty.setDefinition(property.getKey().getIdentifier());
            smlProperty.setValue(new Double(property.getValue()));
            smlProperty.setLabel(property.getKey().getName());
            UnitReference unit = swe.createUnitReference();
            unit.setCode(property.getUom());
            smlProperty.setUom(unit);
            return smlProperty;
        }
        return null;
    }

    private CIResponsiblePartyPropertyType createContact(IPerson person, Role role) {
        if (person != null) {
            CIResponsiblePartyPropertyType respPartyProperty = gmd.createCIResponsiblePartyPropertyType();
            CIResponsiblePartyType respParty = gmd.createCIResponsiblePartyType();

            respPartyProperty.setCIResponsibleParty(respParty);
            respParty.setIndividualName(createString(person.getFirstName() + " " + person.getLastName()));
            if (role != null) {
                respParty.setRole(ROLES.get(role));
            }
            return respPartyProperty;
        }
        return null;
    }

    private CIResponsiblePartyPropertyType createContact(IOrganisation organisation, Role role) {
        if (organisation != null) {
            CIResponsiblePartyPropertyType respPartyProperty = gmd.createCIResponsiblePartyPropertyType();
            CIResponsiblePartyType respParty = gmd.createCIResponsiblePartyType();

            respPartyProperty.setCIResponsibleParty(respParty);
            respParty.setOrganisationName(createString(organisation.getTerm().getName()));
            CIContactPropertyType contactType = gmd.createCIContactPropertyType();
            CIContactType contact = gmd.createCIContactType();
            CIAddressPropertyType addressType = gmd.createCIAddressPropertyType();
            CIAddressType address = gmd.createCIAddressType();
            address.setCity(createString(organisation._getCity()));
            address.setPostalCode(createString(organisation._getPostalcode()));
            // address.set(createString(organisation._getPostalcode()));
            addressType.setCIAddress(address);
            contact.setAddress(addressType);
            contactType.setCIContact(contact);
            respParty.setContactInfo(contactType);
            //respParty.setIndividualName(createString(actor.getFirstName() + " " + actor.getLastName()));
            if (role != null) {
                respParty.setRole(ROLES.get(role));
            }
            return respPartyProperty;
        }
        return null;
    }

    private CharacterStringPropertyType createString(String string) {
        JAXBElement<String> jaxbElement = gco.createCharacterString(string);
        CharacterStringPropertyType c = gco.createCharacterStringPropertyType();
        c.setCharacterString(jaxbElement);
        return c;
    }

    private StringOrRefType createString2(String string) {
        StringOrRefType stringOrRefType = gml.createStringOrRefType();
        stringOrRefType.setValue(string);
        return stringOrRefType;
    }

}
