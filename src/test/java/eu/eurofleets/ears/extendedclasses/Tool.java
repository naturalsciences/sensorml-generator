package eu.eurofleets.ears.extendedclasses;

import be.naturalsciences.bmdc.cruise.model.IEvent;
import be.naturalsciences.bmdc.cruise.model.ILinkedDataTerm;
import be.naturalsciences.bmdc.cruise.model.IProperty;
import be.naturalsciences.bmdc.cruise.model.ITool;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
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
public class Tool implements ITool {

    private ILinkedDataTerm thisTool;
    private ILinkedDataTerm parentTool;
    private Collection<? extends IEvent> events;

    private OffsetDateTime beginPosition;
    private OffsetDateTime endPosition;
    private String serialNumber;
    private ILinkedDataTerm toolCategory;
    private Collection<Property> characteristics;
    private Collection<Property> capabilities;
    private Collection<LinkedDataTerm> measuredParameters;

    public Tool(ILinkedDataTerm thisTool, ILinkedDataTerm parentTool) {
        this.thisTool = thisTool;
        this.parentTool = parentTool;
    }

    @Override
    public ILinkedDataTerm getTerm() {
        return thisTool;
    }

    @Override
    public void setTerm(ILinkedDataTerm thisTool) {
        this.thisTool = thisTool;
    }

    @Override
    public ILinkedDataTerm getParentTool() {
        return parentTool;
    }

    @Override
    public void setParentTool(ILinkedDataTerm parentTool) {
        this.parentTool = parentTool;
    }

    public ILinkedDataTerm getThisTool() {
        return thisTool;
    }

    public void setThisTool(ILinkedDataTerm thisTool) {
        this.thisTool = thisTool;
    }

    @Override
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public ILinkedDataTerm getToolCategory() {
        return toolCategory;
    }

    public void setToolCategory(ILinkedDataTerm toolCategory) {
        this.toolCategory = toolCategory;
    }

    @Override
    public Collection<Property> getCharacteristics() {
        return this.characteristics;
    }

    @Override
    public Collection<Property> getCapabilities() {
        return this.capabilities;
    }

    @Override
    public Collection<LinkedDataTerm> getMeasuredParameters() {
        return this.measuredParameters;
    }

    @Override
    public void setCharacteristics(Collection<? extends IProperty> characteristics) {
        this.characteristics = (Collection<Property>) characteristics;
    }

    @Override
    public void setCapabilities(Collection<? extends IProperty> capabilities) {
        this.capabilities = (Collection<Property>) capabilities;
    }

    @Override
    public void setMeasuredParameters(Collection<? extends ILinkedDataTerm> measuredParameters) {
        this.measuredParameters = (Collection<LinkedDataTerm>) measuredParameters;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.thisTool);
        hash = 67 * hash + Objects.hashCode(this.parentTool);
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
        final Tool other = (Tool) obj;
        if (!Objects.equals(this.thisTool, other.thisTool)) {
            return false;
        }
        if (!Objects.equals(this.parentTool, other.parentTool)) {
            return false;
        }
        return true;
    }
}
