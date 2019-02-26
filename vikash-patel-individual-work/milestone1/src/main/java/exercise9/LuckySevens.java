/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise9;
import java.util.*;

/**
 *
 * @author vpatel
 */
public class LuckySevens 
{
    public static void main(String[] args) 
    {
        int initial = getInitialBet();
        play(initial);
    }
    
    private static void play(int initial)
    {
        int numRolls = 0;
        int maxMoney = 0;
        int bank = initial;
        int rollsAtMax = 0;
        while(bank > 0)
        {
            int d1 = rollDice();
            int d2 = rollDice();
            int diceSum = d1+d2;
            numRolls++;
            if (diceSum == 7)
                {bank+=4;}
            else
                {bank--;}
            
            if (bank > maxMoney)
            {
                maxMoney = bank;
                rollsAtMax = numRolls;
            }            
        }
        System.out.println("You are broke after " + numRolls + " rolls.");
        System.out.println("You should have quit after " + rollsAtMax + " rolls when you had $" + maxMoney + ".");
    }
    
    private static int rollDice()
      {
          Random dice = new Random();
          return dice.nextInt(6) + 1;
      }
    
    private static int getInitialBet()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("How much would you like to bet? ");
        return scan.nextInt();
    }
}
