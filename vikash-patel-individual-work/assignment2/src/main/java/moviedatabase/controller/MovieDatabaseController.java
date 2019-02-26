/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedatabase.controller;
import moviedatabase.ui.*;
import moviedatabase.dao.*;
import moviedatabase.dto.*;
import java.util.*;
/**
 *
 * @author vpatel
 */
public class MovieDatabaseController 
{
    MovieDatabaseView view;
    MovieDatabaseDAO dao;
    
    public MovieDatabaseController(MovieDatabaseDAO dao, MovieDatabaseView view)
    {
        this.dao = dao;
        this.view = view;
    }
    
    public void run()
    {
        boolean keepGoing = true;
        int menuSelection = 0;
        try
        {
            while (keepGoing) 
            {
                menuSelection = getMenuSelection();

                switch (menuSelection) 
                {
                    case 1:
                        showAllMovies();
                        break;
                    case 2:
                        createMovie();
                        break;
                    case 3:
                        deleteMovie();
                        break;
                    case 4:
                        editMovie();
                        break;
                    case 5:
                        displayMovieInfo();
                        break;
                    case 6:
                        searchForMovies();
                        break;
                    case 7: 
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch(MovieDatabaseDAOException e)
        {
            view.displayErrorMessage(e.getMessage());

        }
    }
    
    private void searchForMovies() throws MovieDatabaseDAOException
    {
        String search = view.searchPrompt();
        List<Movie> results = dao.searchResults(search);
        view.searchForMovieOutput(results);
    }
    
    private void displayMovieInfo() throws MovieDatabaseDAOException
    {
        view.displayMovieBanner();
        int id = view.getMovieIdChoice();
        view.displayMovieOutput(dao.getMovie(id));
    }
    
    private void editMovie() throws MovieDatabaseDAOException
    {
        view.editMovieChoiceBanner();
        int id = view.getMovieIdChoice();
        int choice = view.getEditMovieChoice();
        String s;
        switch(choice)
        {
            case 1: //edit title
                s = view.editTitlePrompt();
                dao.editMovie(id, choice, s); 
                break;
            case 2: //edit release date
                s = view.editDatePrompt();
                dao.editMovie(id, choice, s);
                break;
            case 3: //edit mpaa rating
                s = view.editRatingPrompt();
                dao.editMovie(id, choice, s); 
                break;
            case 4: //edit director
                s = view.editDirectorPrompt();
                dao.editMovie(id, choice, s); 
                break;
            case 5: //edit studio
                s = view.editStudioPrompt();
                dao.editMovie(id, choice, s); 
                break;
            case 6: //edit review
                s = view.editReviewPrompt();
                dao.editMovie(id, choice, s); 
                break;
            default:
                view.displayUnknownCommandBanner(); break;
        }
    }
    
    private void deleteMovie() throws MovieDatabaseDAOException
    {
        view.removeMovieBanner();
        dao.removeMovie(view.getMovieIdChoice());
        view.removeMovieSuccess();
    }
    
    private void createMovie() throws MovieDatabaseDAOException
    {
        Movie newMovie = view.getNewMovieInput();
        dao.addMovie(newMovie);
    }
    
    private void showAllMovies() throws MovieDatabaseDAOException
    {
        List<Movie> list = dao.getAllMovies();
        view.displayMovieList(list);
    }
    
    private int getMenuSelection() 
    {
        return view.printMenuAndGetSelection();
    }
    
    private void unknownCommand() 
    {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() 
    {
        view.displayExitBanner();
    }
    
}
