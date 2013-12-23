package it.polimi.dmw.cac.explore.model;

import java.io.Serializable;
import java.util.List;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class Exhibition implements Serializable {

    private static final long serialVersionUID = 1L;

    public Exhibition() {
        checkInsRef =
            new InverseModelListRef<CheckIn, Exhibition>(
                CheckIn.class,
                "exhibitionRef",
                this);

        taggingsRef =
            new InverseModelListRef<Tagging, Exhibition>(
                Tagging.class,
                "taggingsRef",
                this);
    }

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    private String name;
    private String description;
    private String photoUrl;

    @Attribute(persistent = false)
    private InverseModelListRef<CheckIn, Exhibition> checkInsRef;

    @Attribute(persistent = false)
    private InverseModelListRef<Tagging, Exhibition> taggingsRef;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<CheckIn> getCheckIns() {
        return checkInsRef == null ? null : checkInsRef.getModelList();
    }

    public List<Tagging> getTaggings() {
        return taggingsRef == null ? null : taggingsRef.getModelList();
    }

    // Relationships getters and setters
    public InverseModelListRef<CheckIn, Exhibition> getCheckInsRef() {
        return checkInsRef;
    }

    public InverseModelListRef<Tagging, Exhibition> getTaggingsRef() {
        return taggingsRef;
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
        Exhibition other = (Exhibition) obj;
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
