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
public class Airplane 
{
    private int seats;
    private int topSpeed;
    
    public Airplane(int seats, int topSpeed)
    {
        this.seats = seats;
        this.topSpeed = topSpeed;
    }
    
    public int getSeats()
    {
        return this.seats;
    }
    
    public void setSeats(int seats)
    {
        this.seats = seats;
    }
    
    public int getTopSpeed()
    {
        return this.topSpeed;
    }
    
    public void setTopSpeed(int topSpeed)
    {
        this.topSpeed = topSpeed;
    }
}
