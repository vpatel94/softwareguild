/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import flooringmastery.service.*;
import flooringmastery.dao.*;
import flooringmastery.model.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 *
 * @author vpatel
 */
public class FlooringMasteryServiceTest {
    
    static ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext_S.xml");
    private static FlooringMasteryService testService = ctx.getBean("myService", FlooringMasteryService.class);
    private final int INITIAL_SIZE = 4;
    
    
    public FlooringMasteryServiceTest() {
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
    public void testGetCurrentDate() 
    {
        LocalDate ldDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String sDate = ldDate.format(formatter);
        
        assertEquals(sDate, testService.getCurrentDate());
    }
    
    @Test
    public void testGetOrders() throws FlooringMasteryDAOException
    {
        List<Order> orders = testService.getOrders(" ");
        assertEquals(INITIAL_SIZE+1, orders.size());
    }
    
    @Test
    public void testGetOrder() throws FlooringMasteryDAOException, MissingOrderNumException
    {
        Order o = testService.getOrder(" ", 1);
        assertEquals(1, o.getOrderNum());
    }
    
    @Test
    public void testNewOrder() throws FlooringMasteryDAOException, MissingStateException, MissingMaterialException
    {
        Order o = new Order("name", new BigDecimal(10.00), "OH", "Wood");
        Order o1 = testService.newOrder("name", new BigDecimal(10.00), "OH", "Wood");
        assertEquals(o.getName(), o1.getName());
        assertEquals(o.getArea(), o1.getArea());
        assertEquals(o.getState(), o1.getState());
        assertEquals(o.getMaterialType(), o1.getMaterialType());
    }
    
    @Test
    public void testAddOrder() throws FlooringMasteryDAOException
    {
        Order o = new Order("name", new BigDecimal(10.00), "OH", "Wood");
        testService.addOrder(o);
        List<Order> orders = testService.getOrders(" ");
        assertEquals(INITIAL_SIZE+1, orders.size());
    }
    
    @Test
    public void testEditOrderName() throws FlooringMasteryDAOException, MissingOrderNumException
    {
        testService.editOrderName(" ", 1, "Name001");
        assertEquals("Name001", testService.getOrder(" ", 1).getName());
    }
    
    @Test
    public void testEditOrderArea() throws FlooringMasteryDAOException, MissingOrderNumException
    {
        testService.editOrderArea(" ", 1, new BigDecimal(20.00));
        assertEquals(new BigDecimal(20.00), testService.getOrder(" ", 1).getArea());
    }
    
    @Test
    public void testEditOrderState() throws FlooringMasteryDAOException, MissingOrderNumException, MissingStateException
    {
        testService.editOrderState(" ", 1, "MI");
        assertEquals("MI", testService.getOrder(" ", 1).getState());
    }
    
    @Test
    public void testEditOrderMaterial() throws FlooringMasteryDAOException, MissingOrderNumException, MissingMaterialException
    {
        testService.editOrderMaterial(" ", 1, "Tile");
        assertEquals("Tile", testService.getOrder(" ", 1).getMaterialType());
    }
    
    @Test
    public void testRemoveOrder() throws FlooringMasteryDAOException, MissingOrderNumException
    {
        List<Order> orders = testService.getOrders(" ");
        testService.removeOrder(" ", 1);
        assertEquals(INITIAL_SIZE - 1, orders.size());
    }
}
