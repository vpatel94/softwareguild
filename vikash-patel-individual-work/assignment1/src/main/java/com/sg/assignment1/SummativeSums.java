/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.assignment1;
/**
 *
 * @author vpatel
 */
public class SummativeSums 
{
    public static void main(String[] args) 
    {
        int[] a1 = {1, 90, -33, -55, 67, -16, 28, -55, 15};
        int[] a2 = {999, -60, -77, 14, 160, 301};
        int[] a3 = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 
                      140, 150, 160, 170, 180, 190, 200, -99};
        int [][] arrays = {a1, a2, a3}; //each array above has its own row in multi-dim array
        int sum = 0;
        for(int i = 0; i < arrays.length; i++)
        {
            for(int j = 0; j < arrays[i].length; j++)
            {
                sum += arrays[i][j]; //goes through each number in the respective array
            }
            System.out.println("#" + (i+1) + " Array Sum: " + sum);
            sum = 0; //resets sum back to zero before moving to next array
        }
    }
}
