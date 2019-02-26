/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachine.dao;
import vendingmachine.dto.*;
import java.util.*;
import java.math.BigDecimal;

/**
 *
 * @author vpatel
 */
public interface VendingMachineDAO 
{
    public List<Item> getItems();
    public void startMachine() throws VendingMachineDAOException;
    public void vendItem(Item item, BigDecimal money) throws VendingMachineDAOException;
    public BigDecimal showChange();
    public int[] calcChange();
}
