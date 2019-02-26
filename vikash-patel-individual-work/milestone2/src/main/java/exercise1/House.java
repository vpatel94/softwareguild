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
public class House 
{
    private int sqft;
    private String address;
    
    public House(int sqft, String address)
    {
        this.sqft = sqft;
        this.address = address;
    }
    
    public int getSqft()
    {
        return this.sqft;
    }
    
    public void setSqft(int sqft)
    {
        this.sqft = sqft;
    }
    
    public String getAddress()
    {
        return this.address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
}
