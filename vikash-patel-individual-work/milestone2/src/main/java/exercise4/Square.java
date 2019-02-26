/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise4;

/**
 *
 * @author vpatel
 */
public class Square extends Shape 
{
    private int area, perimeter;
    
    public Square(int area, int perimeter)
    {
        this.area = area;
        this.perimeter = perimeter;
    }
    
    @Override
    public int getArea()
    {
        return this.area;
    }
    
    @Override
    public int getPerimeter()
    {
        return this.perimeter;
    }
}
