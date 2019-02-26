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
public class MissingMaterialException extends Exception
{
    public MissingMaterialException(String message) {
        super(message);
    }

    public MissingMaterialException(String message,
            Throwable cause) {
        super(message, cause);
    }
}
