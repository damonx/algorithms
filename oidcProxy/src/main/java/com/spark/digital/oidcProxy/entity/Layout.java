package com.spark.digital.oidcProxy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Layout {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String primary_colour;
    private String secondary_colour;
    private String logo_url;

    public Layout() {};

    public Layout(Long id, String name, String primary_colour, String secondary_colour, String logo_url) {
        this.id = id;
        this.name = name;
        this.primary_colour = primary_colour;
        this.secondary_colour = secondary_colour;
        this.logo_url = logo_url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimary_colour() {
        return primary_colour;
    }

    public void setPrimary_colour(String primary_colour) {
        this.primary_colour = primary_colour;
    }

    public String getSecondary_colour() {
        return secondary_colour;
    }

    public void setSecondary_colour(String secondary_colour) {
        this.secondary_colour = secondary_colour;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }
}
