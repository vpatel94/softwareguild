/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luxmd.data;

import java.util.List;
import luxmd.models.*;

/**
 *
 * @author vpatel
 */
public interface AppointmentDao 
{
    public Appointment createAppointment(Appointment a);
    public List<Appointment> getAllAppointments();
    public Appointment getAppointmentById(int id);
    public List<Appointment> getAppointmentsByPatientId(int id);
    public List<Appointment> getAppointmentsByDoctorId(int id);
    public Doctor getDoctorFromAppointmentId(int id);
    public Patient getPatientFromAppointmentId(int id);
    public boolean update(Appointment a, Patient p, Doctor d);
    public boolean deleteAppointmentById(int id);
}
