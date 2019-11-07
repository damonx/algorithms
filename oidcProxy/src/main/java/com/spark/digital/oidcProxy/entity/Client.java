package com.spark.digital.oidcProxy.entity;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class Client {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String username;
    private String secret;
    private String logo_url;

    public Client() {};

    public Client(Long id, String name, String username, String secret, String logo_url) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.secret = secret;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }
}
