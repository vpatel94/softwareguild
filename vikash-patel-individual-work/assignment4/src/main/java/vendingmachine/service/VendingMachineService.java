/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachine.service;
import vendingmachine.dao.*;
import vendingmachine.dto.*;
import java.math.BigDecimal;
import java.util.*;


/**
 *
 * @author vpatel
 */
public class VendingMachineService 
{
    private VendingMachineDAO dao;
    private VendingMachineAuditDAO auditDAO;
    
    public VendingMachineService(VendingMachineDAO vmdao, VendingMachineAuditDAO vmadao)
    {
        dao = vmdao;
        auditDAO = vmadao;
    }
    
    public void startMachine() throws VendingMachineDAOException
    {
        dao.startMachine();
    }
    
    public void vendItem(String name, BigDecimal money) throws OutOfStockException, 
            InsufficientFundsException, InvalidSelectionException, VendingMachineDAOException
    {
        Item choice = null;
        for(Item i : dao.getItems())
        {
            if(i.getName().toLowerCase().equals(name.toLowerCase()))
            {
                if(i.getInventory() == 0)
                {
                    throw new OutOfStockException(i.getName() + " is out of stock.");
                }
                
                if(i.getCost().compareTo(money) > 0)
                {
                    throw new InsufficientFundsException("Insufficient funds to purchase " + i.getName());
                }
                
                choice = i;
            }
        }
        if(choice == null)
        {
            throw new InvalidSelectionException("Invalid item selection.");
        }
        dao.vendItem(choice, money);
        auditDAO.writeAuditEntry(choice.getName() + " VEND SUCCESSFUL | UPDATED INVENTORY: " 
                + Integer.toString(choice.getInventory()));
    }
    
    public BigDecimal showChange()
    {
        return dao.showChange();
    }
    
    public int[] calcChange()
    {
        return dao.calcChange();
    }
    
    public List<Item> getItems()
    {
        return dao.getItems();
    }
    
}
