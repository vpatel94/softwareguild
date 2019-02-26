/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luxmd.data;

import java.util.List;
import luxmd.models.Doctor;
import luxmd.models.Service;

/**
 *
 * @author vpatel
 */
public interface DoctorDao 
{
    public Doctor createDoctor(Doctor d);
    public List<Doctor> getAllDoctors();
    public Doctor getDoctorById(int id);
    public List<Doctor> getDoctorsByServiceId(int id);
    public Service getServiceFromDoctor(Doctor d);
    public boolean update(Doctor d, Service s);
    public boolean deleteDoctorById(int id);
}
