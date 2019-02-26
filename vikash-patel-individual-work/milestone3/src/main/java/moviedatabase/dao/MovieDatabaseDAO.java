/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedatabase.dao;
import moviedatabase.dto.*;
import java.util.*;
/**
 *
 * @author vpatel
 */
public interface MovieDatabaseDAO 
{    
    public void addMovie(Movie movie) throws MovieDatabaseDAOException;
    public List<Movie> getAllMovies() throws MovieDatabaseDAOException;
    public Movie getMovie(int id) throws MovieDatabaseDAOException;
    public void removeMovie(int id) throws MovieDatabaseDAOException;
    public void editMovie(int id, int choice, String s) throws MovieDatabaseDAOException;
    public List<Movie> searchResults(String title) throws MovieDatabaseDAOException;
}
