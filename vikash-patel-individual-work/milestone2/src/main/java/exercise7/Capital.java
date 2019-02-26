/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise7;

/**
 *
 * @author vpatel
 */
public class Capital 
{
    private String name;
    private int pop;
    private int sqmi;
    
    public Capital(String n, int p, int sq)
    {
        this.name = n;
        this.pop = p;
        this.sqmi = sq;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public int getPopulation()
    {
        return this.pop;
    }
    
    public int getSqMiles()
    {
        return this.sqmi;
    }
}
