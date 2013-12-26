package it.polimi.dmw.cac.explore.controller;

import it.polimi.dmw.cac.explore.details.UserDetails;
import it.polimi.dmw.cac.explore.request.RegistrationRequest;

public class UserController {

    public static UserController createUser(RegistrationRequest request)
            throws ControllerException {
        return null;
    }

    public static UserController getUser(String id) throws ControllerException {
        return null;
    }

    public void authenticate(String password) throws ControllerException {
        return;
    }

    public UserDetails getDetails() {
        return null;
    }

}
