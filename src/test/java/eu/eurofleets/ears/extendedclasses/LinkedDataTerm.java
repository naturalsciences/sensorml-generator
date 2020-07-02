package eu.eurofleets.ears.extendedclasses;

import be.naturalsciences.bmdc.cruise.model.ILinkedDataTerm;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author thomas
 */
public class LinkedDataTerm implements ILinkedDataTerm {

    private String identifier;  //an identifier in an external vocabulary, i.e. the EARS ontology or the BODC Tool list L22 (can only be url)
    private String transitiveIdentifier;  //an identifier in an external vocabulary, i.e. the EARS ontology or the BODC Tool list L22 (can only be url)
    private String name;

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public LinkedDataTerm(String identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    @Override
    public String getTransitiveIdentifier() {
        return transitiveIdentifier != null ? transitiveIdentifier : identifier;
    }

    @Override
    public void setTransitiveIdentifier(String transitiveIdentifier) {
        this.transitiveIdentifier = transitiveIdentifier;
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
}
