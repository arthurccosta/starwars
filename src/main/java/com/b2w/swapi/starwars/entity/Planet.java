package com.b2w.swapi.starwars.entity;


import org.springframework.data.annotation.Id;

/**
 * Created by arthur on 01/05/19
 */
public class Planet {

    @Id
    private String id;

    private String name;
    private String climate;
    private String terrain;
    private Integer qtdFilms;

    public Planet() {
    }

    public Planet(String name,
                  String climate,
                  String terrain) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
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
