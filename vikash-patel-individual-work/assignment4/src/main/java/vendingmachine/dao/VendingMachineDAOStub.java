/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachine.dao;

import java.math.BigDecimal;
import java.util.*;
import vendingmachine.dto.*;

/**
 *
 * @author vpatel
 */
public class VendingMachineDAOStub implements VendingMachineDAO
{
    
    private Item i = new Item("Snickers", new BigDecimal(1.99).setScale(2, BigDecimal.ROUND_HALF_UP), 2);
    private Change change;
    List<Item> items = new ArrayList<>();
    
    public VendingMachineDAOStub()
    {
        items.add(i);
    }
    
    @Override
    public List<Item> getItems() 
    {
        return this.items;
    }

    @Override
    public void startMachine() throws VendingMachineDAOException 
    {
        
    }

    @Override
    public void vendItem(Item item, BigDecimal money) throws VendingMachineDAOException 
    {
        int currentInv = item.getInventory();
        currentInv--;
        item.setInventory(currentInv);
    }

    @Override
    public BigDecimal showChange() 
    {
        return new BigDecimal(0.41).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public int[] calcChange() 
    {
        int[] c = new int[4];
        c[0] = 1;
        c[1] = 1;
        c[2] = 1;
        c[3] = 1;
        return c;
    }
    
}
