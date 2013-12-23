package it.polimi.dmw.cac.explore.model;

import java.io.Serializable;
import java.util.List;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    public Tag() {
        taggingsRef =
            new InverseModelListRef<Tagging, Tag>(Tagging.class, "tagRef", this);

    }

    public InverseModelListRef<Tagging, Tag> getTaggingsRef() {
        return taggingsRef;
    }

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    private String name;

    @Attribute(persistent = false)
    private InverseModelListRef<Tagging, Tag> taggingsRef;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tagging> getTaggings() {
        return taggingsRef == null ? null : taggingsRef.getModelList();
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
        Tag other = (Tag) obj;
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
