package it.polimi.dmw.cac.explore.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

@Model(schemaVersion = 1)
public class CheckIn implements Serializable {

    private static final long serialVersionUID = 1L;

    public CheckIn() {
        authorRef = new ModelRef<User>(User.class);
        exhibitionRef = new ModelRef<Exhibition>(Exhibition.class);
    }

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    private ModelRef<User> authorRef;
    private ModelRef<Exhibition> exhibitionRef;

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

    public User getAuthor() {
        return authorRef == null ? null : authorRef.getModel();
    }

    public void setAuthor(User author) {
        if (author == null || authorRef == null) {
            return;
        }

        authorRef.setModel(author);
    }

    public Exhibition getExhibition() {
        return exhibitionRef == null ? null : exhibitionRef.getModel();
    }

    public void setExhibition(Exhibition exhibition) {
        if (exhibition == null || exhibitionRef == null) {
            return;
        }

        exhibitionRef.setModel(exhibition);
    }

    // Relationships getters and setters
    public ModelRef<User> getAuthorRef() {
        return authorRef;
    }

    public ModelRef<Exhibition> getExhibitionRef() {
        return exhibitionRef;
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
        CheckIn other = (CheckIn) obj;
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
