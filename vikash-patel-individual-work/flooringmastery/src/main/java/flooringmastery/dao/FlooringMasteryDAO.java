/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.dao;
import flooringmastery.model.*;
import java.util.*;
import java.math.BigDecimal;

/**
 *
 * @author vpatel
 */
public interface FlooringMasteryDAO 
{
    public List<Order> getOrders(String date) throws FlooringMasteryDAOException;
    public Order getOrder(String date, int orderNum) throws FlooringMasteryDAOException;
    public Order newOrder(String date, String name, BigDecimal area, String state, String material) throws FlooringMasteryDAOException;
    public List<Order> addOrder(Order o);
    public Order editOrderName(Order o, String name) throws FlooringMasteryDAOException;
    public Order editOrderArea(Order o, BigDecimal area) throws FlooringMasteryDAOException;
    public Order editOrderState(Order o, String state) throws FlooringMasteryDAOException;
    public Order editOrderMaterial(Order o, String material) throws FlooringMasteryDAOException;
    public List<Order> removeOrder(Order o) throws FlooringMasteryDAOException;
    public void save(String date) throws FlooringMasteryDAOException;
    public List<String> getAllStates();
    public List<String> getAllMaterials();
}
