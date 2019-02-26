/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luxmd.data;

import java.util.List;
import luxmd.TestApplicationConfiguration;
import luxmd.models.Appointment;
import luxmd.models.Doctor;
import luxmd.models.Patient;
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
public class AppointmentDatabaseDaoTest {
    
    @Autowired
    AppointmentDao aDao;
    
    @Autowired
    DoctorDao dDao;
    
    @Autowired
    PatientDao pDao;
    
    public AppointmentDatabaseDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        List<Appointment> appointments = aDao.getAllAppointments();
        for(Appointment a : appointments) {
            aDao.deleteAppointmentById(a.getAppointmentId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createAppointment and getAppointmentById methods, of class AppointmentDatabaseDao.
     */
    @Test
    public void testCreateAppointmentAndGetById() {
        
        Appointment a1 = new Appointment();
        a1.setDate("2019-01-10");
        a1.setTime("12:30:00");
        a1.setNotes("Coughing");
        a1.setTotalDue(99);
        a1.setDueDate("2019-01-17");
        a1 = aDao.createAppointment(a1);
        
        Appointment check = aDao.getAppointmentById(a1.getAppointmentId());
        
        assertEquals(a1, check);
    }

    /**
     * Test of getAllAppointments method, of class AppointmentDatabaseDao.
     */
    @Test
    public void testGetAllAppointments() {
        
        Appointment a1 = new Appointment();
        a1.setDate("2019-01-10");
        a1.setTime("12:30:00");
        a1.setNotes("Coughing");
        a1.setTotalDue(99);
        a1.setDueDate("2019-01-17");
        a1 = aDao.createAppointment(a1);
        
        Appointment a2 = new Appointment();
        a2.setDate("2019-01-12");
        a2.setTime("16:30:00");
        a2.setNotes("Headache");
        a2.setTotalDue(79);
        a2.setDueDate("2019-01-19");
        a2 = aDao.createAppointment(a2);
        
        List<Appointment> appointments = aDao.getAllAppointments();
        
        assertEquals(2, appointments.size());
        assertTrue(appointments.contains(a1));
        assertTrue(appointments.contains(a2));
    }

    /**
     * Test of update method, of class AppointmentDatabaseDao.
     */
    @Test
    public void testUpdate() {
        
        Appointment a1 = new Appointment();
        a1.setDate("2019-01-10");
        a1.setTime("12:30:00");
        a1.setNotes("Coughing");
        a1.setTotalDue(99);
        a1.setDueDate("2019-01-17");
        a1 = aDao.createAppointment(a1);
        
        Doctor d1 = new Doctor();
        d1.setFirstName("First");
        d1.setLastName("Last");
        d1.setPhoneNumber("7047047040");
        d1 = dDao.createDoctor(d1);
        
        Patient p1 = new Patient();
        p1.setFirstName("First");
        p1.setLastName("Last");
        p1.setPhoneNumber("7047047040");
        p1.setStreetAddress("123 Patient Street");
        p1.setCity("Charlotte");
        p1.setState("North Carolina");
        p1.setZipCode("28206");
        p1 = pDao.createPatient(p1);
        
        Appointment fromDao = aDao.getAppointmentById(a1.getAppointmentId());
        
        assertEquals(a1, fromDao);
        
        a1.setNotes("No longer coughing");
        
        aDao.update(a1, p1, d1);
        
        assertNotEquals(a1, fromDao);
        
        fromDao = aDao.getAppointmentById(a1.getAppointmentId());
        
        assertEquals(a1, fromDao);
    }

    /**
     * Test of deleteAppointmentById method, of class AppointmentDatabaseDao.
     */
    @Test
    public void testDeleteAppointmentById() {
        
        Appointment a1 = new Appointment();
        a1.setDate("2019-01-10");
        a1.setTime("12:30:00");
        a1.setNotes("Coughing");
        a1.setTotalDue(99);
        a1.setDueDate("2019-01-17");
        a1 = aDao.createAppointment(a1);
        
        Appointment a2 = new Appointment();
        a2.setDate("2019-01-12");
        a2.setTime("16:30:00");
        a2.setNotes("Headache");
        a2.setTotalDue(79);
        a2.setDueDate("2019-01-19");
        a2 = aDao.createAppointment(a2);
        
        List<Appointment> appointments = aDao.getAllAppointments();
        
        assertEquals(2, appointments.size());
        assertTrue(appointments.contains(a1));
        assertTrue(appointments.contains(a2));
        
        aDao.deleteAppointmentById(a1.getAppointmentId());
        appointments = aDao.getAllAppointments();
        
        assertEquals(1, appointments.size());
        assertFalse(appointments.contains(a1));
        assertTrue(appointments.contains(a2));
    }
    
}
