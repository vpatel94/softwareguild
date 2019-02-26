/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise9;

/**
 *
 * @author vpatel
 */
public class App 
{
    public static void main(String[] args)
    {
        aRainbow();
        System.out.println("**********");
        stillPositive();
        System.out.println("**********");
        hiddenNuts();
        System.out.println("**********");
        fruitsBasket();
        System.out.println("**********");
        simpleSort();
        System.out.println("**********");
        fruitSalad();
    }
    
    private static void aRainbow()
    {
        String[] colors = {"Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet"};

        System.out.println(colors[0]);
        System.out.println(colors[1]);
        System.out.println(colors[2]);
        System.out.println(colors[3]);
        System.out.println(colors[4]);
        System.out.println(colors[5]);
        System.out.println(colors[6]);
    }
    
    private static void stillPositive()
    {
        int[] numbers = { 389, -447, 26, -485, 712, -884, 94, -64, 868, -776, 227, -744, 422, -109, 259, -500, 278, -219, 799, -311};
        for(int i = 0; i < numbers.length; i++)
        {
            if(numbers[i] > 0)
            {
                System.out.println(numbers[i]);
            }
        }
    }
    
    private static void hiddenNuts()
    {
        //Mastered
    }
    
    private static void fruitsBasket()
    {
        String[] fruit = {"Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange"};
        int total = fruit.length, numapples = 0, numoranges = 0;
        
        for(int i = 0; i < total; i++)
        {
            if(fruit[i].equals("Orange")) {numoranges++;}
            else {numapples++;}
        }
        
        String[] apples = new String[numapples];
        String[] oranges = new String[numoranges];
        for(int i = 0; i < numapples; i++)
        {
            apples[i] = "Apple";
        }
        for(int i = 0; i < numoranges; i++)
        {
            oranges[i] = "Orange";
        }
        
        System.out.println("Total number of fruits in the basket: "+total);
        System.out.println("Total number of apples: "+numapples);
        System.out.println("Total number of oranges: "+numoranges);
    }
    
    private static void simpleSort()
    {
        //Mastered
    }
    
    private static void fruitSalad()
    {
        //Mastered
    }
}
