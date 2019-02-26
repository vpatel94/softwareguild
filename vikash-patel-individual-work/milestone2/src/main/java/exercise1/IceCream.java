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
public class IceCream 
{
    private String flavor;
    private int size;
    
    public IceCream(String flavor, int size)
    {
        this.flavor = flavor;
        this.size = size;
    }
    
    public String getFlavor()
    {
        return this.flavor;
    }
    
    public void setFlavor(String flavor)
    {
        this.flavor = flavor;
    }
    
    public int getSize()
    {
        return this.size;
    }
    
    public void setSize(int size)
    {
        this.size = size;
    }
    
}
