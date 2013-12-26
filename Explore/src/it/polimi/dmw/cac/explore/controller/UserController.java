package it.polimi.dmw.cac.explore.controller;

import it.polimi.dmw.cac.explore.controller.ControllerException.Type;
import it.polimi.dmw.cac.explore.controller.builder.UserBuilder;
import it.polimi.dmw.cac.explore.details.UserDetails;
import it.polimi.dmw.cac.explore.model.User;
import it.polimi.dmw.cac.explore.request.RegistrationRequest;

public class UserController {

    public static UserController createUser(RegistrationRequest request)
            throws ControllerException {

        if (!request.isComplete()) {
            throw new ControllerException(Type.MISSING_PARAMETER);
        }

        if (Queries.getUserByUsername(request.getUsername()) != null) {
            throw new ControllerException(Type.DUPLICATE_ENTITY);
        }

        UserBuilder builder = UserBuilder.create(request);
        User user = builder.getUser();
        builder.store();

        return new UserController(user);
    }

    public static UserController getUser(String username)
            throws ControllerException {
        User user = Queries.getUserByUsername(username);
        if (user == null) {
            throw new ControllerException(Type.MISSING_ENTITY);
        }
        return new UserController(user);
    }

    private User entity;

    private UserController(User user) {
        this.entity = user;
    }

    public void authenticate(String password) throws ControllerException {
        if (!entity.isPasswordMatching(password)) {
            throw new ControllerException(Type.WRONG_PASSWORD);
        }
    }

    public UserDetails getDetails() {
        return new UserDetails(entity);
    }
}
