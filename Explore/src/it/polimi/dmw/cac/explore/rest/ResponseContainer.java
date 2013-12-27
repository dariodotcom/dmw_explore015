package it.polimi.dmw.cac.explore.rest;

import it.polimi.dmw.cac.explore.controller.ControllerException;
import it.polimi.dmw.cac.explore.details.Details;
import it.polimi.dmw.cac.explore.details.ErrorDetails;

import javax.xml.bind.annotation.XmlElement;

public class ResponseContainer {
    private String type;
    private Details details;

    public ResponseContainer() {

    }

    public ResponseContainer(ControllerException e) {
        this.details = new ErrorDetails(e);
        this.type = "ERROR";
    }

    public ResponseContainer(Details details) {
        this.details = details;
        this.type = "RESULT";
    }

    @XmlElement(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement(name = "details")
    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }
}