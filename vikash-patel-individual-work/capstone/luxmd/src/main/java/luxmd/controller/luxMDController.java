/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luxmd.controller;

import java.time.LocalDate;
import java.util.List;
import luxmd.models.Appointment;
import luxmd.models.Doctor;
import luxmd.models.HybridAppt;
import luxmd.models.Patient;
import luxmd.models.Service;
import luxmd.models.UpcomingDocAppt;
import luxmd.models.UpcomingPatAppt;
import luxmd.service.luxMDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vpatel
 */
@RestController
@RequestMapping("/api/luxMD")
public class luxMDController 
{
    @Autowired
    luxMDService service;
    
    @GetMapping("/ourServices")
    @ResponseStatus(HttpStatus.OK)
    public List<Service> getAllServices()
    {
        return service.getAllServices();
    }
    
    @GetMapping("/ourServices/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Service getService(@PathVariable int id)
    {
        return service.getServiceById(id);
    }
    
    @GetMapping("/ourServices/ourDoctors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Doctor> getDoctorsForService(@PathVariable int id)
    {
        return service.getDoctorsForService(id);
    }
    
    @GetMapping("/myCare/Patient/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient getPatientById(@PathVariable int id)
    {
        return service.getPatientById(id);
    }
    
    @PostMapping("/myCare/newPatient")
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public int newPatient(@RequestBody Patient p)
    {
        service.createPatient(p);
        return p.getPatientId();
    }
    
    @PostMapping("/myCare/editProfile/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @CrossOrigin
    public Patient editProfile(@PathVariable int id, @RequestBody Patient p)
    {
        service.updatePatient(p);
        return p;
    }
    
    @PostMapping("/myCare/newAppointment")
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public Appointment newAppointment(@RequestBody HybridAppt hybridappt)
    {
        Appointment a = new Appointment();
        Doctor d = service.getDoctorById(hybridappt.getDoctorId());
        Patient p = service.getPatientById(hybridappt.getPatientId());
        Service s = service.getServiceFromDoctor(d);
        a.setDate(hybridappt.getDate());
        a.setTime(hybridappt.getTime());
        a.setNotes(hybridappt.getNotes());
        a.setTotalDue(s.getFlatRate());
        LocalDate curDate = LocalDate.parse(hybridappt.getDate());
        LocalDate dueDate = curDate.plusDays(7);
        a.setDueDate(dueDate.toString());
        service.createAppointment(a, p, d);
        return a;
    }
    
    @PostMapping("/myCare/editAppointment/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @CrossOrigin
    public Appointment editAppointment(@PathVariable int id, @RequestBody HybridAppt hybridappt)
    {
        Appointment a = service.getAppointmentById(id);
        Doctor d = service.getDoctorById(service.getDoctorForAppointment(a).getDoctorId());
        Patient p = service.getPatientById(service.getPatientFromAppointment(a).getPatientId());
//        Service s = service.getServiceFromDoctor(d);
        a.setDate(hybridappt.getDate());
        a.setTime(hybridappt.getTime());
        a.setNotes(hybridappt.getNotes());
//        a.setTotalDue(s.getFlatRate());
        LocalDate curDate = LocalDate.parse(hybridappt.getDate());
        LocalDate dueDate = curDate.plusDays(7);
        a.setDueDate(dueDate.toString());
        service.updateAppointment(a, p, d);
        return a;
    }
    
    @DeleteMapping("/myCare/cancelAppointment/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @CrossOrigin
    public void cancelAppointment(@PathVariable int id)
    {
        service.deleteAppointment(service.getAppointmentById(id));
    }
    
    @GetMapping("/myCare/myAppointments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<UpcomingPatAppt> upcomingPatAppointments(@PathVariable int id)
    {
        List<UpcomingPatAppt> appts = service.upcomingPatAppointments(service.getPatientById(id));     
        return appts;
    }
    
    @GetMapping("/myCare/myAppointment/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HybridAppt showAppointment(@PathVariable int id)
    {
        HybridAppt hAppt = new HybridAppt();
        Appointment a = service.getAppointmentById(id);
        hAppt.setDate(a.getDate());
        hAppt.setTime(a.getTime());
        hAppt.setNotes(a.getNotes());
        return hAppt;
    }
    
    @GetMapping("/ourPC/Doctor/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Doctor getDoctorById(@PathVariable int id)
    {
        return service.getDoctorById(id);
    }
    
    @GetMapping("/ourPC/myAppointments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<UpcomingDocAppt> upcomingDocAppointments(@PathVariable int id)
    {
        List<UpcomingDocAppt> appts = service.upcomingDocAppointments(service.getDoctorById(id));     
        return appts;
    }
    
    @PostMapping("/ourPC/editInternalNote/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @CrossOrigin
    public Patient editInternalNote(@PathVariable int id, @RequestBody String internalNote)
    {
        Patient p = service.getPatientById(id);
        p.setInternalNote(internalNote);
        service.updatePatient(p);
        return p;
    }
}
