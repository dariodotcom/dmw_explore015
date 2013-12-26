package it.polimi.dmw.cac.explore.controller;

import it.polimi.dmw.cac.explore.details.ExhibitionDetails;
import it.polimi.dmw.cac.explore.request.ReviewRequest;

public class ExhibitionController {

    public static ExhibitionController getExhibition(UserController requestor,
            String id) throws ControllerException {
        return null;
    }

    public ExhibitionDetails getDetails() {
        return null;
    }

    public void checkIn() throws ControllerException {
        return;
    }

    public ReviewController review(ReviewRequest request)
            throws ControllerException {
        return null;
    }
}
