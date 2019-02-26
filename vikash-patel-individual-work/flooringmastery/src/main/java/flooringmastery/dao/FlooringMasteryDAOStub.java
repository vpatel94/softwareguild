/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.dao;
import flooringmastery.model.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
/**
 *
 * @author vpatel
 */
public class FlooringMasteryDAOStub implements FlooringMasteryDAO
{
    private List<Order> orders = new ArrayList<>();
    
    public FlooringMasteryDAOStub()
    {
        Order o1 = new Order("Name1", new BigDecimal(5.25), "OH", "Wood");
        Order o2 = new Order("Name2", new BigDecimal(3.20), "OH", "Tile");
        Order o3 = new Order("Name3", new BigDecimal(7.80), "OH", "Carpet");
        Order o4 = new Order("Name4", new BigDecimal(4.40), "OH", "Wood");
        orders.add(o1);
        orders.add(o2);
        orders.add(o3);
        orders.add(o4);
    }
    
    public List<Order> getOrders(String date) throws FlooringMasteryDAOException
    {
        return this.orders;
    }
    
    public Order getOrder(String date, int orderNum) throws FlooringMasteryDAOException
    {
        Order result = null;
        for(Order o : orders)
        {
            if(o.getOrderNum() == orderNum)
            {
                result = o;
            }
        }
        return result;
    }
    
    public Order newOrder(String date, String name, BigDecimal area, String state, String material) throws FlooringMasteryDAOException
    {
        Order o = new Order(name, area, state, material);
        return o;
    }
    
    public List<Order> addOrder(Order o)
    {
        orders.add(o);
        return this.orders;
    }
    
    public Order editOrderName(Order o, String name) throws FlooringMasteryDAOException
    {
        o.setName(name);
        return o;
    }
    
    public Order editOrderArea(Order o, BigDecimal area) throws FlooringMasteryDAOException
    {
        o.setArea(area);
        o.calculate(o.getCustomer(), o.getTax(), o.getMaterial());
        return o;
    }
    
    public Order editOrderState(Order o, String state) throws FlooringMasteryDAOException
    {
        o.setState(state);
        o.calculate(o.getCustomer(), o.getTax(), o.getMaterial());
        return o;
    }
    
    public Order editOrderMaterial(Order o, String material) throws FlooringMasteryDAOException
    {
        o.setMaterialType(material);
        o.calculate(o.getCustomer(), o.getTax(), o.getMaterial());
        return o;
    }
    
    public List<Order> removeOrder(Order o) throws FlooringMasteryDAOException
    {
        orders.remove(o);
        return this.orders;
    }
    
    public void save(String date) throws FlooringMasteryDAOException
    {
        
    }
    
    public List<String> getAllStates()
    {
        List<String> states = new ArrayList<>();
        try{
            Tax t = new Tax(true);
            states = t.getAllStates();
            
        } catch (FlooringModelException e)
        {
            System.out.println(e.getMessage());
        }
        return states;
    }
    
    public List<String> getAllMaterials()
    {
        List<String> materials = new ArrayList<>();
        try{
            Material m = new Material(true);
            materials = m.getAllMaterials();
            
        } catch (FlooringModelException e)
        {
            System.out.println(e.getMessage());
        }
        return materials;
    }
}
