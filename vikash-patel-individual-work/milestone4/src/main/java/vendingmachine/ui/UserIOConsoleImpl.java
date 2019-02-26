/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachine.ui;
import java.util.*;
/**
 *
 * @author vpatel
 */
public class UserIOConsoleImpl implements UserIO
{
    private Scanner scan = new Scanner(System.in);
    
    @Override
    public void print(String message)
    {
        System.out.print(message);
    }
    
    @Override
    public void println(String message)
    {
        System.out.println(message);
    }
    
    @Override
    public double readDouble(String prompt)
    {
        print(prompt);
        return Double.parseDouble(scan.nextLine());
    }
    
    @Override
    public double readDouble(String prompt, double min, double max)
    {
        double d1 = readDouble(prompt);
        if(min <= d1 && d1 <= max)
        {
            return d1;
        }
        else
        {
            return readDouble(prompt, min, max);
        }
    }
    
    @Override
    public float readFloat(String prompt)
    {
        print(prompt);
        return Float.parseFloat(scan.nextLine());
    }
    
    @Override
    public float readFloat(String prompt, float min, float max)
    {
        float f1 = readFloat(prompt);
        if(min <= f1 && f1 <= max)
        {
            return f1;
        }
        else
        {
            return readFloat(prompt, min, max);
        }
    }
    
    @Override
    public int readInt(String prompt)
    {
        print(prompt);
        return Integer.parseInt(scan.nextLine());
    }
    
    @Override
    public int readInt(String prompt, int min, int max)
    {
        int i1 = readInt(prompt);
        if(min <= i1 && i1 <= max)
        {
            return i1;
        }
        else
        {
            return readInt(prompt, min, max);
        }
    }
    
    @Override
    public long readLong(String prompt)
    {
        print(prompt);
        return Long.parseLong(scan.nextLine());
    }
    
    @Override
    public long readLong(String prompt, long min, long max)
    {
        long l1 = readLong(prompt);
        if(min <= l1 && l1 <= max)
        {
            return l1;
        }
        else
        {
            return readLong(prompt, min, max);
        }
    }
    
    @Override
    public String readString(String prompt)
    {
        print(prompt);
        return scan.nextLine();
    }
}
