/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachine.dto;
import java.math.BigDecimal;

/**
 *
 * @author vpatel
 */
public class Change 
{
    private final BigDecimal QUARTER = new BigDecimal(0.25);
    private final BigDecimal DIME = new BigDecimal(0.10);
    private final BigDecimal NICKEL = new BigDecimal(0.05);
    private final BigDecimal PENNY = new BigDecimal(0.01);
    private BigDecimal change;
    
    public Change(BigDecimal change)
    {
        this.change = change;
    }
    
    public BigDecimal getChange()
    {
        return this.change;
    }
    
    public int getQuarters()
    {
        int q = change.divide(QUARTER, 2, BigDecimal.ROUND_HALF_UP).intValue();
        change = change.subtract(QUARTER.multiply(new BigDecimal(q).setScale(2)));
        return q;
    }
    
    public int getDimes()
    {
        int d = change.divide(DIME, 2, BigDecimal.ROUND_HALF_UP).intValue();
        change = change.subtract(DIME.multiply(new BigDecimal(d).setScale(2)));
        return d;
    }
    
    public int getNickels()
    {
        int n = change.divide(NICKEL, 2, BigDecimal.ROUND_HALF_UP).intValue();
        change = change.subtract(NICKEL.multiply(new BigDecimal(n).setScale(2)));
        return n;
    }
    
    public int getPennies()
    {
        int p = change.divide(PENNY, 2, BigDecimal.ROUND_HALF_UP).intValue();
        change = change.subtract(PENNY.multiply(new BigDecimal(p).setScale(2)));
        return p;
    }
}
