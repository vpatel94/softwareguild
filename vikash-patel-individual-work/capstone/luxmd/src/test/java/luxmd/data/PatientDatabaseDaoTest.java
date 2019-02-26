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
public class PatientDatabaseDaoTest {
    
    @Autowired
    PatientDao pDao;
    
    @Autowired
    AppointmentDao aDao;
    
    @Autowired
    DoctorDao dDao;
    
    public PatientDatabaseDaoTest() {
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
        
        List<Patient> patients = pDao.getAllPatients();
        for(Patient p : patients) {
            pDao.deletePatientById(p.getPatientId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createPatient and getPatientById methods, of class PatientDatabaseDao.
     */
    @Test
    public void testCreatePatientAndGetById() {
        
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
        
        Patient check = pDao.getPatientById(p1.getPatientId());
        
        assertEquals(p1, check);
    }

    /**
     * Test of getAllPatients method, of class PatientDatabaseDao.
     */
    @Test
    public void testGetAllPatients() {
        
        Patient p1 = new Patient();
        p1.setFirstName("First");
        p1.setLastName("Last");
        p1.setPhoneNumber("7047047040");
        p1.setStreetAddress("123 Patient Street");
        p1.setCity("Charlotte");
        p1.setState("North Carolina");
        p1.setZipCode("28206");
        p1 = pDao.createPatient(p1);
        
        Patient p2 = new Patient();
        p2.setFirstName("First2");
        p2.setLastName("Last2");
        p2.setPhoneNumber("7047047041");
        p2.setStreetAddress("123 Patient Road");
        p2.setCity("Charlotte");
        p2.setState("North Carolina");
        p2.setZipCode("28206");
        p2 = pDao.createPatient(p2);
        
        List<Patient> patients = pDao.getAllPatients();
        
        assertEquals(2, patients.size());
        assertTrue(patients.contains(p1));
        assertTrue(patients.contains(p2));
    }

    /**
     * Test of update method, of class PatientDatabaseDao.
     */
    @Test
    public void testUpdate() {
        
        Patient p1 = new Patient();
        p1.setFirstName("First");
        p1.setLastName("Last");
        p1.setPhoneNumber("7047047040");
        p1.setStreetAddress("123 Patient Street");
        p1.setCity("Charlotte");
        p1.setState("North Carolina");
        p1.setZipCode("28206");
        p1 = pDao.createPatient(p1);
        
        Patient fromDao = pDao.getPatientById(p1.getPatientId());
        
        assertEquals(p1, fromDao);
        
        p1.setStreetAddress("9000 New Block Way");
        
        pDao.update(p1);
        
        assertNotEquals(p1, fromDao);
        
        fromDao = pDao.getPatientById(p1.getPatientId());
        
        assertEquals(p1, fromDao);
    }

    /**
     * Test of deletePatientById method, of class PatientDatabaseDao.
     */
    @Test
    public void testDeletePatientById() {
        
        Patient p1 = new Patient();
        p1.setFirstName("First");
        p1.setLastName("Last");
        p1.setPhoneNumber("7047047040");
        p1.setStreetAddress("123 Patient Street");
        p1.setCity("Charlotte");
        p1.setState("North Carolina");
        p1.setZipCode("28206");
        p1 = pDao.createPatient(p1);
        
        Patient p2 = new Patient();
        p2.setFirstName("First2");
        p2.setLastName("Last2");
        p2.setPhoneNumber("7047047041");
        p2.setStreetAddress("123 Patient Road");
        p2.setCity("Charlotte");
        p2.setState("North Carolina");
        p2.setZipCode("28206");
        p2 = pDao.createPatient(p2);
        
        List<Patient> patients = pDao.getAllPatients();
        
        assertEquals(2, patients.size());
        assertTrue(patients.contains(p1));
        assertTrue(patients.contains(p2));
        
        pDao.deletePatientById(p1.getPatientId());
        patients = pDao.getAllPatients();
        
        assertEquals(1, patients.size());
        assertFalse(patients.contains(p1));
        assertTrue(patients.contains(p2));
    }
    
}
