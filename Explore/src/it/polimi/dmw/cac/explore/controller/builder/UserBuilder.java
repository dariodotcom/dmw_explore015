package it.polimi.dmw.cac.explore.controller.builder;

import it.polimi.dmw.cac.explore.model.User;
import it.polimi.dmw.cac.explore.request.RegistrationRequest;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

public class UserBuilder {

    public static UserBuilder create(RegistrationRequest request) {
        return new UserBuilder()
            .username(request.getUsername())
            .name(request.getName())
            .surname(request.getSurname())
            .password(request.getPassword());
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

    public User getUser() {
        return user;
    }
}
