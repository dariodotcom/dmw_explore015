package it.polimi.dmw.cac.explore.controller;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

import it.polimi.dmw.cac.explore.Utils;
import it.polimi.dmw.cac.explore.controller.builder.AppreciationBuilder;
import it.polimi.dmw.cac.explore.details.ReviewDetails;
import it.polimi.dmw.cac.explore.model.Appreciation;
import it.polimi.dmw.cac.explore.model.Review;
import it.polimi.dmw.cac.explore.model.User;

public class ReviewController {

    public static ReviewController getReview(UserController requestor, String id)
            throws ControllerException {
        Key k = Utils.stringToKey(id);
        Review review = Datastore.get(Review.class, k);
        return new ReviewController(requestor.getEntity(), review);
    }

    private User requestor;
    private Review review;

    private ReviewController(User requestor, Review review) {
        this.requestor = requestor;
        this.review = review;
    }

    public ReviewDetails getDetails() {
        return new ReviewDetails(review, requestor);
    }

    public void appreciate(boolean positive) throws ControllerException {
        ControllerException.Type errorType =
            Coherence.user(requestor).verifyAppreciation(review);

        if (errorType != null) {
            throw new ControllerException(errorType);
        }

        Appreciation appreciation = Queries.getAppreciation(requestor, review);
        if (appreciation == null) {
            AppreciationBuilder
                .create()
                .author(requestor)
                .review(review)
                .positive(positive)
                .store();
        } else {
            AppreciationBuilder
                .get(appreciation.getKey())
                .positive(positive)
                .store();
        }
    }
}
