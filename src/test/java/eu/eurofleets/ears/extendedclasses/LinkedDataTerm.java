package eu.eurofleets.ears.extendedclasses;

import be.naturalsciences.bmdc.cruise.model.ILinkedDataTerm;
import java.util.Objects;

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
    private String urn;
    private String transitiveUrn;

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

    /* public LinkedDataTerm(String identifier, String name, String urn) {
        this.identifier = identifier;
        this.name = name;
        this.urn = urn;
    }*/
    public LinkedDataTerm(String identifier, String transitiveIdentifier, String name) {
        this.identifier = ILinkedDataTerm.cleanUrl(identifier);
        this.name = name;
        this.transitiveIdentifier = transitiveIdentifier;
        this.urn = ILinkedDataTerm.getUrnFromUrl(identifier);
        this.transitiveUrn = ILinkedDataTerm.getUrnFromUrl(transitiveIdentifier);
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
        return urn;
    }

    @Override
    public void setUrn(String urn) {
        this.urn = urn;
    }

    @Override
    public String getTransitiveUrn() {
        return transitiveUrn;
    }

    @Override
    public void setTransitiveUrn(String transitiveUrn) {
        this.transitiveUrn = transitiveUrn;
    }

    @Override
    public ILinkedDataTerm getTerm() {
        return this;
    }

    @Override
    public void setTerm(ILinkedDataTerm term) {

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.identifier);
        hash = 97 * hash + Objects.hashCode(this.transitiveIdentifier);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LinkedDataTerm other = (LinkedDataTerm) obj;
        if (!Objects.equals(this.identifier, other.identifier)) {
            return false;
        }
        if (!Objects.equals(this.transitiveIdentifier, other.transitiveIdentifier)) {
            return false;
        }
        return true;
    }

}
