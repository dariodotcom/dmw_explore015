package it.polimi.dmw.cac.explore.controller;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import it.polimi.dmw.cac.explore.controller.ControllerException.Type;
import it.polimi.dmw.cac.explore.controller.builder.CheckInBuilder;
import it.polimi.dmw.cac.explore.controller.builder.ExhibitionBuilder;
import it.polimi.dmw.cac.explore.controller.builder.ReviewBuilder;
import it.polimi.dmw.cac.explore.details.ExhibitionDetails;
import it.polimi.dmw.cac.explore.model.Exhibition;
import it.polimi.dmw.cac.explore.model.User;
import it.polimi.dmw.cac.explore.request.ExhibitionCreationRequest;
import it.polimi.dmw.cac.explore.request.ReviewRequest;

public class ExhibitionController {

    public static ExhibitionController getExhibition(UserController requestor,
            String id) throws ControllerException {

        Key k;

        try {
            k = KeyFactory.stringToKey(id);
        } catch (IllegalStateException e) {
            throw new ControllerException(Type.BAD_REQUEST);
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

    private ExhibitionController(User requestor, Exhibition exhibition) {
        this.requestor = requestor;
        this.exhibition = exhibition;
    }

    public ExhibitionDetails getDetails() {
        return new ExhibitionDetails(exhibition, requestor);
    }

    public void checkIn() throws ControllerException {
        if (requestor == null) {
            throw new ControllerException(Type.LOGIN_REQUIRED);
        }

        if (Queries.hasUserVisited(requestor, exhibition)) {
            throw new ControllerException(Type.DUPLICATE_ENTITY);
        }

        CheckInBuilder
            .create()
            .author(requestor)
            .exhibition(exhibition)
            .store();

        // TODO store tags

        return;
    }

    public Key review(ReviewRequest request) throws ControllerException {
        if (requestor == null) {
            throw new ControllerException(Type.LOGIN_REQUIRED);
        }

        if (Queries.hasUserReviewed(requestor, exhibition)) {
            throw new ControllerException(Type.DUPLICATE_ENTITY);
        }

        if (!request.isValid()) {
            throw new ControllerException(Type.BAD_REQUEST);
        }

        return ReviewBuilder
            .create(request)
            .author(requestor)
            .exhibition(exhibition)
            .store();
    }

}
