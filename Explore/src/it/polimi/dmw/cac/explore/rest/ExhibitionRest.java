package it.polimi.dmw.cac.explore.rest;

import it.polimi.dmw.cac.explore.request.ReviewRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("exhibition")
@Produces(MediaType.APPLICATION_JSON)
public class ExhibitionRest {

    @Path("id/{id}")
    public ExhibitionHandler getById(@PathParam("id") String id) {
        return new ExhibitionHandler(id);
    }

    @Path("search/{term}")
    @GET
    public Response doSearch(@PathParam("term") String term) {
        return null;
    }

    public static class ExhibitionHandler {

        private String id;

        private ExhibitionHandler(String id) {
            this.id = id;
            this.id.toCharArray();
        }

        @GET
        public Response getExhibitionDetails() {
            return null;
        }

        @Path("review")
        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        public Response doReview(ReviewRequest request) {
            return null;
        }

        @Path("checkin")
        @GET
        public Response doCheckIn() {
            return null;
        }
    }
}