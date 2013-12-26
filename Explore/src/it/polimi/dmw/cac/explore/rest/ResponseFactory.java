package it.polimi.dmw.cac.explore.rest;

import it.polimi.dmw.cac.explore.controller.ControllerException;
import it.polimi.dmw.cac.explore.details.Details;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ResponseFactory {

    public static Response from(Details details) {
        return Response.ok(details).build();
    }

    public static Response from(ControllerException exception) {
        return Response
            .status(Status.INTERNAL_SERVER_ERROR)
            .entity(exception.getMessage())
            .build();
    }

    public static Response emptyResult() {
        return null;
    }

}
