/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.naturalsciences.bmdc.sensormlgenerator;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import net.opengis.sensorml.AbstractPhysicalProcessType;
import net.opengis.sensorml.PhysicalComponentType;
import net.opengis.sensorml.PhysicalSystemType;

/**
 *
 * @author thomas
 */
public class SensorMLPrinter<S extends AbstractPhysicalProcessType> {

    //private SensorMLBuilder builder;
    private Marshaller marshaller;
    private S system;
    private Class<S> cls;

    public SensorMLPrinter(S system, Class<S> cls) throws JAXBException {
        this.system = system;
        this.cls = cls;
        JAXBContext jaxbContext = JAXBContext.newInstance(cls);
        marshaller = jaxbContext.createMarshaller();
        process();
    }

    private static Map<Class, String> CLASS_TO_XML = new HashMap();

    static {
        CLASS_TO_XML.put(PhysicalSystemType.class, "PhysicalSystem");
        CLASS_TO_XML.put(PhysicalComponentType.class, "PhysicalComponent");
    }

    private void process() throws JAXBException {
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    }

    public void createFile(File file, boolean overwrite) throws JAXBException {
        JAXBElement<S> jaxbElement = new JAXBElement<S>(new QName("sml", CLASS_TO_XML.get(cls)), cls, system);

        marshaller.marshal(jaxbElement, file);
    }

    public String getResult() throws JAXBException {
        StringWriter writer = new StringWriter();

        JAXBElement<S> jaxbElement = new JAXBElement<S>(new QName("sml", CLASS_TO_XML.get(cls)), cls, system);

        marshaller.marshal(jaxbElement, writer);
        String r = writer.toString();
        r = r.replace(" xsi:type=\"swe:QuantityPropertyType\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "");
        r = r.replace(" xsi:type=\"swe:CountPropertyType\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "");
        r = r.replace(" xsi:type=\"swe:TextPropertyType\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "");
        r = r.replaceAll("<sml:value>\\|(.*?)\\|", "<!--$1--><sml:value>");
        return r;
    }

    public void print() throws JAXBException {
        JAXBElement<S> jaxbElement = new JAXBElement<S>(new QName("sml", CLASS_TO_XML.get(cls.getSimpleName())), cls, system);

        marshaller.marshal(jaxbElement, System.out);
    }

}
