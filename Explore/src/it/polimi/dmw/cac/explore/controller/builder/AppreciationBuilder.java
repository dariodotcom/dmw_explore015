package it.polimi.dmw.cac.explore.controller.builder;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

import it.polimi.dmw.cac.explore.model.Appreciation;

public class AppreciationBuilder {

    private Appreciation appreciation;

    public static AppreciationBuilder create() {
        return new AppreciationBuilder();
    }

    private AppreciationBuilder() {
        appreciation = new Appreciation();
    }

    public AppreciationBuilder positive(boolean positive) {
        if (positive) {
            appreciation.setValue(+1);
        } else {
            appreciation.setValue(-1);
        }
        return this;
    }

    public Key store() {
        return Datastore.put(appreciation);
    }

}
