package com.damonx.simple;

public class CarMain
{
    public static void main(String[] args)
    {
        final Car ferrariCar = new Car("white", "Ferrari");
        System.out.println(String.format("This is my new %s %s Car", ferrariCar.getColour(), ferrariCar.getModel()));

        final Car car = new Car();
        car.setColour("Red");
        car.setModel("Volkswagen Tiguan");
        System.out.println(String.format("This is my new %s %s Car", car.getColour(), car.getModel()));
    }
}
