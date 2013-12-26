package it.polimi.dmw.cac.explore.controller.builder;

import org.slim3.datastore.Datastore;

import it.polimi.dmw.cac.explore.model.CheckIn;
import it.polimi.dmw.cac.explore.model.Exhibition;
import it.polimi.dmw.cac.explore.model.User;

public class ChekInBuilder {
    private CheckIn checkIn ;
    
    public static ChekInBuilder create() {
        return new ChekInBuilder();
    }
    
    private ChekInBuilder() {
        checkIn = new CheckIn() ;
    }
    
    public ChekInBuilder author(User author){
        checkIn.setAuthor(author);
        return this;
    }
    public ChekInBuilder exhibition(Exhibition exb){
        checkIn.setExhibition(exb);
        return this;
    }
    public Key store() {
        return Datastore.put(checkIn);
    }
}
