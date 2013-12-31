package it.polimi.dmw.cac.explore.controller.filters;

import java.util.HashMap;
import java.util.Map;

import it.polimi.dmw.cac.explore.model.Exhibition;

import org.slim3.datastore.InMemorySortCriterion;

public class ExhibitionSorter implements InMemorySortCriterion {

    private Map<Exhibition, Double> cache;

    public ExhibitionSorter() {
        cache = new HashMap<Exhibition, Double>();
    }

    public int compare(Object model1, Object model2)
            throws IllegalStateException {
        if (!(model1 instanceof Exhibition) || !(model2 instanceof Exhibition)) {
            throw new IllegalStateException("Can compare only two exhibition");
        }

        Exhibition exh1 = (Exhibition) model1, exh2 = (Exhibition) model2;

        double g1 = getGrade(exh1), g2 = getGrade(exh2);

        int comparation = Double.compare(g1, g2);

        if (comparation != 0) {
            return comparation;
        }

        return exh1.getName().compareTo(exh2.getName());
    }

    private double getGrade(Exhibition exh) {
        if (cache.keySet().contains(exh)) {
            return cache.get(exh);
        } else {
            double grade = exh.getGrade();
            cache.put(exh, grade);
            return grade;
        }
    }
}
