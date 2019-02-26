/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise1;

/**
 *
 * @author vpatel
 */
public class Book 
{
    private int pages;
    private String author;
    
    public Book(int pages, String author)
    {
        this.pages = pages;
        this.author = author;
    }
    
    public int getPages()
    {
        return this.pages;
    }
    
    public void setPages(int pages)
    {
        this.pages = pages;
    }
    
    public String getAuthor()
    {
        return this.author;
    }
    
    public void setAuthor(String author)
    {
        this.author = author;
    }
}
