package it.polimi.dmw.cac.explore.rest;

import it.polimi.dmw.cac.explore.controller.ControllerException;
import it.polimi.dmw.cac.explore.controller.ReviewController;
import it.polimi.dmw.cac.explore.controller.UserController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("review")
@Produces(MediaType.APPLICATION_JSON)
public class ReviewRest extends RestContainer {

    @Path("id/{id}")
    public ReviewHandler getById(@PathParam("id") String id) {
        return new ReviewHandler(id);
    }

    public class ReviewHandler {

        private static final String APPRECIATION_POSITIVE = "up";
        private String id;

        private ReviewHandler(String id) {
            this.id = id;
        }

        @GET
        public Response getReviewDetails() {
            try {
                ReviewController review = getController();
                return ResponseFactory.from(review.getDetails());
            } catch (ControllerException e) {
                return ResponseFactory.from(e);
            }
        }

        @Path("appreciate/{type}")
        @GET
        public Response doAppreciate(@PathParam("pos") String type) {
            try {
                ReviewController review = getController();
                review.appreciate(type.equals(APPRECIATION_POSITIVE));
                return ResponseFactory.from(review.getDetails());
            } catch (ControllerException e) {
                return ResponseFactory.from(e);
            }
        }

        private ReviewController getController() throws ControllerException {
            UserController user = UserController.getUser(getSessionUsername());
            return ReviewController.getReview(user, id);
        }
    }
}
