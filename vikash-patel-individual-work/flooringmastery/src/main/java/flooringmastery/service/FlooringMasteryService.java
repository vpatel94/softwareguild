/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.service;
import flooringmastery.dao.*;
import flooringmastery.model.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author vpatel
 */
public class FlooringMasteryService 
{
    private LocalDate ldDate = LocalDate.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
    private String sDate = ldDate.format(formatter);
    private FlooringMasteryDAO myDAO;
    
    public FlooringMasteryService(FlooringMasteryDAO dao)
    {
        this.myDAO = dao;
    }
    
    public String getCurrentDate()
    {
        return this.sDate;
    }
    
    public List<Order> getOrders(String date) throws FlooringMasteryDAOException
    {
        return myDAO.getOrders(date);
    }
    
    public Order getOrder(String date, int orderNum) throws FlooringMasteryDAOException, MissingOrderNumException
    {
        Order o = myDAO.getOrder(date, orderNum);
        if(o == null)
        {
            throw new MissingOrderNumException("No order corresponds to this order number.");
        }
        return o;
    }
    
    public Order newOrder(String name, BigDecimal area, String state, String material)
            throws FlooringMasteryDAOException, MissingStateException, MissingMaterialException
    {
        validateState(state);
        validateMaterial(material);
        return myDAO.newOrder(sDate, name, area, state, material);
    }
    
    public void addOrder(Order o)
    {
        myDAO.addOrder(o);
    }
    
    public void editOrderName(String date, int orderNum, String name) throws FlooringMasteryDAOException, MissingOrderNumException
    {
        Order o = getOrder(date, orderNum);
        myDAO.editOrderName(o, name);
    }
    
    public void editOrderArea(String date, int orderNum, BigDecimal area) throws FlooringMasteryDAOException, MissingOrderNumException
    {
        Order o = getOrder(date, orderNum);
        myDAO.editOrderArea(o, area);
    }
    
    public void editOrderState(String date, int orderNum, String state) throws FlooringMasteryDAOException, MissingStateException, MissingOrderNumException
    {
        validateState(state);
        Order o = getOrder(date, orderNum);
        myDAO.editOrderState(o, state);
    }
    
    public void editOrderMaterial(String date, int orderNum, String material) throws FlooringMasteryDAOException, MissingMaterialException, MissingOrderNumException
    {
        validateMaterial(material);
        Order o = getOrder(date, orderNum);
        myDAO.editOrderMaterial(o, material);
    }
    
    public void removeOrder(String date, int orderNum) throws FlooringMasteryDAOException, MissingOrderNumException
    {
        Order o = getOrder(date, orderNum);
        myDAO.removeOrder(o);
    }
    
    public void save(String date, boolean prod) throws FlooringMasteryDAOException, TrainingModelException
    {
        if(prod)
        {
            myDAO.save(date);
        }
        else
        {
            throw new TrainingModelException("Program in training mode, cannot save changes to file.");
        }
    }
    
    private void validateState(String inState) throws MissingStateException
    {
        boolean found = false;
        List<String> states = myDAO.getAllStates();
        for (String state : states)
        {
            if(inState.toUpperCase().equals(state))
            {
                found = true;
            }
        }
        if(!found)
        {
            throw new MissingStateException("The requested state is unavailable for orders.");
        }
    }
    
    private void validateMaterial(String inMaterial) throws MissingMaterialException
    {
        boolean found = false;
        List<String> materials = myDAO.getAllMaterials();
        for (String material : materials)
        {
            if(inMaterial.toUpperCase().equals(material.toUpperCase()))
            {
                found = true;
            }
        }
        if(!found)
        {
            throw new MissingMaterialException("The requested material is unavailable for orders.");
        }
    }
}
