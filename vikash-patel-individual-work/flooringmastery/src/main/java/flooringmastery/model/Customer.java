/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.model;
import java.math.BigDecimal;
import java.util.Objects;
/**
 *
 * @author vpatel
 */
public class Customer 
{
    private String name;
    private BigDecimal areaRequested;
    
    public Customer(){}
    
    public Customer(String n, BigDecimal ar)
    {
        this.name = n;
        this.areaRequested = ar;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String n)
    {
        this.name = n;
    }
    
    public BigDecimal getArea()
    {
        return this.areaRequested;
    }
    
    public void setArea(BigDecimal area)
    {
        this.areaRequested = area;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.areaRequested, other.areaRequested)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Customer{" + "name=" + name + ", areaRequested=" + areaRequested + '}';
    }
}
