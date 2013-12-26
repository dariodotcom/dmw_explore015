package it.polimi.dmw.cac.explore.controller.builder;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

import it.polimi.dmw.cac.explore.model.Exhibition;

public class ExhibitionBuilder {

    public static ExhibitionBuilder create() {
        return new ExhibitionBuilder();
    }

    private Exhibition exhibition;

    private ExhibitionBuilder() {
        exhibition = new Exhibition();
    }

    public ExhibitionBuilder name(String name) {
        exhibition.setName(name);
        return this;
    }

    public ExhibitionBuilder description(String description) {
        exhibition.setDescription(description);
        return this;
    }

    public ExhibitionBuilder photoUrl(String photoUrl) {
        exhibition.setPhotoUrl(photoUrl);
        return this;
    }

    public Key store() {
        return Datastore.put(exhibition);
    }
}
