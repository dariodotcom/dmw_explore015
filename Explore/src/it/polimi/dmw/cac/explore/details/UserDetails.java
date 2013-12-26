package it.polimi.dmw.cac.explore.details;

import it.polimi.dmw.cac.explore.model.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserDetails extends Details {

    private String username;
    private String name;
    private String surname;

    public UserDetails() {

    }

    public UserDetails(User user) {
        username = user.getUsername();
        name = user.getName();
        surname = user.getSurname();
    }

    @XmlElement(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}