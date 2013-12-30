package it.polimi.dmw.cac.explore.controller;

import it.polimi.dmw.cac.explore.controller.ControllerException.Type;
import it.polimi.dmw.cac.explore.model.Exhibition;
import it.polimi.dmw.cac.explore.model.Review;
import it.polimi.dmw.cac.explore.model.User;

public class Coherence {

    public static Coherence user(User user) {
        return new Coherence(user);
    }

    private User user;

    private Coherence(User user) {
        this.user = user;
    }

    public ControllerException.Type verifyCheckIn(Exhibition exhibition) {
        if(user == null){
            return Type.LOGIN_REQUIRED;
        }
        
        if(Queries.hasUserVisited(user, exhibition)){
            return Type.DUPLICATE_ENTITY;
        }
        
        return null;
    }

    public ControllerException.Type verifyReview(Exhibition exhibition) {
        if(user == null){
            return Type.LOGIN_REQUIRED;
        }
        
        if(!Queries.hasUserVisited(user, exhibition)){
            return Type.CHECKIN_REQUIRED;
        }

        if(Queries.hasUserReviewed(user, exhibition)){
            return Type.DUPLICATE_ENTITY;
        }
        
        return null;
    }
    
    public ControllerException.Type verifyAppreciation(Review review){
        if(user == null){
            return Type.LOGIN_REQUIRED;
        }
        
        if(review.getAuthor().equals(user)){
            return Type.SELF_APPRECIATION;
        }
        
        return null;
    }
}
