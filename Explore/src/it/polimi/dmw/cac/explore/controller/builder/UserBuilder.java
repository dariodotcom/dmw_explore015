package it.polimi.dmw.cac.explore.controller.builder;

import it.polimi.dmw.cac.explore.model.User;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

public class UserBuilder {

    public static UserBuilder create() {
        return new UserBuilder();
    }

    private User user;

    private UserBuilder() {
        this.user = new User();
    }

    private UserBuilder(User user) {
        this.user = user;
    }

    public UserBuilder username(String username) {
        user.setUsername(username);
        return this;
    }

    public UserBuilder name(String name) {
        user.setName(name);
        return this;
    }

    public UserBuilder surname(String surname) {
        user.setSurname(surname);
        return this;
    }

    public UserBuilder password(String password) {
        user.setPassword(password);
        return this;
    }

    public Key store() {
        return Datastore.put(user);
    }
}
