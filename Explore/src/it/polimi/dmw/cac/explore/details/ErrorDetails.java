package it.polimi.dmw.cac.explore.details;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import it.polimi.dmw.cac.explore.controller.ControllerException;

@XmlRootElement
public class ErrorDetails extends Details{

    public String type;

    public ErrorDetails(ControllerException e){
        this.type = e.getType().name();
    }

    @XmlElement(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
