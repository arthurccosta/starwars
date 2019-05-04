package com.b2w.swapi.starwars.representation;

import java.io.Serializable;

/**
 * Created by arthur on 01/05/19
 */
public class PlanetRepresentation implements Serializable {
    private static final long serialversionUID = 129317831L;

    private String id;
    private String name;
    private String climate;
    private String terrain;
    private Integer qtdFilms;

    public PlanetRepresentation() {
    }

    public PlanetRepresentation(String id,
                                String name,
                                String climate,
                                String terrain,
                                Integer qtdFilms) {
        this.id = id;
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
        this.qtdFilms = qtdFilms;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public Integer getQtdFilms() {
        return qtdFilms;
    }

    public void setQtdFilms(Integer qtdFilms) {
        this.qtdFilms = qtdFilms;
    }
}

