package it.polimi.dmw.cac.explore.details;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TagDetail implements Comparable<TagDetail>{

    private String name;
    private double weight;

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "weight")
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int compareTo(TagDetail o) {
        return Double.compare(o.getWeight(), weight);
    }

}
