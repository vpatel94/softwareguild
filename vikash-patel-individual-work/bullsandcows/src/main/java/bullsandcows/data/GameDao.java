/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bullsandcows.data;

import bullsandcows.models.*;
import java.util.List;

/**
 *
 * @author vpatel
 */
public interface GameDao 
{
    public Game createGame(Game g);
    public List<Game> getAllGames();
    public Game getGameById(int id);
    public boolean update(Game g);
    public boolean deleteGameById(int id);
}
