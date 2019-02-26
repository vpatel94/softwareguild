/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luxmd.data;

import java.util.List;
import luxmd.TestApplicationConfiguration;
import luxmd.models.Doctor;
import luxmd.models.Service;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author vpatel
 */
@Profile("database")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class ServiceDatabaseDaoTest {
    
    @Autowired
    DoctorDao dDao;
    
    @Autowired
    ServiceDao sDao;
    
    public ServiceDatabaseDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        List<Doctor> doctors = dDao.getAllDoctors();
        for(Doctor d : doctors) {
            dDao.deleteDoctorById(d.getDoctorId());
        }
        
        List<Service> services = sDao.getAllServices();
        for(Service s : services) {
            sDao.deleteServiceById(s.getServiceId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createService and getServiceById methods, of class ServiceDatabaseDao.
     */
    @Test
    public void testCreateServiceAndGetById() {
        
        Doctor d1 = new Doctor();
        d1.setFirstName("First");
        d1.setLastName("Last");
        d1.setPhoneNumber("7047047040");
        d1 = dDao.createDoctor(d1);
        
        Doctor d2 = new Doctor();
        d2.setFirstName("First2");
        d2.setLastName("Last2");
        d2.setPhoneNumber("7047047041");
        d2 = dDao.createDoctor(d2);
        
        List<Doctor> doctors = dDao.getAllDoctors();
        
        Service s = new Service();
        s.setCareType("Primary");
        s.setFlatRate(99);
        s.setDoctors(doctors);
        s = sDao.createService(s);
        dDao.update(d1, s);
        dDao.update(d2, s);
        
        Service check = sDao.getServiceById(s.getServiceId());

        assertEquals(s, check);
    }

    /**
     * Test of getAllServices method, of class ServiceDatabaseDao.
     */
    @Test
    public void testGetAllServices() {
        
        Service s = new Service();
        s.setCareType("Primary");
        s.setFlatRate(99);
        s = sDao.createService(s);
        
        Service s2 = new Service();
        s2.setCareType("Nursing");
        s2.setFlatRate(79);
        s2 = sDao.createService(s2);
        
        List<Service> services = sDao.getAllServices();
        
        assertEquals(2, services.size());
        assertTrue(services.contains(s));
        assertTrue(services.contains(s2));
    }

    /**
     * Test of update method, of class ServiceDatabaseDao.
     */
    @Test
    public void testUpdate() {
        
        Service s = new Service();
        s.setCareType("Primary");
        s.setFlatRate(99);
        s = sDao.createService(s);
        
        Service fromDao = sDao.getServiceById(s.getServiceId());
        
        assertEquals(s, fromDao);
        
        s.setCareType("Nursing");
        
        sDao.update(s);
        
        assertNotEquals(s, fromDao);
        
        fromDao = sDao.getServiceById(s.getServiceId());
        
        assertEquals(s, fromDao);
    }

    /**
     * Test of deleteServiceById method, of class ServiceDatabaseDao.
     */
    @Test
    public void testDeleteServiceById() {
        
        Service s = new Service();
        s.setCareType("Primary");
        s.setFlatRate(99);
        s = sDao.createService(s);
        
        Service s2 = new Service();
        s2.setCareType("Nursing");
        s2.setFlatRate(79);
        s2 = sDao.createService(s2);
        
        List<Service> services = sDao.getAllServices();
        
        assertEquals(2, services.size());
        assertTrue(services.contains(s));
        assertTrue(services.contains(s2));
        
        sDao.deleteServiceById(s.getServiceId());
        services = sDao.getAllServices();
        
        assertEquals(1, services.size());
        assertFalse(services.contains(s));
        assertTrue(services.contains(s2));
    }
    
}
