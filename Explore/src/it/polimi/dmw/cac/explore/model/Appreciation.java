package it.polimi.dmw.cac.explore.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

@Model(schemaVersion = 1)
public class Appreciation implements Serializable {

    private static final long serialVersionUID = 1L;

    public Appreciation() {
        authorRef = new ModelRef<User>(User.class);
        reviewRef = new ModelRef<Review>(Review.class);
    }

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    private ModelRef<User> authorRef;
    private ModelRef<Review> reviewRef;

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
        if (authorRef == null || author == null) {
            return;
        }

        authorRef.setModel(author);
    }
    
    public Review getReview(){
        return reviewRef == null ? null : reviewRef.getModel();
    }
    
    public void setReview(Review review){
        if(review == null || reviewRef == null){
            return;
        }
        
        reviewRef.setModel(review);
    }

    // Relations getters and setters
    public ModelRef<User> getAuthorRef() {
        return authorRef;
    }

    public ModelRef<Review> getReviewRef() {
        return reviewRef;
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
        Appreciation other = (Appreciation) obj;
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
