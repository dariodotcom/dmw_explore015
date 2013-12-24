package it.polimi.dmw.cac.explore.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

@Model(schemaVersion = 1)
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    public Review() {
        appreciationsRef =
            new InverseModelListRef<Appreciation, Review>(
                Appreciation.class,
                "authorRef",
                this);
        authorRef = new ModelRef<User>(User.class);
    }

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    private long dateRep;
    private String text;
    private int grade;

    @Attribute(persistent = false)
    private InverseModelListRef<Appreciation, Review> appreciationsRef;

    private ModelRef<User> authorRef;

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

    public Date getDate() {
        return new Date(dateRep);
    }

    public void setDate(Date date) {
        if (date == null) {
            return;
        }

        dateRep = date.getTime();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
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

    public List<Appreciation> getApprectiations() {
        return appreciationsRef == null ? null : appreciationsRef
            .getModelList();
    }

    // Proper getters setters
    public ModelRef<User> getAuthorRef() {
        return authorRef;
    }

    public InverseModelListRef<Appreciation, Review> getAppreciationsRef() {
        return appreciationsRef;
    }

    public long getDateRep() {
        return dateRep;
    }

    public void setDateRep(long dateRep) {
        this.dateRep = dateRep;
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
        Review other = (Review) obj;
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