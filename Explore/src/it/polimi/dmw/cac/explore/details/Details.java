package it.polimi.dmw.cac.explore.details;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Details {

    private static Details emptyInstance;

    public static Details empty() {
        if (emptyInstance == null) {
            emptyInstance = new Details();
        }

        return emptyInstance;
    }

    protected Details() {

    }

}
