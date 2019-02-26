/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bullsandcows.data;

import bullsandcows.models.Game;
import java.util.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vpatel
 */
@Repository
@Profile("memory")
public class GameInMemoryDao implements GameDao
{
    
    private static final List<Game> games = new ArrayList<>();
    
    @Override
    public Game createGame(Game g) {
        
        int nextId = games.stream()
                .mapToInt(i -> i.getGameId())
                .max()
                .orElse(0) + 1;

        g.setGameId(nextId);
        games.add(g);
        return g;
    }

    @Override
    public List<Game> getAllGames() {
        
        return this.games;
    }

    @Override
    public Game getGameById(int id) {
        
        return games.stream()
                .filter(i -> i.getGameId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean update(Game g) {
        
        int index = 0;
        while (index < games.size()
                && games.get(index).getGameId() != g.getGameId()) {
            index++;
        }

        if (index < games.size()) {
            games.set(index, g);
        }
        return index < games.size();
    }

    @Override
    public boolean deleteGameById(int id) {
        
        return games.removeIf(i -> i.getGameId() == id);
    }
    
}
