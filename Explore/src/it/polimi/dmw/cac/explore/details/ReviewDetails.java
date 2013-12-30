package it.polimi.dmw.cac.explore.details;

import it.polimi.dmw.cac.explore.controller.Coherence;
import it.polimi.dmw.cac.explore.controller.Queries;
import it.polimi.dmw.cac.explore.model.Appreciation;
import it.polimi.dmw.cac.explore.model.Review;
import it.polimi.dmw.cac.explore.model.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.appengine.api.datastore.KeyFactory;

@XmlRootElement
public class ReviewDetails extends Details {

    private String id;
    private String authorUsername;
    private String text;
    private int grade;
    private int positiveAppreciations;
    private int negativeAppreciations;
    private String appreciation;
    private boolean appreciable;

    public ReviewDetails(Review review, User requestor) {
        setId(KeyFactory.keyToString(review.getKey()));
        authorUsername = review.getAuthor().getUsername();
        text = review.getText();
        grade = review.getGrade();
        positiveAppreciations = 0;
        negativeAppreciations = 0;
        appreciable =
            Coherence.user(requestor).verifyAppreciation(review) == null;

        for (Appreciation a : review.getApprectiations()) {
            if (a.getValue() > 0) {
                positiveAppreciations++;
            } else if (a.getValue() < 0) {
                negativeAppreciations++;
            }
        }

        if (requestor == null) {
            appreciation = null;
        } else {

            Appreciation appr = Queries.getAppreciation(requestor, review);
            appreciation =
                appr == null ? null : appr.getValue() > 0
                    ? "POSITIVE"
                    : "NEGATIVE";
        }
    }

    @XmlElement(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "author")
    public String getAuthorIdentity() {
        return authorUsername;
    }

    public void setAuthorIdentity(String authorIdentity) {
        this.authorUsername = authorIdentity;
    }

    @XmlElement(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @XmlElement(name = "mark")
    public int getMark() {
        return grade;
    }

    public void setMark(int mark) {
        this.grade = mark;
    }

    @XmlElement(name = "positiveAppreciations")
    public int getPositiveAppreciations() {
        return positiveAppreciations;
    }

    public void setPositiveAppreciations(int positiveAppreciations) {
        this.positiveAppreciations = positiveAppreciations;
    }

    @XmlElement(name = "negativeAppreciations")
    public int getNegativeAppreciations() {
        return negativeAppreciations;
    }

    public void setNegativeAppreciations(int negativeAppreciations) {
        this.negativeAppreciations = negativeAppreciations;
    }

    @XmlElement(name = "userAppreciation")
    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    @XmlElement(name = "appreciable")
    public boolean isAppreciable() {
        return appreciable;
    }

    public void setAppreciable(boolean appreciable) {
        this.appreciable = appreciable;
    }
}