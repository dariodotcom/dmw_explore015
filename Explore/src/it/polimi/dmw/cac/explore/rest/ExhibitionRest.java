package it.polimi.dmw.cac.explore.rest;

import it.polimi.dmw.cac.explore.controller.ControllerException;
import it.polimi.dmw.cac.explore.controller.ExhibitionController;
import it.polimi.dmw.cac.explore.controller.UserController;
import it.polimi.dmw.cac.explore.request.ExhibitionCreationRequest;
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
public class ExhibitionRest extends RestContainer {

    @Path("create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doCreate(ExhibitionCreationRequest request) {
        ExhibitionController exhibition = ExhibitionController.create(request);
        return ResponseFactory.from(exhibition.getDetails());
    }

    @Path("id/{id}")
    public ExhibitionHandler getById(@PathParam("id") String id) {
        return new ExhibitionHandler(id);
    }

    @Path("search/{term}")
    @GET
    public Response doSearch(@PathParam("term") String term) {
        return null;
    }

    @Produces(MediaType.APPLICATION_JSON)
    public class ExhibitionHandler {

        private String id;

        private ExhibitionHandler(String id) {
            this.id = id;
        }

        @GET
        public Response getExhibitionDetails() {
            try {
                ExhibitionController exhibition = getController();
                return ResponseFactory.from(exhibition.getDetails());
            } catch (ControllerException e) {
                return ResponseFactory.from(e);
            }
        }

        @Path("review")
        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        public Response doReview(ReviewRequest request) {
            try {
                ExhibitionController exhibition = getController();
                exhibition.review(request);
                return ResponseFactory.from(exhibition.getDetails());
            } catch (ControllerException e) {
                return ResponseFactory.from(e);
            }
        }

        @Path("checkin")
        @GET
        public Response doCheckIn() {
            try {
                ExhibitionController exhibition = getController();
                exhibition.checkIn();
                return ResponseFactory.from(exhibition.getDetails());
            } catch (ControllerException e) {
                return ResponseFactory.from(e);
            }
        }
        
        @Path("reviews")
        @GET
        public Response getReviews(){
            try {
                ExhibitionController exhibition = getController();
                return ResponseFactory.from(exhibition.getReviews());
            } catch (ControllerException e) {
                return ResponseFactory.from(e);
            }
        }

        private ExhibitionController getController() throws ControllerException {
            UserController user = UserController.getUser(getSessionUsername());
            return ExhibitionController.getExhibition(user, id);
        }
    }
}