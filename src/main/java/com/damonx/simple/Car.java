package com.damonx.simple;

public class Car
{
    private String colour;
    private String model;

    /**
     * Default constructor.
     */
    public Car()
    {
    }

    /**
     * Constructor.
     * @param colour
     * @param model
     */
    public Car(final String colour, final String model)
    {
        this.colour = colour;
        this.model = model;
    }

    public String getColour()
    {
        return colour;
    }

    public void setColour(final String colour)
    {
        this.colour = colour;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(final String model)
    {
        this.model = model;
    }
}
