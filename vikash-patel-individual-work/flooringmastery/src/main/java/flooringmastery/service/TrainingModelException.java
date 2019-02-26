/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.service;

/**
 *
 * @author vpatel
 */
public class TrainingModelException extends Exception
{
    public TrainingModelException(String message) {
        super(message);
    }

    public TrainingModelException(String message,
            Throwable cause) {
        super(message, cause);
    }
}
