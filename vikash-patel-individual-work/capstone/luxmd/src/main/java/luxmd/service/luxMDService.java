/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luxmd.service;

import java.util.ArrayList;
import java.util.List;
import luxmd.data.AppointmentDao;
import luxmd.data.DoctorDao;
import luxmd.data.PatientDao;
import luxmd.data.ServiceDao;
import luxmd.models.Appointment;
import luxmd.models.Doctor;
import luxmd.models.Patient;
import luxmd.models.Service;
import luxmd.models.UpcomingDocAppt;
import luxmd.models.UpcomingPatAppt;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author vpatel
 */
@org.springframework.stereotype.Service
public class luxMDService 
{
    @Autowired
    PatientDao pDao;
    
    @Autowired
    AppointmentDao aDao;
    
    @Autowired 
    DoctorDao dDao;
    
    @Autowired
    ServiceDao sDao;
    
    public List<Service> getAllServices()
    {
        return sDao.getAllServices();
    }
    
    public Service getServiceById(int id)
    {
        return sDao.getServiceById(id);
    }
    
    public List<Doctor> getDoctorsForService(int id)
    {
        return dDao.getDoctorsByServiceId(id);
    }
    
    public Patient getPatientFromAppointment(Appointment a)
    {
        return aDao.getPatientFromAppointmentId(a.getAppointmentId());
    }
    
    public void createPatient(Patient p)
    {
        pDao.createPatient(p);
    }
    
    public Patient getPatientById(int id)
    {
        return pDao.getPatientById(id);
    }
    
    public Patient updatePatient(Patient p)
    {
        pDao.update(p);
        pDao.addAppointmentsToPatient(p);
        return p;
    }
    
    public void deletePatient(Patient p)
    {
        pDao.deletePatientById(p.getPatientId());
    }
    
    public void createAppointment(Appointment a, Patient p, Doctor d)
    {
        aDao.createAppointment(a);
        aDao.update(a, p, d);
    }
    
    public void updateAppointment(Appointment a, Patient p, Doctor d)
    {
        aDao.update(a, p, d);
    }
    
    public Appointment getAppointmentById(int id)
    {
        return aDao.getAppointmentById(id);
    }
    
    public void deleteAppointment(Appointment a)
    {
        aDao.deleteAppointmentById(a.getAppointmentId());
    }
    
    public List<UpcomingPatAppt> upcomingPatAppointments(Patient p)
    {
        List<UpcomingPatAppt> upcomingAppts = new ArrayList<>();
        for(Appointment a : aDao.getAppointmentsByPatientId(p.getPatientId()))
        {
            UpcomingPatAppt upAppt = new UpcomingPatAppt();
            int aId = a.getAppointmentId();
            upAppt.setApptId(aId);
            Doctor d = aDao.getDoctorFromAppointmentId(aId);
            String docFName = d.getFirstName();
            String docLName = d.getLastName();
            String docFullName = docFName + " " + docLName;
            upAppt.setDocName(docFullName);
            upAppt.setCareType(dDao.getServiceFromDoctor(d).getCareType());
            String dateTime = a.getDate() + " " + a.getTime();
            upAppt.setDateTime(dateTime);
            upAppt.setTotalDue(a.getTotalDue());
            upAppt.setDueDate(a.getDueDate());
            upAppt.setNotes(a.getNotes());
            upcomingAppts.add(upAppt);
        }
        return upcomingAppts;
    }
    
    public List<UpcomingDocAppt> upcomingDocAppointments(Doctor d)
    {
        List<UpcomingDocAppt> upcomingAppts = new ArrayList<>();
        for(Appointment a : aDao.getAppointmentsByDoctorId(d.getDoctorId()))
        {
            UpcomingDocAppt upAppt = new UpcomingDocAppt();
            int aId = a.getAppointmentId();
            upAppt.setApptId(aId);
            int pId = aDao.getPatientFromAppointmentId(aId).getPatientId();
            upAppt.setPatId(pId);
            String patFName = aDao.getPatientFromAppointmentId(aId).getFirstName();
            String patLName = aDao.getPatientFromAppointmentId(aId).getLastName();
            String patFullName = patFName + " " + patLName;
            upAppt.setPatName(patFullName);
            String dateTime = a.getDate() + " " + a.getTime();
            upAppt.setDateTime(dateTime);
            upAppt.setNotes(a.getNotes());
            upcomingAppts.add(upAppt);
        }
        return upcomingAppts;
    }
    
    public Doctor getDoctorById(int id)
    {
        return dDao.getDoctorById(id);
    }
    
    public Doctor getDoctorForAppointment(Appointment a)
    {
        return aDao.getDoctorFromAppointmentId(a.getAppointmentId());
    }
    
    public Service getServiceFromDoctor(Doctor d)
    {
        return dDao.getServiceFromDoctor(d);
    }
}
