package it.polimi.dmw.cac.explore.controller;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import it.polimi.dmw.cac.explore.controller.ControllerException.Type;
import it.polimi.dmw.cac.explore.details.ExhibitionDetails;
import it.polimi.dmw.cac.explore.model.Exhibition;
import it.polimi.dmw.cac.explore.model.User;
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

        return new ExhibitionController(requestor.getEntity(), exhibition);
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
        if(requestor == null){
            
        }
        
        
        return;
    }

    public ReviewController review(ReviewRequest request)
            throws ControllerException {
        return null;
    }
}
