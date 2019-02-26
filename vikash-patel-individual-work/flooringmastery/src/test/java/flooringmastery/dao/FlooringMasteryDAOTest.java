/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import flooringmastery.dao.*;
import flooringmastery.model.*;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author vpatel
 */
public class FlooringMasteryDAOTest {
    
    static ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext_D.xml");
    private static FlooringMasteryDAO testDAO = ctx.getBean("dao", FlooringMasteryDAO.class);
    private final static String DATE = "12345678";
    private final int INITIAL_SIZE = 3;
    
    public FlooringMasteryDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testGetOrders() throws FlooringMasteryDAOException
    {
        List<Order> orders = testDAO.getOrders(DATE);
        assertEquals(INITIAL_SIZE, orders.size());
    }
    
    @Test
    public void testGetOrder() throws FlooringMasteryDAOException
    {
        int orderNum = 4;
        Order o = testDAO.getOrder(DATE, orderNum);
        assertEquals(orderNum, o.getOrderNum());
    }
    
    @Test
    public void testNewOrder() throws FlooringMasteryDAOException
    {
        Order o = testDAO.newOrder(DATE, "name", new BigDecimal(10.00), "OH", "Wood");
        assertEquals("name", o.getName());
    }
    
    @Test
    public void testAddOrder() throws FlooringMasteryDAOException
    {
        Order o = testDAO.newOrder(DATE, "name", new BigDecimal(10.00), "OH", "Wood");
        List<Order> orders = testDAO.addOrder(o);
        int size = orders.size();
        assertEquals(INITIAL_SIZE + 1, size);
    }
    
    @Test
    public void testEditOrderName() throws FlooringMasteryDAOException
    {
        Order o = testDAO.newOrder(DATE, "name", new BigDecimal(10.00), "OH", "Wood");
        String name = "John";
        Order edit = testDAO.editOrderName(o, name);
        assertEquals(name, edit.getName());
    }
    
    @Test
    public void testEditOrderArea() throws FlooringMasteryDAOException
    {
        Order o = testDAO.newOrder(DATE, "name", new BigDecimal(10.00), "OH", "Wood");
        BigDecimal bd = new BigDecimal(5.00).setScale(2);
        Order edit = testDAO.editOrderArea(o, bd);
        assertEquals(bd, edit.getArea());
    }
    
    @Test
    public void testEditOrderState() throws FlooringMasteryDAOException
    {
        Order o = testDAO.newOrder(DATE, "name", new BigDecimal(10.00), "OH", "Wood");
        String state = "PA";
        Order edit = testDAO.editOrderState(o, state);
        assertEquals(state, edit.getState());
    }
    
    @Test
    public void testEditOrderMaterial() throws FlooringMasteryDAOException
    {
        Order o = testDAO.newOrder(DATE, "name", new BigDecimal(10.00), "OH", "Wood");
        String material = "Tile";
        Order edit = testDAO.editOrderMaterial(o, material);
        assertEquals(material, edit.getMaterialType());
    }
    
    @Test
    public void testRemoveOrder() throws FlooringMasteryDAOException
    {
        Order o = testDAO.newOrder(DATE, "name", new BigDecimal(10.00), "OH", "Wood");
        int addSize = testDAO.addOrder(o).size();
        assertEquals(INITIAL_SIZE + 1, addSize);
        List<Order> orders = testDAO.removeOrder(o);
        int removeSize = orders.size();
        assertEquals(INITIAL_SIZE, removeSize);
    }
}
