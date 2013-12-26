package it.polimi.dmw.cac.explore.details;

import javax.xml.bind.annotation.XmlElement;

public class ReviewDetails extends Details{

    private String authorIdentity;
    private String text;
    private int mark;
    private int positiveAppreciations;
    private int negativeAppreciations;
    private boolean canAppreciate;

    @XmlElement(name = "author")
    public String getAuthorIdentity() {
        return authorIdentity;
    }

    public void setAuthorIdentity(String authorIdentity) {
        this.authorIdentity = authorIdentity;
    }

    @XmlElement(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @XmlElement(name = "mark")
    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @XmlElement(name = "positive")
    public int getPositiveAppreciations() {
        return positiveAppreciations;
    }

    public void setPositiveAppreciations(int positiveAppreciations) {
        this.positiveAppreciations = positiveAppreciations;
    }

    @XmlElement(name = "negative")
    public int getNegativeAppreciations() {
        return negativeAppreciations;
    }

    public void setNegativeAppreciations(int negativeAppreciations) {
        this.negativeAppreciations = negativeAppreciations;
    }

    @XmlElement(name = "canAppreciate")
    public boolean canAppreciate() {
        return canAppreciate;
    }

    public void setCanAppreciate(boolean canAppreciate) {
        this.canAppreciate = canAppreciate;
    }

}