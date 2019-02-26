/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bullsandcows.data;

import bullsandcows.TestApplicationConfiguration;
import bullsandcows.models.Game;
import bullsandcows.models.Round;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author vpatel
 */

@Profile("database")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GameDatabaseDaoTest {
    
    @Autowired
    RoundDao roundDao;
    
    @Autowired
    GameDao gameDao;
    
    public GameDatabaseDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        List<Round> rounds = roundDao.getAllRounds();
        for(Round round : rounds) {
            roundDao.deleteRoundById(round.getRoundId());
        }
        
        List<Game> games = gameDao.getAllGames();
        for(Game game : games) {
            gameDao.deleteGameById(game.getGameId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createGame and getGameById method, of class GameDatabaseDao.
     */
    @Test
    public void testCreateAndGetGame() 
    {
        Round r = new Round();
        r.setTimeStamp(LocalDateTime.now());
        r.setGuess("guess");
        r.setResult("result");
        r = roundDao.createRound(r);
        
        Round r2 = new Round();
        r2.setTimeStamp(LocalDateTime.now());
        r2.setGuess("guess2");
        r2.setResult("result2");
        r2 = roundDao.createRound(r2);
        
        List<Round> rounds = roundDao.getAllRounds();
        
        Game g = new Game();
        g.setAnswer("Answer");
        g.setStatus("Status");
        g.setRounds(rounds);
        g = gameDao.createGame(g);
        
        Game check = gameDao.getGameById(g.getGameId());
        assertEquals(g, check);
    }

    /**
     * Test of getAllGames method, of class GameDatabaseDao.
     */
    @Test
    public void testGetAllGames() 
    {
        Game g = new Game();
        g.setAnswer("Answer");
        g.setStatus("Status");
        g = gameDao.createGame(g);
        
        Game g2 = new Game();
        g2.setAnswer("Answer");
        g2.setStatus("Status");
        g2 = gameDao.createGame(g2);
        
        List<Game> games = gameDao.getAllGames();
        
        assertEquals(2, games.size());
        assertTrue(games.contains(g));
        assertTrue(games.contains(g2));
        
    }

    /**
     * Test of update method, of class GameDatabaseDao.
     */
    @Test
    public void testUpdate() 
    {
        Game g = new Game();
        g.setAnswer("Answer");
        g.setStatus("Status");
        g = gameDao.createGame(g);
        
        Game fromDao = gameDao.getGameById(g.getGameId());
        
        assertEquals(g, fromDao);
        
        g.setStatus("Another Status");
        
        gameDao.update(g);
        
        assertNotEquals(g, fromDao);
        
        fromDao = gameDao.getGameById(g.getGameId());
        
        assertEquals(g, fromDao);
    }

    /**
     * Test of deleteGameById method, of class GameDatabaseDao.
     */
    @Test
    public void testDeleteGameById() 
    {
        Game g = new Game();
        g.setAnswer("Answer");
        g.setStatus("Status");
        g = gameDao.createGame(g);
        
        Game g2 = new Game();
        g2.setAnswer("Answer");
        g2.setStatus("Status");
        g2 = gameDao.createGame(g2);
        
        List<Game> games = gameDao.getAllGames();
        
        assertEquals(2, games.size());
        assertTrue(games.contains(g));
        assertTrue(games.contains(g2));
        
        gameDao.deleteGameById(g.getGameId());
        games = gameDao.getAllGames();
        
        assertEquals(1, games.size());
        assertFalse(games.contains(g));
        assertTrue(games.contains(g2));
    }
    
}
