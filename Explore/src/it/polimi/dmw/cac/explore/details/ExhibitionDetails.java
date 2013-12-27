package it.polimi.dmw.cac.explore.details;

import java.util.ArrayList;
import java.util.List;

import it.polimi.dmw.cac.explore.controller.Queries;
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

    private int grade;
    private boolean reviewable;
    private Boolean visited;

    public ExhibitionDetails(Exhibition exhibition, User requestor) {
        id = KeyFactory.keyToString(exhibition.getKey());
        name = exhibition.getName();
        description = exhibition.getDescription();
        photoUrl = exhibition.getPhotoUrl();
        visited =
            requestor == null ? false : Queries.hasUserVisited(
                requestor,
                exhibition);
        reviewable =
            requestor == null ? false : !Queries.hasUserReviewed(
                requestor,
                exhibition);
        computeTags(exhibition);
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

    @XmlElement(name = "grade")
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @XmlElement(name = "reviewable")
    public boolean isReviewable() {
        return reviewable;
    }

    public void setReviewable(boolean reviewable) {
        this.reviewable = reviewable;
    }

    @XmlElement(name = "visited")
    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    @XmlElement(name = "tags")
    public List<TagDetail> getTags() {
        return tags;
    }

    public void setTags(List<TagDetail> tags) {
        this.tags = tags;
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