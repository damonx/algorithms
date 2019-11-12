package com.spark.digital.oidcProxy.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo {

    @JsonProperty("family_name")
    String familyName;

    String sub;
    String name;

    public String getSub() {
        return sub;
    }

    public void setSub(final String sub) {
        this.sub = sub;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(final String familyName) {
        this.familyName = familyName;
    }
}
