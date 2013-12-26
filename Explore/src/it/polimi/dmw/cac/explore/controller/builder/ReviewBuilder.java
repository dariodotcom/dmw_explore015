package it.polimi.dmw.cac.explore.controller.builder;

import java.util.Date;

import it.polimi.dmw.cac.explore.model.Exhibition;
import it.polimi.dmw.cac.explore.model.Review;
import it.polimi.dmw.cac.explore.model.User;
import it.polimi.dmw.cac.explore.request.ReviewRequest;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

public class ReviewBuilder {

    private Review review;

    public static ReviewBuilder create(ReviewRequest request) {
        return new ReviewBuilder().text(request.getText()).grade(
            request.getGrade());
    }

    private ReviewBuilder() {
        review = new Review();
    }

    public ReviewBuilder author(User author) {
        review.setAuthor(author);
        return this;
    }

    public ReviewBuilder exhibition(Exhibition exhibition) {
        review.setExhibition(exhibition);
        return this;
    }

    public ReviewBuilder text(String text) {
        review.setText(text);
        return this;
    }

    public ReviewBuilder grade(int grade) {
        review.setGrade(grade);
        return this;
    }

    public ReviewBuilder date(Date date) {
        review.setDate(date);
        return this;
    }

    public Key store() {
        return Datastore.put(review);
    }
}
