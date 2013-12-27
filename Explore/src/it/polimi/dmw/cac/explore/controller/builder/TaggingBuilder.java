package it.polimi.dmw.cac.explore.controller.builder;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

import it.polimi.dmw.cac.explore.Utils;
import it.polimi.dmw.cac.explore.controller.Queries;
import it.polimi.dmw.cac.explore.model.Exhibition;
import it.polimi.dmw.cac.explore.model.Tag;
import it.polimi.dmw.cac.explore.model.Tagging;

public class TaggingBuilder {

    private Tagging tagging;

    public static TaggingBuilder create() {
        return new TaggingBuilder();
    }

    private TaggingBuilder() {
        tagging = new Tagging();
    }

    public TaggingBuilder tag(String name, Exhibition exhibition) {
        String normalized = Utils.normalizeTagName(name);
        Tag tag = Queries.getTagByName(normalized);

        if (tag == null) {
            tag = new Tag();
            tag.setName(normalized);
            Datastore.put(tag);
        }

        tagging = Queries.getTaggingBetween(exhibition, tag);

        if (tagging == null) {
            tagging = new Tagging();
            tagging.getExhibitionRef().setModel(exhibition);
            tagging.setTag(tag);
            tagging.setWeight(1);
        } else {
            double weight = tagging.getWeight();
            tagging.setWeight(weight + 1);
        }

        return this;
    }

    public Key store() {
        return Datastore.put(tagging);
    }
}
