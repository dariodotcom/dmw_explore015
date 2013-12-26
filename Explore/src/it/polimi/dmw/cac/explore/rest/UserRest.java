package it.polimi.dmw.cac.explore.rest;

import it.polimi.dmw.cac.explore.controller.ControllerException;
import it.polimi.dmw.cac.explore.controller.UserController;
import it.polimi.dmw.cac.explore.details.UserDetails;
import it.polimi.dmw.cac.explore.request.LoginRequest;
import it.polimi.dmw.cac.explore.request.RegistrationRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserRest extends RestContainer {

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doRegister(RegistrationRequest request) {

        try {
            UserController user = UserController.createUser(request);
            return ResponseFactory.from(user.getDetails());
        } catch (ControllerException e) {
            return ResponseFactory.from(e);
        }
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doLogin(LoginRequest loginRequest) {
        try {
            String username = loginRequest.getUsername();
            UserController user = UserController.getUser(username);
            user.authenticate(loginRequest.getPassword());
            UserDetails details = user.getDetails();
            setSessionUsername(details.getUsername());
            return ResponseFactory.from(details);
        } catch (ControllerException e) {
            return ResponseFactory.from(e);
        }
    }

    @GET
    @Path("logout")
    public Response doLogout() {
        setSessionUsername(null);
        return ResponseFactory.emptyResult();
    }

    @GET
    @Path("current")
    public Response getCurrentUser() {
        try {
            String username = getSessionUsername();
            UserController user = UserController.getUser(username);
            return ResponseFactory.from(user.getDetails());
        } catch (ControllerException e) {
            return ResponseFactory.from(e);
        }
    }

    @GET
    @Path("username/{username}")
    public Response getById(@PathParam("username") String username) {
        try {
            UserController user = UserController.getUser(username);
            return ResponseFactory.from(user.getDetails());
        } catch (ControllerException e) {
            return ResponseFactory.from(e);
        }
    }
}