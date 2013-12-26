package it.polimi.dmw.cac.explore.controller.builder;

import org.slim3.datastore.Datastore;

import it.polimi.dmw.cac.explore.model.CheckIn;
import it.polimi.dmw.cac.explore.model.Exhibition;
import it.polimi.dmw.cac.explore.model.User;

public class chekInBuilder {
    private CheckIn checkIn ;
    
    public static chekInBuilder create() {
        return new chekInBuilder();
    }
    
    private chekInBuilder() {
        checkIn = new CheckIn() ;
    }
    
    public chekInBuilder author(User author){
        checkIn.setAuthor(author);
        return this;
    }
    public chekInBuilder exhibition(Exhibition exb){
        checkIn.setExhibition(exb);
        return this;
    }
    public Key store() {
        return Datastore.put(checkIn);
    }
}
