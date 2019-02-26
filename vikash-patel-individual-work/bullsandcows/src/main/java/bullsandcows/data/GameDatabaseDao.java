/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bullsandcows.data;

import bullsandcows.data.RoundDatabaseDao.RoundMapper;
import bullsandcows.models.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vpatel
 */
@Repository
@Profile("database")
public class GameDatabaseDao implements GameDao
{
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public GameDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Game createGame(Game g) 
    {
        final String sql = "INSERT INTO Game(Answer, Status) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, g.getAnswer());
            statement.setString(2, g.getStatus());
            return statement;

        }, keyHolder);

        g.setGameId(keyHolder.getKey().intValue());

        return g;
    }

    @Override
    public List<Game> getAllGames() 
    {
        final String sql = "SELECT GameID, Answer, Status FROM Game;";
        return jdbcTemplate.query(sql, new GameMapper());
    }

    @Override
    public Game getGameById(int id) 
    {
        final String sql = "SELECT GameID, Answer, Status "
                + "FROM Game WHERE GameID = ?;";

        Game game = jdbcTemplate.queryForObject(sql, new GameMapper(), id);
        game.setRounds(addRoundsToGame(game));
        return game;
    }

    @Override
    @Transactional
    public boolean update(Game g) 
    {
        final String sql0 = "DELETE FROM Round WHERE GameID = ?;";
        jdbcTemplate.update(sql0, g.getGameId());
        
        List<Round> rounds = g.getRounds();
        final String sql1 = "INSERT INTO Round(TimeStamp, Guess, Result, GameID) "
                + "VALUES(?,?,?,?);";
        
        for(Round round : rounds)
        {
            jdbcTemplate.update(sql1, 
                    round.getTimeStamp().withNano(0),
                    round.getGuess(),
                    round.getResult(),
                    g.getGameId());
        }
        
        final String sql2 = "UPDATE Game "
                + "SET Status = ? "
                + "WHERE GameID = ?;";
        return jdbcTemplate.update(sql2,
                g.getStatus(),
                g.getGameId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteGameById(int id) 
    {
        final String sql0 = "DELETE FROM Round WHERE GameID = ?;";
        jdbcTemplate.update(sql0, id);
        final String sql1 = "DELETE FROM Game WHERE GameID = ?;";
        return jdbcTemplate.update(sql1, id) > 0;
    }
    
    @Transactional
    private List<Round> addRoundsToGame(Game game)
    {
        final String ROUNDS_FOR_GAME = "SELECT r.* FROM Round r "
                + "JOIN Game g ON r.GameID = g.GameID WHERE g.GameID = ?";
        return jdbcTemplate.query(ROUNDS_FOR_GAME, new RoundMapper(), 
                game.getGameId());
    }
    
    public static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("GameID"));
            game.setAnswer(rs.getString("Answer"));
            game.setStatus(rs.getString("Status"));
            return game;
        }
    }
}
