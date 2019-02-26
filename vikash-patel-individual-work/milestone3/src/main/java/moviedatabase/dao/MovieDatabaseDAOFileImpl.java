/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedatabase.dao;
import moviedatabase.dto.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author vpatel
 */
public class MovieDatabaseDAOFileImpl implements MovieDatabaseDAO
{
    List<Movie> movies = new ArrayList<>();
    public static final String MOVIE_FILE = "movies.txt";
    public static final String DELIMITER = "::";
    
    @Override
    public void addMovie(Movie movie) throws MovieDatabaseDAOException 
    {
        //loadMovies();
        int id;
        if(movies.size() == 0)
            {id = 0;}
        else
            {id = movies.get(movies.size() - 1).getID() + 1;}
        movie.setID(id);
        movies.add(movie);
        //writeMovies();
    }

    @Override
    public List<Movie> getAllMovies() throws MovieDatabaseDAOException 
    {
        //loadMovies();
        return movies;
    }

    @Override
    public Movie getMovie(int id) throws MovieDatabaseDAOException 
    {
        //loadMovies();
        Movie result = null;
        for(Movie m : movies)
        {
            if(m.getID() == id)
                {result = m;}
        }
        return result;
    }

    @Override
    public void removeMovie(int id) throws MovieDatabaseDAOException 
    {
        Movie m = getMovie(id);
        movies.remove(m);
        //writeMovies();
    }

    @Override
    public void editMovie(int id, int choice, String s) throws MovieDatabaseDAOException 
    {
        Movie movie = getMovie(id);
        switch(choice)
        {
            case 1: movie.setTitle(s); break;
            case 2: movie.setDate(s); break;
            case 3: movie.setRating(s); break;
            case 4: movie.setDirector(s); break;
            case 5: movie.setStudio(s); break;
            case 6: movie.setReview(s); break;
            default: break;
        }
        //writeMovies();
    }
        

    @Override
    public List<Movie> searchResults(String title) throws MovieDatabaseDAOException 
    {
        //loadMovies();
        List<Movie> results = new ArrayList<>();
        for(Movie m : movies)
        {
            if(m.getTitle().startsWith(title))
            {
                results.add(m);
            }
        }
        return results;
    }
    
    private void loadMovies() throws MovieDatabaseDAOException 
    {
        List<Movie> moviesLoaded = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(MOVIE_FILE)));
        } catch (FileNotFoundException e) {
            throw new MovieDatabaseDAOException("Could not load movie data into memory.", e);
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) 
        {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Movie currentMovie = new Movie();
            currentMovie.setID(Integer.parseInt(currentTokens[0]));
            currentMovie.setTitle(currentTokens[1]);
            currentMovie.setDate(currentTokens[2]);
            currentMovie.setRating(currentTokens[3]);
            currentMovie.setDirector(currentTokens[4]);
            currentMovie.setStudio(currentTokens[5]);
            currentMovie.setReview(currentTokens[6]);
            moviesLoaded.add(currentMovie);
        }
        scanner.close();
        movies = moviesLoaded;
    }

    private void writeMovies() throws MovieDatabaseDAOException 
    {
        PrintWriter write;
        try {
            write = new PrintWriter(new FileWriter(MOVIE_FILE));
        } catch (IOException e) {
            throw new MovieDatabaseDAOException("Could not save movie data.", e);
        }

        for (Movie m : movies) 
        {
            write.println(m.getID() + DELIMITER
                     + m.getTitle() + DELIMITER 
                     + m.getDate() + DELIMITER
                     + m.getRating() + DELIMITER
                     + m.getDirector() + DELIMITER
                     + m.getStudio() + DELIMITER
                     + m.getReview());
            write.flush();
        }
        write.close();
    }
}
