package it.polimi.dmw.cac.explore.model;

import java.io.Serializable;
import java.util.List;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    public User() {
        checkInsRef =
            new InverseModelListRef<CheckIn, User>(
                CheckIn.class,
                "authorRef",
                this);
        appreciationsRef =
            new InverseModelListRef<Appreciation, User>(
                Appreciation.class,
                "authorRef",
                this);
        reviewsRef =
            new InverseModelListRef<Review, User>(
                Review.class,
                "authorRef",
                this);
    }

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    private String username;
    private String name;
    private String surname;
    private String passwordHash;

    @Attribute(persistent = false)
    private InverseModelListRef<CheckIn, User> checkInsRef;

    @Attribute(persistent = false)
    private InverseModelListRef<Appreciation, User> appreciationsRef;

    @Attribute(persistent = false)
    private InverseModelListRef<Review, User> reviewsRef;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isPasswordMatching(String pwd) {
        String hash = pwd; // FIXME hash password
        return hash.equals(passwordHash);
    }

    public void setPassword(String password) {
        String hash = password; // FIXME hash password
        this.passwordHash = hash;
    }

    public List<Appreciation> getAppreciations() {
        return appreciationsRef.getModelList();
    }

    public List<CheckIn> getCheckIns() {
        return checkInsRef.getModelList();
    }

    public List<Review> getReviews() {
        return reviewsRef.getModelList();
    }

    // Proper methods
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public InverseModelListRef<Appreciation, User> getAppreciationsRef() {
        return appreciationsRef;
    }

    public InverseModelListRef<CheckIn, User> getCheckInsRef() {
        return checkInsRef;
    }

    public InverseModelListRef<Review, User> getReviewsRef() {
        return reviewsRef;
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
        User other = (User) obj;
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
