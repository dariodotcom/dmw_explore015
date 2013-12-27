package it.polimi.dmw.cac.explore.rest;

import it.polimi.dmw.cac.explore.controller.ControllerException;
import it.polimi.dmw.cac.explore.details.Details;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ResponseFactory {

    public static Response from(Details details) {
        ResponseContainer c = new ResponseContainer(details);
        return Response.ok(c).build();
    }

    public static Response from(ControllerException exception) {
        return Response
            .status(Status.BAD_REQUEST)
            .entity(new ResponseContainer(exception))
            .build();
    }

    public static Response emptyResult() {
        return Response.ok().build();
    }
}