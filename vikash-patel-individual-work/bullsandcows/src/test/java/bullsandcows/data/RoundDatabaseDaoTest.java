/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bullsandcows.data;

import bullsandcows.TestApplicationConfiguration;
import bullsandcows.models.*;
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
public class RoundDatabaseDaoTest {
    
    @Autowired
    RoundDao roundDao;
    
    @Autowired
    GameDao gameDao;
    
    public RoundDatabaseDaoTest() {
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
     * Test of createRound and getRoundById method, of class RoundDatabaseDao.
     */
    @Test
    public void testCreateAndGetRound() 
    {
        Round r = new Round();
        r.setTimeStamp(LocalDateTime.now());
        r.setGuess("guess");
        r.setResult("result");
        r = roundDao.createRound(r);
        
        Round check = roundDao.getRoundById(r.getRoundId());
        assertEquals(r, check);
    }

    /**
     * Test of getAllRounds method, of class RoundDatabaseDao.
     */
    @Test
    public void testGetAllRounds() 
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
        
        assertEquals(2, rounds.size());
        assertTrue(rounds.contains(r));
        assertTrue(rounds.contains(r2));
    }

    /**
     * Test of update method, of class RoundDatabaseDao.
     */
    @Test
    public void testUpdate() 
    {
        Round r = new Round();
        r.setTimeStamp(LocalDateTime.now());
        r.setGuess("guess");
        r.setResult("result");
        r = roundDao.createRound(r);
        
        Round fromDao = roundDao.getRoundById(r.getRoundId());
        
        assertEquals(r, fromDao);
        
        r.setGuess("Another Guess");
        
        roundDao.update(r);
        
        assertNotEquals(r, fromDao);
        
        fromDao = roundDao.getRoundById(r.getRoundId());
        
        assertEquals(r, fromDao);
    }

    /**
     * Test of deleteRoundById method, of class RoundDatabaseDao.
     */
    @Test
    public void testDeleteRoundById() 
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
        
        assertEquals(2, rounds.size());
        assertTrue(rounds.contains(r));
        assertTrue(rounds.contains(r2));
        
        roundDao.deleteRoundById(r.getRoundId());
        rounds = roundDao.getAllRounds();
        
        assertEquals(1, rounds.size());
        assertFalse(rounds.contains(r));
        assertTrue(rounds.contains(r2));
    }
    
}
