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
public class DoctorDatabaseDaoTest {
    
    @Autowired
    PatientDao pDao;
    
    @Autowired
    AppointmentDao aDao;
    
    @Autowired
    DoctorDao dDao;
    
    @Autowired
    ServiceDao sDao;
    
    public DoctorDatabaseDaoTest() {
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
        
        List<Doctor> doctors = dDao.getAllDoctors();
        for(Doctor d : doctors) {
            dDao.deleteDoctorById(d.getDoctorId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createDoctor and getDoctorById methods, of class DoctorDatabaseDao.
     */
    @Test
    public void testCreateDoctor() {
        
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
        
        Doctor d1 = new Doctor();
        d1.setFirstName("First");
        d1.setLastName("Last");
        d1.setPhoneNumber("7047047040");
        d1.setAppointments(appointments);
        d1 = dDao.createDoctor(d1);
        
        Patient p1 = new Patient();
        p1.setFirstName("First");
        p1.setLastName("Last");
        p1.setPhoneNumber("7047047040");
        p1.setStreetAddress("123 Patient Street");
        p1.setCity("Charlotte");
        p1.setState("North Carolina");
        p1.setZipCode("28206");
        p1.setAppointments(appointments);
        p1 = pDao.createPatient(p1);
        aDao.update(a1, p1, d1);
        aDao.update(a2, p1, d1);
        
        Doctor check = dDao.getDoctorById(d1.getDoctorId());
        
        assertEquals(d1, check);
    }

    /**
     * Test of getAllDoctors method, of class DoctorDatabaseDao.
     */
    @Test
    public void testGetAllDoctors() {
        
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
        
        assertEquals(2, doctors.size());
        assertTrue(doctors.contains(d1));
        assertTrue(doctors.contains(d2));
    }

    /**
     * Test of update method, of class DoctorDatabaseDao.
     */
    @Test
    public void testUpdate() {
        
        Doctor d1 = new Doctor();
        d1.setFirstName("First");
        d1.setLastName("Last");
        d1.setPhoneNumber("7047047040");
        d1 = dDao.createDoctor(d1);
        
        Service s = new Service();
        s.setCareType("Primary");
        s.setFlatRate(99);
        s = sDao.createService(s);
        
        Doctor fromDao = dDao.getDoctorById(d1.getDoctorId());
        
        assertEquals(d1, fromDao);
        
        d1.setFirstName("Doctor");
        
        dDao.update(d1, s);
        
        assertNotEquals(d1, fromDao);
        
        fromDao = dDao.getDoctorById(d1.getDoctorId());
        
        assertEquals(d1, fromDao);
    }

    /**
     * Test of deleteDoctorById method, of class DoctorDatabaseDao.
     */
    @Test
    public void testDeleteDoctorById() {
        
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
        
        assertEquals(2, doctors.size());
        assertTrue(doctors.contains(d1));
        assertTrue(doctors.contains(d2));
        
        dDao.deleteDoctorById(d1.getDoctorId());
        doctors = dDao.getAllDoctors();
        
        assertEquals(1, doctors.size());
        assertFalse(doctors.contains(d1));
        assertTrue(doctors.contains(d2));
    }
    
}
