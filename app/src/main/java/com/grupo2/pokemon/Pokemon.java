package com.grupo2.pokemon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pokemon {
    private String name;
    private String descripcion;
    private String image;

    public Pokemon(String name, String url, String image) {
        this.name = name;
        this.descripcion = url;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescripcion(String url) {
        this.descripcion = url;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
