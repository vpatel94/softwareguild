/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise9;
import java.util.*;
import java.math.*;
import java.text.*;
/**
 *
 * @author vpatel
 */
public class InterestCalc 
{
    final static double INT_RATE = 0.1; // constant interest rate
    public static void main(String[] args)
    {
        System.out.println("This mutual fund has an annual 10% interest rate.");
        BigDecimal principal = new BigDecimal(askPrincipal()).setScale(2, BigDecimal.ROUND_HALF_UP); //rounds up to nearest cent
        int years = askYears();
        int numComp = askCompound();
        System.out.println("****************************************");
        for(int i = 1; i <= years; i++)
        {
            principal = output(i, principal, numComp);
        }
    }

    private static BigDecimal output(int year, BigDecimal principal, int numComp)
    {
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US); //US currency format
        BigDecimal begPrincipal = principal; //previous year end principal becomes this year's beginning
        System.out.println("Year " + year + " Financials: ");
        System.out.println("Beginning Principal: " + n.format(begPrincipal));
        principal = calcAmount(numComp, begPrincipal).setScale(2, BigDecimal.ROUND_HALF_UP); //rounds up to nearest cent
        System.out.println("Interest Gained: " + n.format(principal.subtract(begPrincipal)));
        System.out.println("Ending Principal: " + n.format(principal));
        System.out.println("****************************************");
        return principal; // year end principal is carried over as beginning for next loop
    }
    
    private static BigDecimal calcAmount(float numComp, BigDecimal principal)
    {
        BigDecimal bd = new BigDecimal(Math.pow((1 + (INT_RATE/numComp)), numComp));
        return principal.multiply(bd);
    }    
    
    private static int askCompound()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Would you like to calculate your interest compounded annually (A), quarterly (Q), monthly (M), or daily (D)? ");
        String s = scan.nextLine();
        switch (s)
        {
            case "A": return 1;
            case "Q": return 4;
            case "M": return 12;
            case "D": return 365;
            default: 
                System.out.println("You did not enter a correct letter. Annual compound will be calculated.");
                return 1;
        }
    }
    
    private static float askPrincipal()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("How much money are you planning to invest in this mutual fund? ");
        float principal = scan.nextInt();
        return principal;
    }
    
    private static int askYears()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("How many years do you plan to keep your money in the mutual fund? ");
        int years = scan.nextInt();
        return years;
    }   
}
