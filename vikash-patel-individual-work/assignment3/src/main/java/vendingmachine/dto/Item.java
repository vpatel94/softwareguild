/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachine.dto;
import java.math.BigDecimal;

/**
 *
 * @author vpatel
 */
public class Item 
{
    private String name;
    private BigDecimal cost;
    private int inventory;
    
    public Item(){}
    
    public Item(String n, BigDecimal c, int i)
    {
        this.name = n;
        this.cost = c;
        this.inventory = i;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String n)
    {
        this.name = n;
    }
    
    public BigDecimal getCost()
    {
        return this.cost;
    }
    
    public void setCost(BigDecimal c)
    {
        this.cost = c;
    }
    
    public int getInventory()
    {
        return this.inventory;
    }
    
    public void setInventory(int i)
    {
        this.inventory = i;
    }
}
