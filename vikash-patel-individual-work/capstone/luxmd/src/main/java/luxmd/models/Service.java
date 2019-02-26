/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luxmd.models;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author vpatel
 */
public class Service 
{
    private int serviceId;
    private String careType;
    private int flatRate;
    private List<Doctor> doctors;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getCareType() {
        return careType;
    }

    public void setCareType(String careType) {
        this.careType = careType;
    }

    public int getFlatRate() {
        return flatRate;
    }

    public void setFlatRate(int flatRate) {
        this.flatRate = flatRate;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.careType);
        hash = 41 * hash + this.flatRate;
        hash = 41 * hash + Objects.hashCode(this.doctors);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Service other = (Service) obj;
        if (this.flatRate != other.flatRate) {
            return false;
        }
        if (!Objects.equals(this.careType, other.careType)) {
            return false;
        }
        if (!Objects.equals(this.doctors, other.doctors)) {
            return false;
        }
        return true;
    }
    
    
}
