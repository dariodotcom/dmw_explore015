package it.polimi.dmw.cac.explore.controller;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import it.polimi.dmw.cac.explore.controller.ControllerException.Type;
import it.polimi.dmw.cac.explore.controller.builder.CheckInBuilder;
import it.polimi.dmw.cac.explore.controller.builder.ExhibitionBuilder;
import it.polimi.dmw.cac.explore.controller.builder.ReviewBuilder;
import it.polimi.dmw.cac.explore.controller.builder.TaggingBuilder;
import it.polimi.dmw.cac.explore.details.ExhibitionDetails;
import it.polimi.dmw.cac.explore.details.ReviewListDetails;
import it.polimi.dmw.cac.explore.details.SearchResultsDetails;
import it.polimi.dmw.cac.explore.model.Exhibition;
import it.polimi.dmw.cac.explore.model.User;
import it.polimi.dmw.cac.explore.request.ExhibitionCreationRequest;
import it.polimi.dmw.cac.explore.request.ReviewRequest;

public class ExhibitionController {

    public static SearchResultsDetails search(String term) {
        List<Exhibition> results = Queries.getExhibitionByName(term);
        return new SearchResultsDetails(results);
    }

    public static SearchResultsDetails top() {
        List<Exhibition> results = Queries.getTopExhibitions();
        return new SearchResultsDetails(results);
    }

    public static ExhibitionController getExhibition(UserController requestor,
            String id) throws ControllerException {

        Key k;

        try {
            k = KeyFactory.stringToKey(id);
        } catch (IllegalStateException e) {
            throw new ControllerException(Type.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            throw new ControllerException(Type.MISSING_ENTITY);
        }

        Exhibition exhibition = Datastore.get(Exhibition.class, k);
        User user = requestor == null ? null : requestor.getEntity();

        return new ExhibitionController(user, exhibition);
    }

    public static ExhibitionController create(ExhibitionCreationRequest request) {
        Key k = ExhibitionBuilder.create(request).store();
        Exhibition ex = Datastore.get(Exhibition.class, k);
        return new ExhibitionController(null, ex);
    }

    private User requestor;
    private Exhibition exhibition;
    private Coherence userCoherence;

    private ExhibitionController(User requestor, Exhibition exhibition) {
        this.requestor = requestor;
        this.exhibition = exhibition;
        this.userCoherence = Coherence.user(requestor);
    }

    public ExhibitionDetails getDetails() {
        return ExhibitionDetails.fullDetail(exhibition, requestor);
    }

    public void checkIn() throws ControllerException {
        ControllerException.Type errorType =
            userCoherence.verifyCheckIn(exhibition);
        if (errorType != null) {
            throw new ControllerException(errorType);
        }

        CheckInBuilder
            .create()
            .author(requestor)
            .exhibition(exhibition)
            .store();

        return;
    }

    public Key review(ReviewRequest request) throws ControllerException {
        ControllerException.Type errorType =
            userCoherence.verifyReview(exhibition);
        if (errorType != null) {
            throw new ControllerException(errorType);
        }

        if (!request.isValid()) {
            throw new ControllerException(Type.BAD_REQUEST);
        }

        for (String tag : request.getTags()) {
            TaggingBuilder.create().tag(tag, exhibition).store();
        }

        return ReviewBuilder
            .create(request)
            .author(requestor)
            .exhibition(exhibition)
            .store();
    }

    public ReviewListDetails getReviews() {
        return new ReviewListDetails(exhibition.getReviews(), requestor);
    }
}