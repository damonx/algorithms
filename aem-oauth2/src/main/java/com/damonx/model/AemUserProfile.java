package com.damonx.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AemUserProfile
{
    private String path;
    private String gender;
    private String email;
    private String familyName;
    private String givenName;
    private String state;
    private String country;

    public static AemUserProfile ofEmpty()
    {
        return new AemUserProfile();
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(final String path)
    {
        this.path = path;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(final String gender)
    {
        this.gender = gender;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(final String email)
    {
        this.email = email;
    }

    public String getFamilyName()
    {
        return familyName;
    }

    public void setFamilyName(final String familyName)
    {
        this.familyName = familyName;
    }

    public String getGivenName()
    {
        return givenName;
    }

    public void setGivenName(final String givenName)
    {
        this.givenName = givenName;
    }

    public String getState()
    {
        return state;
    }

    public void setState(final String state)
    {
        this.state = state;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(final String country)
    {
        this.country = country;
    }

}
