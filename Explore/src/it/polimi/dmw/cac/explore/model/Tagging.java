package it.polimi.dmw.cac.explore.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

@Model(schemaVersion = 1)
public class Tagging implements Serializable {

    private static final long serialVersionUID = 1L;

    public Tagging() {
        exhibitionRef = new ModelRef<Exhibition>(Exhibition.class);
        tagRef = new ModelRef<Tag>(Tag.class);
    }

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    private double weight;

    private ModelRef<Exhibition> exhibitionRef;
    private ModelRef<Tag> tagRef;

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Exhibition getExhibition() {
        return exhibitionRef == null ? null : exhibitionRef.getModel();
    }

    public void setExhibition(Exhibition exhibition) {
        if (exhibition == null || exhibitionRef == null) {
            return;
        }
    }

    public Tag getTag() {
        return tagRef == null ? null : tagRef.getModel();
    }

    public void setTag(Tag tag) {
        if (tag == null || tagRef == null) {
            return;
        }

        tagRef.setModel(tag);
    }

    // Relationship methods
    public ModelRef<Exhibition> getExhibitionRef() {
        return exhibitionRef;
    }

    public ModelRef<Tag> getTagRef() {
        return tagRef;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Tagging other = (Tagging) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }
}
