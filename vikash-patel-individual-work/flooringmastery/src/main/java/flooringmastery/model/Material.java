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
public class Material 
{
    public static final String MAT_FILE = "Products.txt";
    public static final String DELIMITER = ",";
    private String materialTypeReq;
    private BigDecimal costPerSqft;
    private BigDecimal laborCostPerSqft;
    private static Map<String, BigDecimal[]> materialCosts = new HashMap<>();
    
    public Material(){}
    
    public Material(boolean t) throws FlooringModelException
    {
        loadFile();
    }
    
    public Material(String m) throws FlooringModelException
    {
        String m1 = m.substring(0, 1).toUpperCase();
        String mFixed = m1 + m.substring(1);
        this.materialTypeReq = mFixed;
        loadFile();
        this.costPerSqft = materialCosts.get(materialTypeReq)[0];
        this.laborCostPerSqft = materialCosts.get(materialTypeReq)[1];
    }
    
    public List<String> getAllMaterials()
    {
        List<String> materials = new ArrayList<>(materialCosts.keySet());
        return materials;
    }
    
    public String getMaterial()
    {
        return this.materialTypeReq;
    }
    
    public void setMaterial(String s)
    {
        this.materialTypeReq = s;
    }
    
    public BigDecimal getCostPerSqft()
    {
        return costPerSqft;
    }
    
    public void setCostPerSqft(BigDecimal bd)
    {
        this.costPerSqft = bd;
    }
    
    public BigDecimal getLaborCostPerSqft()
    {
        return laborCostPerSqft;
    }
    
    public void setLaborCostPerSqft(BigDecimal bd)
    {
        this.laborCostPerSqft = bd;
    }
    
    
    public void loadFile() throws FlooringModelException
    {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(MAT_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringModelException("Could not load material data into memory.", e);
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) 
        {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            // Line: MATERIALTYPE,COSTperSQft,LABORCOSTperSQft
            BigDecimal[] costs = new BigDecimal[2];
            String sMatCost = currentTokens[1];
            String sLabCost = currentTokens[2];
            float fMatCost = Float.parseFloat(sMatCost);
            float fLabCost = Float.parseFloat(sLabCost);
            costs[0] = new BigDecimal(fMatCost).setScale(2, BigDecimal.ROUND_HALF_UP); // Cost per Sqft
            costs[1] = new BigDecimal(fLabCost).setScale(2, BigDecimal.ROUND_HALF_UP); // Labor Cost per Sqft
            materialCosts.put(currentTokens[0], costs);
        }
    }

    @Override
    public int hashCode() {
        int hash = 6;
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
        final Material other = (Material) obj;
        if (!Objects.equals(this.materialTypeReq, other.materialTypeReq)) {
            return false;
        }
        return true;
    }
}
