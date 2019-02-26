/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedatabase.dao;

/**
 *
 * @author vpatel
 */
public class MovieDatabaseDAOException extends Exception
{
    public MovieDatabaseDAOException(String message) 
    {
        super(message);
    }

    public MovieDatabaseDAOException(String message, Throwable cause) 
    {
        super(message, cause);
    }
}
