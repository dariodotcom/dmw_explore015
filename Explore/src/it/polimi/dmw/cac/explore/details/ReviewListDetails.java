package it.polimi.dmw.cac.explore.details;

import it.polimi.dmw.cac.explore.model.Review;
import it.polimi.dmw.cac.explore.model.User;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReviewListDetails extends Details {

    private List<ReviewDetails> reviews;

    public ReviewListDetails(List<Review> reviews, User requestor) {
        this.reviews = new ArrayList<ReviewDetails>();
        for (Review r : reviews) {
            this.reviews.add(new ReviewDetails(r, requestor));
        }
    }

    @XmlElement(name = "reviews")
    public List<ReviewDetails> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDetails> reviews) {
        this.reviews = reviews;
    }
}
