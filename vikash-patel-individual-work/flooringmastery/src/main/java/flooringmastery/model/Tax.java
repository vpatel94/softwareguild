/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.model;
import java.math.BigDecimal;
import java.io.*;
import java.util.*;
/**
 *
 * @author vpatel
 */
public class Tax 
{
    public static final String TAX_FILE = "Taxes.txt";
    public static final String DELIMITER = ",";
    private String stateReq;
    private BigDecimal rate;
    private static Map<String, BigDecimal> stateRate = new HashMap<>();
    
    public Tax(){}
    
    public Tax(boolean t) throws FlooringModelException
    {
        loadFile();
    }      
    
    public Tax(String s) throws FlooringModelException
    {
        this.stateReq = s;
        loadFile();
        this.rate = stateRate.get(s);
    }
    
    public List<String> getAllStates()
    {
        List<String> states = new ArrayList<>(stateRate.keySet());
        return states;
    }
    
    public String getState()
    {
        return this.stateReq;
    }
    
    public void setState(String n)
    {
        this.stateReq = n;
    }
    
    public BigDecimal getRate()
    {
        return this.rate;
    }
    
    public void setRate(BigDecimal bd)
    {
        this.rate = bd;
    }
    
    public void loadFile() throws FlooringModelException
    {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(TAX_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringModelException("Could not load tax data into memory.", e);
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) 
        {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            String state = currentTokens[0];
            String sRate = currentTokens[1];
            float fRate = Float.parseFloat(sRate);
            BigDecimal readRate = new BigDecimal(fRate).setScale(2, BigDecimal.ROUND_HALF_UP);
            // Line: STATE,RATE
            stateRate.put(state, readRate);
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Tax other = (Tax) obj;
        if (!Objects.equals(this.stateReq, other.stateReq)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tax{" + "stateReq=" + stateReq + ", rate=" + rate + '}';
    }
}
