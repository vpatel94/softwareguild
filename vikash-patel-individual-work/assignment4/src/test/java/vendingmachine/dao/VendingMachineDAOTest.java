/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachine.dao;

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
public class VendingMachineDAOTest {
    
    //private static VendingMachineDAO dao = new VendingMachineDAOFileImpl();
    
    static ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext_D.xml");
    static VendingMachineDAO dao = ctx.getBean("dao", VendingMachineDAO.class);
    
    public VendingMachineDAOTest() {
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
     * Test of vendItem method, class VendingMachineDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testVendItem() throws Exception
    {
        dao.startMachine();
        Item i = dao.getItems().get(0); //Snickers priced at 1.99 with 50 inventory
        BigDecimal bd = new BigDecimal(2.40).setScale(2, BigDecimal.ROUND_HALF_UP);
        dao.vendItem(i, bd);
        assertEquals(49, dao.getItems().get(0).getInventory());
    }
    
    /**
     * Test of getAllItems method, class VendingMachineDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllItems() throws Exception
    {
        assertEquals(7, dao.getItems().size());
    }
    
    /**
     * Test of showChange method, class VendingMachineDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testShowChange() throws Exception
    {
        assertEquals(new BigDecimal(0.41).setScale(2, BigDecimal.ROUND_HALF_UP), dao.showChange());
    }
    
    /**
     * Test of calcChange method, class VendingMachineDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testCalcChange() throws Exception
    {
        int[] i = {1,1,1,1};
        Assert.assertArrayEquals(i, dao.calcChange());
    }
    
}
