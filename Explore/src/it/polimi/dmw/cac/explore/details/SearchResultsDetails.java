package it.polimi.dmw.cac.explore.details;

import it.polimi.dmw.cac.explore.model.Exhibition;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SearchResultsDetails extends Details {

    private List<ExhibitionDetails> results;

    public SearchResultsDetails(List<Exhibition> results) {
        this.results = new ArrayList<ExhibitionDetails>();

        for (Exhibition e : results) {
            this.results.add(ExhibitionDetails.searchResultDetail(e));
        }
    }

    @XmlElement(name = "results")
    public List<ExhibitionDetails> getResults() {
        return results;
    }

    public void setResults(List<ExhibitionDetails> results) {
        this.results = results;
    }
}