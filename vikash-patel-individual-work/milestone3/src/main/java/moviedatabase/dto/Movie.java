/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedatabase.dto;

/**
 *
 * @author vpatel
 */
public class Movie 
{
    private int movieID;
    private String title;
    private String releaseDate;
    private String rating; //MPAA
    private String director;
    private String studio;
    private String review;
    
    public int getID()
    {
        return this.movieID;
    }
    
    public void setID(int mID)
    {
        this.movieID = mID;
    }
    
    public String getTitle()
    {
        return this.title;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public String getDate()
    {
        return this.releaseDate;
    }
    
    public void setDate(String date)
    {
        this.releaseDate = date;
    }
    
    public String getRating()
    {
        return this.rating;
    }
    
    public void setRating(String rating)
    {
        this.rating = rating;
    }
    
    public String getDirector()
    {
        return this.director;
    }
    
    public void setDirector(String director)
    {
        this.director = director;
    }
    
    public String getStudio()
    {
        return this.studio;
    }
    
    public void setStudio(String studio)
    {
        this.studio = studio;
    }
    
    public String getReview()
    {
        return this.review;
    }
    
    public void setReview(String review)
    {
        this.review = review;
    }
}
