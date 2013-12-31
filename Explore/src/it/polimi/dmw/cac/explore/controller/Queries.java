package it.polimi.dmw.cac.explore.controller;

import java.util.List;

import org.slim3.datastore.Datastore;

import it.polimi.dmw.cac.explore.controller.filters.ByNameFilter;
import it.polimi.dmw.cac.explore.controller.filters.ExhibitionSorter;
import it.polimi.dmw.cac.explore.meta.*;
import it.polimi.dmw.cac.explore.model.Appreciation;
import it.polimi.dmw.cac.explore.model.Exhibition;
import it.polimi.dmw.cac.explore.model.Review;
import it.polimi.dmw.cac.explore.model.Tag;
import it.polimi.dmw.cac.explore.model.Tagging;
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
        int reviewCount =
            Datastore
                .query(review)
                .filter(review.authorRef.equal(requestor.getKey()))
                .filter(review.exhibitionRef.equal(exhibition.getKey()))
                .count();

        return reviewCount > 0;
    }

    public static Appreciation getAppreciation(User requestor, Review review) {
        AppreciationMeta appreciation = AppreciationMeta.get();
        return Datastore
            .query(appreciation)
            .filter(appreciation.authorRef.equal(requestor.getKey()))
            .filter(appreciation.reviewRef.equal(review.getKey()))
            .asSingle();
    }

    public static Tag getTagByName(String name) {
        TagMeta tag = TagMeta.get();
        return Datastore.query(tag).filter(tag.name.equal(name)).asSingle();
    }

    public static Tagging getTaggingBetween(Exhibition exhibition, Tag tag) {
        TaggingMeta tagging = TaggingMeta.get();
        return Datastore
            .query(tagging)
            .filter(tagging.exhibitionRef.equal(exhibition.getKey()))
            .filter(tagging.tagRef.equal(tag.getKey()))
            .asSingle();
    }

    public static List<Exhibition> getExhibitionByName(String term) {
        ExhibitionMeta exhibition = ExhibitionMeta.get();
        return Datastore
            .query(exhibition)
            .filterInMemory(new ByNameFilter(term))
            .sortInMemory(new ExhibitionSorter())
            .asList();
    }

    public static List<Exhibition> getTopExhibitions() {
        ExhibitionMeta exhibition = ExhibitionMeta.get();
        return Datastore
            .query(exhibition)
            .sort(exhibition.name.desc)
            .limit(10)
            .asList();
    }

}
