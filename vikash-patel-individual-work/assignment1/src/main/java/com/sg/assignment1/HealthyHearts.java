/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.assignment1;
import java.util.*;
/**
 *
 * @author vpatel
 */
public class HealthyHearts 
{
    public static void main(String[] args) 
    {
        int maxRate = 220 - askAge();
        System.out.println("Your maximum heart rate should be "+maxRate+" beats per minute.");
        System.out.println("Your target HR Zone is " + (int)(maxRate*0.5) + " - " + (int)(maxRate*0.85) 
                + " beats per minute.");
    }
    
    private static int askAge()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("What is your age? ");
        return scan.nextInt();
    }
}
