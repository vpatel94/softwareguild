/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedatabase.ui;
import moviedatabase.dto.*;
import java.util.*;
/**
 *
 * @author vpatel
 */
public class MovieDatabaseView 
{
    UserIO io;

    public MovieDatabaseView(UserIO io)
    {
        this.io = io;
    }
    
    public int printMenuAndGetSelection() 
    {
        io.println("\n*********************************************************");
        io.println("Welcome to the Movie Database!");
        io.println("Main Menu:");
        io.println("1. List All Movies");
        io.println("2. Add A Movie");
        io.println("3. Remove A Movie");
        io.println("4. Edit Movie Information");
        io.println("5. Display Movie Information");
        io.println("6. Search For A Movie");
        io.println("7. Exit Program");
        int choice = io.readInt("Please select from the above choices: ", 1, 7);
        return choice;
    }
    
    public Movie getNewMovieInput() 
    {
        io.println("\n***** Add A Movie *****");
        String title = io.readString("Please enter the movie's title: ");
        String date = io.readString("Please enter the release date: ");
        String rating = io.readString("Please enter the MPAA rating: ");
        String director = io.readString("Please enter the director's name: ");
        String studio = io.readString("Please enter the studio's name: ");
        String review = io.readString("Please enter a review: ");
        Movie currentMovie = new Movie();
        currentMovie.setTitle(title);
        currentMovie.setDate(date);
        currentMovie.setRating(rating);
        currentMovie.setDirector(director);
        currentMovie.setStudio(studio);
        currentMovie.setReview(review);
        io.println("Movie added successfully.");
        return currentMovie;
    }
    
    public void displayMovieList(List<Movie> movieList) 
    {
        io.println("\n***** List All Movies *****");
        io.println("Format: [ Movie ID ] [ Title ] [ Release Date ] [ MPAA Rating ] "
                + "[ Director ] [ Studio ] [ User Review ]\n");
        for (Movie currentMovie : movieList) 
        {
            io.println("[ " + currentMovie.getID() + " ] "
                    + "[ " + currentMovie.getTitle() + " ] "
                    + "[ " + currentMovie.getDate() + " ] "
                    + "[ " + currentMovie.getRating() + " ] "
                    + "[ " + currentMovie.getDirector() + " ] "
                    + "[ " + currentMovie.getStudio() + " ] "
                    + "[ " + currentMovie.getReview() + " ] ");
        }
        io.println("All movies successfully listed.");
    }
    
    public int getMovieIdChoice() 
    {
        int choice = io.readInt("\nPlease enter the Movie ID: ");
        return choice;
    }
    
    public void displayMovieBanner()
    {
        io.println("\n***** Display Movie Information *****");
    }
    
    public void displayMovieOutput(Movie movie) 
    {
        if (movie != null) 
        {
            io.println("[ " + movie.getID() + " ] "
                    + "[ " + movie.getTitle() + " ] "
                    + "[ " + movie.getDate() + " ] "
                    + "[ " + movie.getRating() + " ] "
                    + "[ " + movie.getDirector() + " ] "
                    + "[ " + movie.getStudio() + " ] "
                    + "[ " + movie.getReview() + " ] ");
        } else {
            io.println("No such movie in database.");
        }
    }
    
    public void editMovieChoiceBanner()
    {
        io.println("\n***** Edit Movie Information *****");
    }
    
    public int getEditMovieChoice()
    {
        io.println("1. Edit Title");
        io.println("2. Edit Release Date");
        io.println("3. Edit MPAA Rating");
        io.println("4. Edit Director");
        io.println("5. Edit Studio");
        io.println("6. Edit Review");
        int choice = io.readInt("Please select from the above choices: ", 1, 6);
        return choice;
    }
    
    public String editTitlePrompt()
    {
        return io.readString("Please enter a new title: ");
    }
    
    public String editDatePrompt()
    {
        return io.readString("Please enter a new release date: ");
    }
    
    public String editRatingPrompt()
    {
        return io.readString("Please enter a new MPAA rating: ");
    }
    
    public String editDirectorPrompt()
    {
        return io.readString("Please enter a new director name: ");
    }
    
    public String editStudioPrompt()
    {
        return io.readString("Please enter a new studio name: ");
    }
    
    public String editReviewPrompt()
    {
        return io.readString("Please enter a new user review: ");
    }
    
    public void removeMovieBanner()
    {
        io.println("\n***** Remove A Movie *****");
    }
    
    public void removeMovieSuccess()
    {
        io.println("Movie removed successfully.");
    }
    
    public String searchPrompt()
    {
        io.println("\n***** Search For A Movie *****");
        String search = io.readString("Please enter the first few letters of the movie title: ");
        return search;
    }
    
    public void searchForMovieOutput(List<Movie> movieList)
    {
        io.println("Results:");
        for (Movie m : movieList)
        {
            displayMovieOutput(m);
        }
        io.println("Search complete.");
    }
    
    public void displayExitBanner() 
    {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() 
    {
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg) 
    {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
