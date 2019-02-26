/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachine.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import vendingmachine.dto.*;
import java.math.BigDecimal;
import org.junit.Assert;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author vpatel
 */
public class VendingMachineServiceTest {
    
    //private static VendingMachineService service = new VendingMachineService();
    
    static ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext_S.xml");
    static VendingMachineService service = ctx.getBean("service", VendingMachineService.class);
    
    public VendingMachineServiceTest() {
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
/**
     * Test of vendItem method, class VendingMachineService.
     * @throws java.lang.Exception
     */
    @Test
    public void testVendItem() throws Exception
    {
        service.startMachine();
        Item i = service.getItems().get(0); //Snickers priced at 1.99 with 50 inventory
        BigDecimal bd = new BigDecimal(2.40).setScale(2, BigDecimal.ROUND_HALF_UP);
        service.vendItem("Snickers", bd);
        assertEquals(49, service.getItems().get(0).getInventory());
    }
    
    /**
     * Test of getAllItems method, class VendingMachineService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllItems() throws Exception
    {
        assertEquals(7, service.getItems().size());
    }
    
    /**
     * Test of showChange method, class VendingMachineService.
     * @throws java.lang.Exception
     */
    @Test
    public void testShowChange() throws Exception
    {
        assertEquals(new BigDecimal(0.41).setScale(2, BigDecimal.ROUND_HALF_UP), service.showChange());
    }
    
    /**
     * Test of calcChange method, class VendingMachineServie.
     * @throws java.lang.Exception
     */
    @Test
    public void testCalcChange() throws Exception
    {
        int[] i = {1,1,1,1};
        Assert.assertArrayEquals(i, service.calcChange());
    }
}
