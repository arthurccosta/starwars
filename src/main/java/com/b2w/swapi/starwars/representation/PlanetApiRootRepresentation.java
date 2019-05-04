package com.b2w.swapi.starwars.representation;

import java.util.List;

/**
 * Created by arthur on 01/05/19
 */
public class PlanetApiRootRepresentation {

    private List<PlanetApiRepresentation> results;

    public PlanetApiRootRepresentation() {
    }

    public PlanetApiRootRepresentation(List<PlanetApiRepresentation> results) {
        this.results = results;
    }

    public List<PlanetApiRepresentation> getResults() {
        return results;
    }

    public void setResults(List<PlanetApiRepresentation> results) {
        this.results = results;
    }
}
