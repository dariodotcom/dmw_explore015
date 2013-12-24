package it.polimi.dmw.cac.explore.rest;

import it.polimi.dmw.cac.explore.rest.post.LoginRequest;
import it.polimi.dmw.cac.explore.rest.post.RegistrationRequest;

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
public class UserRest {

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doRegister(RegistrationRequest request) {
        return null;
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doLogin(LoginRequest loginRequest) {
        return null;
    }

    @GET
    @Path("logout")
    public Response doLogout() {
        return null;
    }

    @GET
    @Path("current")
    public Response getCurrentUser() {
        return null;
    }

    @GET
    @Path("id/{id}")
    public Response getById(@PathParam("id") String id) {
        return null;
    }

}