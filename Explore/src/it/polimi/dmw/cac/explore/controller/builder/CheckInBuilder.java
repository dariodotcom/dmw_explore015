package it.polimi.dmw.cac.explore.controller.builder;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

import it.polimi.dmw.cac.explore.model.CheckIn;
import it.polimi.dmw.cac.explore.model.Exhibition;
import it.polimi.dmw.cac.explore.model.User;

public class CheckInBuilder {
    private CheckIn checkIn ;
    
    public static CheckInBuilder create() {
        return new CheckInBuilder();
    }
    
    private CheckInBuilder() {
        checkIn = new CheckIn() ;
    }
    
    public CheckInBuilder author(User author){
        checkIn.setAuthor(author);
        return this;
    }
    public CheckInBuilder exhibition(Exhibition exb){
        checkIn.setExhibition(exb);
        return this;
    }
    public Key store() {
        return Datastore.put(checkIn);
    }
}
