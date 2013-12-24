package it.polimi.dmw.cac.explore.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("review")
@Produces(MediaType.APPLICATION_JSON)
public class ReviewRest {

    public static class ReviewHandler {

        private String id;

        private ReviewHandler(String id) {
            this.id = id;
            this.id.charAt(0);
        }

        @GET
        public Response getReviewDetails() {
            return null;
        }

        @Path("appreciate")
        @GET
        public Response doAppreciate(@PathParam("pos") boolean positive) {
            return null;
        }
    }
}
