package it.polimi.dmw.cac.explore.details;

import java.util.ArrayList;
import java.util.List;

import it.polimi.dmw.cac.explore.controller.Coherence;
import it.polimi.dmw.cac.explore.model.Exhibition;
import it.polimi.dmw.cac.explore.model.Tagging;
import it.polimi.dmw.cac.explore.model.User;

import javax.xml.bind.annotation.XmlElement;

import com.google.appengine.api.datastore.KeyFactory;

public class ExhibitionDetails extends Details {

    private String id;
    private String name;
    private String description;
    private String photoUrl;
    private List<TagDetail> tags;

    private double grade;
    private Integer reviewCount;
    private Boolean reviewable;
    private Boolean checkinable;

    public static ExhibitionDetails fullDetail(Exhibition exhibition,
            User requestor) {
        return new ExhibitionDetails(exhibition, requestor);
    }

    public static ExhibitionDetails searchResultDetail(Exhibition exhibition) {
        return new ExhibitionDetails(exhibition);
    }

    public ExhibitionDetails() {

    }

    private ExhibitionDetails(Exhibition exhibition, User requestor) {
        Coherence userCoherence = Coherence.user(requestor);

        id = KeyFactory.keyToString(exhibition.getKey());
        name = exhibition.getName();
        description = exhibition.getDescription();
        photoUrl = exhibition.getPhotoUrl();
        checkinable = userCoherence.verifyCheckIn(exhibition) == null;
        reviewable = userCoherence.verifyReview(exhibition) == null;
        reviewCount = exhibition.getReviews().size();
        grade = exhibition.getGrade();
        computeTags(exhibition);
    }

    private ExhibitionDetails(Exhibition exhibition) {
        id = KeyFactory.keyToString(exhibition.getKey());
        name = exhibition.getName();
        photoUrl = exhibition.getPhotoUrl();
        grade = exhibition.getGrade();
    }

    @XmlElement(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "photoUrl")
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @XmlElement(name = "tags")
    public List<TagDetail> getTags() {
        return tags;
    }

    public void setTags(List<TagDetail> tags) {
        this.tags = tags;
    }

    @XmlElement(name = "grade")
    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @XmlElement(name = "reviewCount")
    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    @XmlElement(name = "checkinable")
    public Boolean isCheckinable() {
        return checkinable;
    }

    public void setCheckinable(boolean checkinable) {
        this.checkinable = checkinable;
    }

    @XmlElement(name = "reviewable")
    public Boolean isReviewable() {
        return reviewable;
    }

    public void setReviewable(boolean reviewable) {
        this.reviewable = reviewable;
    }

    private void computeTags(Exhibition exhibition) {
        this.tags = new ArrayList<TagDetail>();
        double maxWeight = Double.NEGATIVE_INFINITY;

        List<Tagging> taggings = exhibition.getTaggingsRef().getModelList();

        for (Tagging t : taggings) {
            double weight = t.getWeight();
            if (weight > maxWeight) {
                maxWeight = weight;
            }
        }

        for (Tagging t : taggings) {
            TagDetail tagDetail = new TagDetail();
            tagDetail.setName(t.getTag().getName());
            tagDetail.setWeight(t.getWeight() / maxWeight);
            tags.add(tagDetail);
        }
    }
}