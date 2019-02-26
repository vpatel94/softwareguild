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
public class FlooringMasteryDAOFileImpl implements FlooringMasteryDAO
{

    private List<Order> currentOrders = new ArrayList<>();
    public static final String DELIMITER = ",";
    
    @Override
    public List<Order> getOrders(String date) throws FlooringMasteryDAOException
    {
        loadOrders(date);
        return this.currentOrders;
    }

    @Override
    public Order getOrder(String date, int orderNum) throws FlooringMasteryDAOException
    {
        loadOrders(date);
        Order result = null;
        for(Order o : currentOrders)
        {
            if(o.getOrderNum() == orderNum)
            {
                result = o;
            }
        }
        return result;
    }

    @Override
    public Order newOrder(String date, String name, BigDecimal area, String state, String material) throws FlooringMasteryDAOException
    {
        loadOrders(date);
        Order o = new Order(name, area, state, material);
        return o;
    }
    
    @Override
    public List<Order> addOrder(Order o)
    {
        currentOrders.add(o);
        return this.currentOrders;
    }
    
    @Override
    public Order editOrderName(Order o, String name) throws FlooringMasteryDAOException
    {
        o.setName(name);
        return o;
    }
    
    @Override
    public Order editOrderArea(Order o, BigDecimal area) throws FlooringMasteryDAOException
    {
        o.setArea(area);
        o.calculate(o.getCustomer(), o.getTax(), o.getMaterial());
        return o;
    }
    
    @Override
    public Order editOrderState(Order o, String state) throws FlooringMasteryDAOException
    {
        o.setState(state);
        o.calculate(o.getCustomer(), o.getTax(), o.getMaterial());
        return o;
    }
    
    @Override
    public Order editOrderMaterial(Order o, String material) throws FlooringMasteryDAOException
    {
        o.setMaterialType(material);
        o.calculate(o.getCustomer(), o.getTax(), o.getMaterial());
        return o;
    }

    @Override
    public List<Order> removeOrder(Order o) throws FlooringMasteryDAOException
    {
        currentOrders.remove(o);
        return this.currentOrders;
    }
    
    @Override
    public void save(String date) throws FlooringMasteryDAOException
    {
        writeOrders(date);
    }
    
    @Override
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
    
    @Override
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
    
    public void loadOrders(String date) throws FlooringMasteryDAOException 
    {
        List<Order> ordersLoaded = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader("Orders_" + date + ".txt")));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryDAOException("Could not load order data into memory for the requested date.", e);
        }
        String currentLine;
        String[] currentTokens;
        int lastOrderNum;
        while (scanner.hasNextLine()) 
        {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Order o = new Order();
            o.setOrderNum(Integer.parseInt(currentTokens[0]));
            o.setName(currentTokens[1]);
            o.setState(currentTokens[2]);
            o.setTaxRate(new BigDecimal(currentTokens[3]).setScale(2));
            o.setMaterialType(currentTokens[4]);
            o.setArea(new BigDecimal(currentTokens[5]).setScale(2));
            o.setMaterialCostPerArea(new BigDecimal(currentTokens[6]).setScale(2));
            o.setLaborCostPerArea(new BigDecimal(currentTokens[7]).setScale(2));
            o.setTotalMaterialCost(new BigDecimal(currentTokens[8]).setScale(2));
            o.setTotalLaborCost(new BigDecimal(currentTokens[9]).setScale(2));
            o.setTotalTax(new BigDecimal(currentTokens[10]).setScale(2));
            o.setTotalCost(new BigDecimal(currentTokens[11]).setScale(2));
            ordersLoaded.add(o);
        }
        scanner.close();
        currentOrders = ordersLoaded;
        try{
            lastOrderNum = currentOrders.get(currentOrders.size()-1).getOrderNum();
        } catch(ArrayIndexOutOfBoundsException e)
        {
            lastOrderNum = 0;
        }
        Order o = new Order();
        o.setRunningOrderNum(lastOrderNum);
    }
    
    public void writeOrders(String date) throws FlooringMasteryDAOException
    {
        PrintWriter write;
        try {
            write = new PrintWriter(new FileWriter("Orders_" + date + ".txt"));
        } catch (IOException e) {
            throw new FlooringMasteryDAOException("Could not save order data.", e);
        }

        for (Order o : currentOrders) 
        {
            write.println(o.getOrderNum() + DELIMITER
                     + o.getName() + DELIMITER 
                     + o.getState() + DELIMITER
                     + o.getTaxRate() + DELIMITER
                     + o.getMaterialType() + DELIMITER
                     + o.getArea() + DELIMITER
                     + o.getMaterialCostPerArea() + DELIMITER
                     + o.getLaborCostPerArea() + DELIMITER
                     + o.getTotalMaterialCost() + DELIMITER
                     + o.getTotalLaborCost() + DELIMITER
                     + o.getTotalTax() + DELIMITER
                     + o.getTotalCost());
            write.flush();
        }
        write.close();
    }
    
}
