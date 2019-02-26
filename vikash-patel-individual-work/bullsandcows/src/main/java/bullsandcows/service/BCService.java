/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bullsandcows.service;

import bullsandcows.models.*;
import bullsandcows.data.*;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
/**
 *
 * @author vpatel
 */

@Service
public class BCService 
{
    @Autowired
    GameDao gameDao;
    
    @Autowired
    RoundDao roundDao;
    
    private Random gen = new Random();
    private int answer;
    private String answerStr;
    
    public Game createGame()
    {
        Game game = new Game();
        do{
            answer = (gen.nextInt(9000) + 1000);
        } while(hasDuplicate(answer));
        answerStr = answer + "";
        game.setAnswer(answerStr);
        game.setStatus("In Progress");
        game = gameDao.createGame(game);
        return game;
    }
    
    public Round makeGuess(int gameId, String guessStr)
    {
        Game game = gameDao.getGameById(gameId);
        List<Round> rounds = game.getRounds();
        Round round = new Round();
        round.setTimeStamp(LocalDateTime.now());
        round.setGuess(guessStr);
        answerStr = game.getAnswer();
        int e = 0, p = 0;
        for(int i= 0;i < 4;i++)
        {
            if(guessStr.charAt(i) == answerStr.charAt(i))
            {
                e++;
            }else if(answerStr.contains(guessStr.charAt(i)+""))
            {
                p++;
            }
        }
        String result = "e:" + e + ":p:" + p;
        if(e == 4)
        {
            game.setStatus("Finished");
        }
        round.setResult(result);
        round = roundDao.createRound(round);
        rounds.add(round);
        game.setRounds(rounds);
        gameDao.update(game);
        return round;
    }
    
    public List<Game> getAllGames()
    {
        return gameDao.getAllGames();
    }
    
    public Game getGameById(int id)
    {
        return gameDao.getGameById(id);
    }
    
    public List<Round> getRoundsbyGameId(int id)
    {
        Game game = gameDao.getGameById(id);
        return game.getRounds();
    }
    
    public static boolean hasDuplicate(int num)
    {
        boolean[] digs = new boolean[10];
        while(num > 0)
        {
            if(digs[num%10]) 
                return true;
            digs[num%10] = true;
            num/= 10;
        }
        return false;
    }
    
}
