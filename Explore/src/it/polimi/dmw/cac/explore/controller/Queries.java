package it.polimi.dmw.cac.explore.controller;

import org.slim3.datastore.Datastore;

import it.polimi.dmw.cac.explore.meta.CheckInMeta;
import it.polimi.dmw.cac.explore.meta.ReviewMeta;
import it.polimi.dmw.cac.explore.meta.UserMeta;
import it.polimi.dmw.cac.explore.model.Exhibition;
import it.polimi.dmw.cac.explore.model.User;

public class Queries {

    public static User getUserByUsername(String username) {
        UserMeta meta = UserMeta.get();
        return Datastore
            .query(meta)
            .filter(meta.username.equal(username))
            .asSingle();
    }

    public static boolean hasUserVisited(User u, Exhibition e) {
        CheckInMeta checkIn = CheckInMeta.get();
        return Datastore
            .query(checkIn)
            .filter(checkIn.authorRef.equal(u.getKey()))
            .filter(checkIn.exhibitionRef.equal(e.getKey()))
            .count() > 0;
    }

    public static boolean hasUserReviewed(User requestor, Exhibition exhibition) {
        ReviewMeta review = ReviewMeta.get();
        return Datastore
            .query(review)
            .filter(review.authorRef.equal(requestor.getKey()))
            .filter(review.exhibitionRef.equal(exhibition.getKey()))
            .count() > 0;
    }
}
