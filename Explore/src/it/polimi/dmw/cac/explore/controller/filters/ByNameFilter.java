package it.polimi.dmw.cac.explore.controller.filters;

import it.polimi.dmw.cac.explore.model.Exhibition;

import org.slim3.datastore.InMemoryFilterCriterion;

public class ByNameFilter implements InMemoryFilterCriterion {

    String name;

    public ByNameFilter(String name) {
        this.name = name;
    }

    public boolean accept(Object model) {
        if (!(model instanceof Exhibition)) {
            return false;
        }

        Exhibition e = (Exhibition) model;

        return e.getName().toLowerCase().contains(name);
    }

}
