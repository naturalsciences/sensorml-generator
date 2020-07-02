/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.naturalsciences.bmdc.sensormlgenerator;

import be.naturalsciences.bmdc.cruise.model.IEvent;
import be.naturalsciences.bmdc.cruise.model.IPerson;
import be.naturalsciences.bmdc.cruise.model.ITool;
import static be.naturalsciences.bmdc.sensormlgenerator.SensorMLBuilder.upsertMapOfList;
import eu.eurofleets.ears.extendedclasses.Country;
import eu.eurofleets.ears.extendedclasses.Cruise;
import eu.eurofleets.ears.extendedclasses.Event;
import eu.eurofleets.ears.extendedclasses.Harbour;
import eu.eurofleets.ears.extendedclasses.LinkedDataTerm;
import eu.eurofleets.ears.extendedclasses.Organisation;
import eu.eurofleets.ears.extendedclasses.Person;
import eu.eurofleets.ears.extendedclasses.Platform;
import eu.eurofleets.ears.extendedclasses.Program;
import eu.eurofleets.ears.extendedclasses.Project;
import eu.eurofleets.ears.extendedclasses.Property;
import eu.eurofleets.ears.extendedclasses.SamplingEvent;
import eu.eurofleets.ears.extendedclasses.SeaArea;
import eu.eurofleets.ears.extendedclasses.Tool;
import java.io.File;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.opengis.sensorml.PhysicalComponentType;
import net.opengis.sensorml.PhysicalSystemType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author thomas
 */
public class SensorMLPrinterTest {
    
    public SensorMLPrinterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createFile method, of class SensorMLPrinter.
     */
    @Test
    @Ignore
    public void testCreateFile() throws Exception {
        System.out.println("createFile");
        File file = null;
        boolean overwrite = false;
        SensorMLPrinter instance = null;
        instance.createFile(file, overwrite);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getResult method, of class SensorMLPrinter.
     */
    @Test
    public void testGetPhysicalSystemResult() throws Exception {
        System.out.println("getPhysicalSystem");
        Platform p = generateANiceTestPlatform();
        SensorMLBuilder builder = new SensorMLBuilder(p, "https://ears.bmdc.be/ears2");
        PhysicalSystemType physicalSystem = builder.getPhysicalSystem();
        SensorMLPrinter instance = new SensorMLPrinter(physicalSystem, physicalSystem.getClass());
        String result = instance.getResult();
        System.out.println(result);
        assertTrue(result.contains("<PhysicalSystem xmlns"));
        assertTrue(result.contains("<sml:component"));
        assertTrue(result.contains("Van Veen grab"));
    }

    /**
     * Test of getResult method, of class SensorMLPrinter.
     */
    @Test
    public void testGetPhysicalComponentResult() throws Exception {
        System.out.println("getPhysicalComponent");
        Platform p = generateANiceTestPlatform();
        SensorMLBuilder builder = new SensorMLBuilder(p, "https://ears.bmdc.be/ears2");
        
        Map<ITool, List<IEvent>> toolEvent = new HashMap<>();
        for (IEvent event : p.getEvents()) {
            upsertMapOfList(toolEvent, event.getTool(), event);
        }
        ITool tool = toolEvent.keySet().iterator().next();
        List<IEvent> events = toolEvent.get(tool);
        PhysicalComponentType physicalSystem = builder.getPhysicalComponent(tool, events);
        SensorMLPrinter instance = new SensorMLPrinter(physicalSystem, physicalSystem.getClass());
        String result = instance.getResult();
        System.out.println(result);
        assertTrue(result.contains("<sml:history>"));
        assertTrue(result.contains("<sml:value>e3c8df0d-02e9-446d-a59b-224a14b89f9a</sml:value>"));
        assertTrue(result.contains("<swe:label>Van Veen grab Sampling End at 2019-05-06T16:44:18Z</swe:label>"));
        assertTrue(result.contains("<gml:timePosition>2019-05-06T16:44:18Z</gml:timePosition>"));
        assertTrue(result.contains("swe:Text definition=\"http://ontologies.ef-ears.eu/ears2/1#pry_4"));
        assertTrue(result.contains("swe:Count definition=\"http://ontologies.orr.org/fish_count"));
        assertTrue(result.contains("swe:Quantity definition=\"http://ontologies.ef-ears.eu/ears2/1/11BE#pry_21")); //depth
    }

    /**
     * Test of print method, of class SensorMLPrinter.
     */
    @Test
    @Ignore
    public void testPrint() throws Exception {
        System.out.println("print");
        SensorMLPrinter instance = null;
        instance.print();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    public Platform generateANiceTestPlatform() {
        Country belgium = new Country(new LinkedDataTerm("C32:BE", "Belgium"));
        LinkedDataTerm odnaturet = new LinkedDataTerm("SDN:EDMO::3330", "Royal Belgian Institute of Natural Sciences, Operational Directorate Natural Environment");
        Organisation odnature = new Organisation(odnaturet, "02/555.555", "02/555.556", "info@odnature.be", "http://odnature.naturalsciences.be", "Vautierstraat 1", "Brussel", "1000", belgium);
        Platform p = new Platform(new LinkedDataTerm("SDN:C17::11BE", "Belgica"), new LinkedDataTerm("SDN:L06::31", "research vessel"), odnature);
        Cruise c = new Cruise();
        
        c.setPlatform(p);
        
        Harbour h = new Harbour(new LinkedDataTerm("SDN:C38::BSH4510", "Zeebrugge"), belgium);
        
        c.setArrivalHarbour(h);
        c.setDepartureHarbour(h);
        c.setIdentifier("BE11/2007_18");
        c.setName("BE11/2007_18");
        c.setStartDate(OffsetDateTime.of(2017, 11, 8, 0, 0, 0, 0, ZoneOffset.UTC));
        c.setEndDate(OffsetDateTime.of(2017, 11, 15, 0, 0, 0, 0, ZoneOffset.UTC));
        c.setObjectives("The objectives of the cruise are to validate the modeling efforts of the last 2 years using the COHERENS model. Rubber ducks will be released.");
        
        List<SeaArea> seaAreas = Arrays.asList(new SeaArea[]{new SeaArea(new LinkedDataTerm("SDN:C19::1_2", "North Sea"))});
        
        List<Cruise> cruises = Arrays.asList(new Cruise[]{c});
        c.setSeaAreas(seaAreas);
        
        LinkedDataTerm sumot = new LinkedDataTerm("SDN:EDMO::3330", "Royal Belgian Institute of Natural Sciences, Operational Directorate Natural Environment, SUMO");
        Organisation sumo = new Organisation(sumot, "02/555.551", "02/555.552", "info@sumo.be", "http://odnature.naturalsciences.be", "Vautierstraat 1", "Brussel", "1000", belgium);
        
        List<Person> chiefScientists = Arrays.asList(new Person[]{new Person("Michael", "Fettweis", sumo, null, null, null)});
        c.setChiefScientists(chiefScientists);
        
        LinkedDataTerm bmdct = new LinkedDataTerm("SDN:EDMO::1778", "Belgian Marine Data Centre");
        Organisation bmdc = new Organisation(bmdct, "02/555666", "02/555662", "info@bmdc.be", "www.bmdc.be", "Vautierstraat 1", "Brussel", "1000", belgium);
        c.setCollateCentre(bmdc);
        
        Project prj = new Project(new LinkedDataTerm("SDN:EDMERP::11436", "An ecosystem approach in sustainable fisheries management through local ecological knowledge {acronym=&quot;LECOFISH&quot; organisation=&quot;Ghent University, Maritime Institute / Dpt. International Public Law&quot; country=&quot;Belgium&quot;}"));
        List<Project> projects = Arrays.asList(new Project[]{prj});
        Program pr = new Program("MOMO", cruises, chiefScientists, "The MOMO programme is active since 2012 and investigates...", projects);
        
        List<Program> programmes = Arrays.asList(new Program[]{pr});
        c.setPrograms(programmes);
        
        Person joan = new Person("Joan", "Backers", null, null, null, null);
        
        List<Event> events = new ArrayList<>();
        Event event = new SamplingEvent();
        event.setIdentifier("e3c8df0d-02e9-446d-a59b-224a14b89f9a");
        event.setTimeStamp(OffsetDateTime.parse("2019-05-06T16:44:18Z"));
        event.setToolCategory(new LinkedDataTerm("http://vocab.nerc.ac.uk/collection/L05/current/50/", "sediment grabs"));
        event.setTool(new Tool(new LinkedDataTerm("http://vocab.nerc.ac.uk/collection/L22/current/TOOL0653/", "Van Veen grab"), null));
        event.setProcess(new LinkedDataTerm("http://ontologies.ef-ears.eu/ears2/1#pro_1", "Sampling"));
        event.setAction(new LinkedDataTerm("http://ontologies.ef-ears.eu/ears2/1#act_2", "End"));
        event.setSubject(new LinkedDataTerm("http://vocab.nerc.ac.uk/collection/C77/current/G71/", "In-situ seafloor measurement/sampling"));
        event.setActor(joan);
        event.setProgram(pr);
        List<Property> properties = new ArrayList<>();
        properties.add(new Property(new LinkedDataTerm("http://ontologies.orr.org/fish_count", "fish_count"), "89", null));
        properties.add(new Property(new LinkedDataTerm("http://ontologies.ef-ears.eu/ears2/1/11BE#pry_21", "depth_m"), "3", "m"));
        properties.add(new Property(new LinkedDataTerm("http://ontologies.ef-ears.eu/ears2/1#pry_4", "label"), "W04", null));
        properties.add(new Property(new LinkedDataTerm("http://ontologies.ef-ears.eu/ears2/1#pry_16", "sampleId"), "20190506_12", null));
        event.setProperties(properties);
        events.add(event);
        c.setEvents(events);
        return p;
    }
    
}
