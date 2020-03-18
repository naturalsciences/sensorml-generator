/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.naturalsciences.bmdc.sensormlgenerator;

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
import java.util.List;
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
     * Test of getBuilder method, of class SensorMLPrinter.
     */
    @Test
    @Ignore
    public void testGetBuilder() {
        System.out.println("getBuilder");
        SensorMLPrinter instance = null;
        SensorMLBuilder expResult = null;
        SensorMLBuilder result = instance.getBuilder();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
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
    public void testGetResult() throws Exception {
        System.out.println("getResult");
        SensorMLBuilder builder = new SensorMLBuilder(generateANiceTestCruise());
        SensorMLPrinter instance = new SensorMLPrinter(builder);
        String result = instance.getResult();
        assertTrue(result.contains("<sml:history>"));

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

    public Cruise generateANiceTestCruise() {
        Cruise c = new Cruise();
        Country belgium = new Country(new LinkedDataTerm("C32:BE", "Belgium"));
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

        LinkedDataTerm odnaturet = new LinkedDataTerm("SDN:EDMO::3330", "Royal Belgian Institute of Natural Sciences, Operational Directorate Natural Environment");
        Organisation odnature = new Organisation(odnaturet, "02/555.555", "02/555.556", "info@odnature.be", "http://odnature.naturalsciences.be", "Vautierstraat 1", "Brussel", "1000", belgium);

        LinkedDataTerm sumot = new LinkedDataTerm("SDN:EDMO::3330", "Royal Belgian Institute of Natural Sciences, Operational Directorate Natural Environment, SUMO");
        Organisation sumo = new Organisation(sumot, "02/555.551", "02/555.552", "info@sumo.be", "http://odnature.naturalsciences.be", "Vautierstraat 1", "Brussel", "1000", belgium);

        List<Person> chiefScientists = Arrays.asList(new Person[]{new Person("Michael", "Fettweis", sumo, null, null, null)});
        c.setChiefScientists(chiefScientists);
        Platform p = new Platform(new LinkedDataTerm("SDN:C17::11BE", "Belgica"), new LinkedDataTerm("SDN:L06::31", "research vessel"), odnature);
        c.setPlatform(p);
        LinkedDataTerm bmdct = new LinkedDataTerm("SDN:EDMO::1778", "Belgian Marine Data Centre");
        Organisation bmdc = new Organisation(bmdct, "02/555666", "02/555662", "info@bmdc.be", "www.bmdc.be", "Vautierstraat 1", "Brussel", "1000", belgium);
        c.setCollateCentre(bmdc);

        Project prj = new Project(new LinkedDataTerm("SDN:EDMERP::11436", "An ecosystem approach in sustainable fisheries management through local ecological knowledge {acronym=&quot;LECOFISH&quot; organisation=&quot;Ghent University, Maritime Institute / Dpt. International Public Law&quot; country=&quot;Belgium&quot;}"));
        List<Project> projects = Arrays.asList(new Project[]{prj});
        Program pr = new Program("MOMO", cruises, chiefScientists, "The MOMO programme is active since 2012 and investigates...", projects);
        List<Program> programmes = Arrays.asList(new Program[]{pr});
        c.setPrograms(programmes);

        List<Event> events = new ArrayList<>();
        Event event = new SamplingEvent();
        event.setToolCategory(new LinkedDataTerm("http://vocab.nerc.ac.uk/collection/L05/current/50/", "sediment grabs"));
        event.setTool(new Tool(new LinkedDataTerm("http://vocab.nerc.ac.uk/collection/L22/current/TOOL0653/", "Van Veen grab"), null));
        event.setProcess(new LinkedDataTerm("http://ontologies.ef-ears.eu/ears2/1#pro_1", "Sampling"));
        event.setAction(new LinkedDataTerm("http://ontologies.ef-ears.eu/ears2/1#act_2", "End"));
        event.setSubject(new LinkedDataTerm("http://vocab.nerc.ac.uk/collection/C77/current/G71/", "In-situ seafloor measurement/sampling"));

        List<Property> properties = new ArrayList<>();
        properties.add(new Property(new LinkedDataTerm("http://ontologies.ef-ears.eu/ears2/1/11BE#pry_21", "depth_m"), "3"));
        properties.add(new Property(new LinkedDataTerm("http://ontologies.ef-ears.eu/ears2/1#pry_4", "label"), "W04"));
        properties.add(new Property(new LinkedDataTerm("http://ontologies.ef-ears.eu/ears2/1#pry_16", "sampleId"), "20200506_12"));
        event.setProperties(properties);
        events.add(event);
        c.setEvents(events);
        return c;
    }

}
