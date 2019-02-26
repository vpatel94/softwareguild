/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luxmd.data;

import java.util.List;
import luxmd.models.Appointment;
import luxmd.models.Patient;

/**
 *
 * @author vpatel
 */
public interface PatientDao 
{
    public Patient createPatient(Patient p);
    public List<Patient> getAllPatients();
    public Patient getPatientById(int id);
    public boolean update(Patient p);
    public boolean deletePatientById(int id);
    public List<Appointment> addAppointmentsToPatient(Patient p);
}
