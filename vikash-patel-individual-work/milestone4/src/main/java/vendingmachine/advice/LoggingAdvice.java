/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachine.advice;
import vendingmachine.dao.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
/**
 *
 * @author vpatel
 */
@Aspect
public class LoggingAdvice 
{
    VendingMachineAuditDAO auditDao;
 
    public LoggingAdvice(VendingMachineAuditDAO auditDao) {
        this.auditDao = auditDao;
    }
    
    @AfterThrowing(pointcut = "execution(* vendingmachine.service.VendingMachineService.vendItem(..))",
        throwing= "error")
    public void createAuditEntry(JoinPoint jp, Throwable error) 
    {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        try {
            auditDao.writeAuditEntry(auditEntry + "Exception: " + error.getMessage());
        } catch (VendingMachineDAOException e) {
            System.err.println(
               "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    
}
