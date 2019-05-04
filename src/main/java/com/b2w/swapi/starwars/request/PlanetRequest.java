package com.b2w.swapi.starwars.request;

import com.b2w.swapi.starwars.request.validation.PlanetValid;

/**
 * Created by arthur on 01/05/19
 */
@PlanetValid
public class PlanetRequest {

    private String name;
    private String climate;
    private String terrain;

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
}
