/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.model;
import java.math.BigDecimal;
import java.util.*;
/**
 *
 * @author vpatel
 */
public class Order 
{
    private Customer cust;
    private Tax tax;
    private Material mat;
    private static int runningOrderNum = 0;
    private int orderNum;
    private BigDecimal totalMatCost, totalLaborCost, totalCostPreTax, totalTax, totalCost;
    
    public Order()
    {
        cust = new Customer();
        tax = new Tax();
        mat = new Material();
    }
    
    public Order(String name, BigDecimal area, String state, String material)
    {
        try{
            cust = new Customer(name, area);
            tax = new Tax(state);
            mat = new Material(material);
            runningOrderNum++;
            orderNum = runningOrderNum++;
            calculate(cust, tax, mat);
        } catch (FlooringModelException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void calculate(Customer cust, Tax tax, Material mat)
    {
        totalMatCost = cust.getArea().multiply(mat.getCostPerSqft()).setScale(2, BigDecimal.ROUND_HALF_UP);
        totalLaborCost = cust.getArea().multiply(mat.getLaborCostPerSqft()).setScale(2, BigDecimal.ROUND_HALF_UP);
        totalCostPreTax = totalMatCost.add(totalLaborCost);
        totalTax = totalCostPreTax.multiply(tax.getRate()).divide(new BigDecimal(100.00)).setScale(2, BigDecimal.ROUND_HALF_UP);
        totalCost = totalCostPreTax.add(totalTax);
    }
    
    public Customer getCustomer()
    {
        return this.cust;
    }
    
    public Tax getTax()
    {
        return this.tax;
    }
    
    public Material getMaterial()
    {
        return this.mat;
    }
    
    public int getOrderNum()
    {
        return this.orderNum;
    }
    
    public void setOrderNum(int i)
    {
        this.orderNum = i;
    }
    
    public void setRunningOrderNum(int i)
    {
        this.runningOrderNum = i;
    }
    
    public String getName()
    {
        return cust.getName();
    }
    
    public void setName(String n)
    {
        this.cust.setName(n);
    }
    
    public BigDecimal getArea()
    {
        return cust.getArea();
    }
    
    public void setArea(BigDecimal bd)
    {
        this.cust.setArea(bd);
    }
    
    public String getState()
    {
        return tax.getState();
    }
    
    public void setState(String s)
    {
        this.tax.setState(s);
    }
    
    public BigDecimal getTaxRate()
    {
        return tax.getRate();
    }
    
    public void setTaxRate(BigDecimal bd)
    {
        this.tax.setRate(bd);
    }
    
    public String getMaterialType()
    {
        return mat.getMaterial();
    }
    
    public void setMaterialType(String s)
    {
        this.mat.setMaterial(s);
    }
    
    public BigDecimal getMaterialCostPerArea()
    {
        return mat.getCostPerSqft();
    }
    
    public void setMaterialCostPerArea(BigDecimal bd)
    {
        this.mat.setCostPerSqft(bd);
    }
    
    public BigDecimal getLaborCostPerArea()
    {
        return mat.getLaborCostPerSqft();
    }
    
    public void setLaborCostPerArea(BigDecimal bd)
    {
        this.mat.setLaborCostPerSqft(bd);
    }
    
    public BigDecimal getTotalMaterialCost()
    {
        return this.totalMatCost;
    }
    
    public void setTotalMaterialCost(BigDecimal bd)
    {
        this.totalMatCost = bd;
    }
    
    public BigDecimal getTotalLaborCost()
    {
        return this.totalLaborCost;
    }
    
    public void setTotalLaborCost(BigDecimal bd)
    {
        this.totalLaborCost = bd;
    }
    
    public BigDecimal getTotalTax()
    {
        return this.totalTax;
    }
    
    public void setTotalTax(BigDecimal bd)
    {
        this.totalTax = bd;
    }
    
    public BigDecimal getTotalCost()
    {
        return this.totalCost;
    }
    
    public void setTotalCost(BigDecimal bd)
    {
        this.totalCost = bd;
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
        final Order other = (Order) obj;
        if (this.orderNum != other.orderNum) {
            return false;
        }
        if (!Objects.equals(this.cust, other.cust)) {
            return false;
        }
        if (!Objects.equals(this.tax, other.tax)) {
            return false;
        }
        if (!Objects.equals(this.mat, other.mat)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "cust=" + cust + ", tax=" + tax + ", mat=" + mat + ", orderNum=" + orderNum + ", totalMatCost=" + totalMatCost + ", totalLaborCost=" + totalLaborCost + ", totalCostPreTax=" + totalCostPreTax + ", totalTax=" + totalTax + ", totalCost=" + totalCost + '}';
    }
}
