package it.polimi.dmw.cac.explore.controller.builder;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

import it.polimi.dmw.cac.explore.model.Tag;

public class TagBuilder {
    
    private Tag tag ;
    
    private TagBuilder(){
        tag = new Tag();
    }
    
    public static TagBuilder create() {
        return new TagBuilder();
    }
    
    public TagBuilder Name(String name){
        tag.setName(name);
        return this;
    }
    public Key store() {
        return Datastore.put(tag);
    }
}
