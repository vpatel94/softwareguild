/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise1;

/**
 *
 * @author vpatel
 */
public class Car 
{
    private String make;
    private String model;
    private int miles;
    
    public Car(String make, String model, int miles)
    {
        this.make = make;
        this.model = model;
        this.miles = miles;
    }
    
    public String getMake()
    {
        return this.make;
    }
    
    public void setMake(String make)
    {
        this.make = make;
    }
    
    public String getModel()
    {
        return this.model;
    }
    
    public void setModel(String model)
    {
        this.model = model;
    }
    
    public int getMiles()
    {
        return this.miles;
    }
    
    public void setMiles(int miles)
    {
        this.miles = miles;
    }
    
    public void drive(int miles)
    {
        this.setMiles(this.getMiles()+miles);
    }
}
