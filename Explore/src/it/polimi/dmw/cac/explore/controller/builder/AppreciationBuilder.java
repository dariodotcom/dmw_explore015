package it.polimi.dmw.cac.explore.controller.builder;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

import it.polimi.dmw.cac.explore.model.Appreciation;
import it.polimi.dmw.cac.explore.model.Review;
import it.polimi.dmw.cac.explore.model.User;

public class AppreciationBuilder {

    private Appreciation appreciation;

    public static AppreciationBuilder create() {
        return new AppreciationBuilder();
    }

    private AppreciationBuilder() {
        appreciation = new Appreciation();
    }

    public AppreciationBuilder(Appreciation appreciation) {
        this.appreciation = appreciation;
    }

    public AppreciationBuilder positive(boolean positive) {
        if (positive) {
            appreciation.setValue(+1);
        } else {
            appreciation.setValue(-1);
        }
        return this;
    }

    public AppreciationBuilder author(User author) {
        appreciation.setAuthor(author);
        return this;
    }

    public AppreciationBuilder review(Review review) {
        appreciation.setReview(review);
        return this;
    }

    public Key store() {
        return Datastore.put(appreciation);
    }

    public static AppreciationBuilder get(Key key) {
        Appreciation appreciation = Datastore.get(Appreciation.class, key);
        return new AppreciationBuilder(appreciation);
    }

}
