/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachine.dao;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author vpatel
 */
public class VendingMachineAuditDAOFileImpl implements VendingMachineAuditDAO
{
    public static final String AUDIT_FILE = "audit.txt";
   
    public void writeAuditEntry(String entry) throws VendingMachineDAOException {
        PrintWriter out;
       
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new VendingMachineDAOException("Could not persist audit information.", e);
        }
 
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        out.println(timestamp.format(formatter) + " : " + entry);
        out.flush();
    }
    
}
