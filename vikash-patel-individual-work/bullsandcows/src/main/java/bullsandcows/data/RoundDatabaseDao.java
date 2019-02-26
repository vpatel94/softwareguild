/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bullsandcows.data;

import bullsandcows.models.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vpatel
 */

@Repository
@Profile("database")
public class RoundDatabaseDao implements RoundDao
{

    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public RoundDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional
    public Round createRound(Round r) 
    {
        final String sql = "INSERT INTO Round(TimeStamp, Guess, Result) "
                + "VALUES(?,?,?);";
        jdbcTemplate.update(sql, 
                    r.getTimeStamp().withNano(0),
                    r.getGuess(),
                    r.getResult());
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        r.setRoundId(newId);
        return r;
    }

    @Override
    public List<Round> getAllRounds() 
    {
        final String sql = "SELECT RoundID, TimeStamp, Guess, Result FROM Round;";
        return jdbcTemplate.query(sql, new RoundMapper());
    }

    @Override
    public Round getRoundById(int id) 
    {
        final String sql = "SELECT RoundID, TimeStamp, Guess, Result FROM Round WHERE RoundID = ?;";
        return jdbcTemplate.queryForObject(sql, new RoundMapper(), id);
    }

    @Override
    @Transactional
    public boolean update(Round r) 
    {
        final String sql = "UPDATE Round "
                + "SET TimeStamp = ?, Guess = ?, Result = ? WHERE RoundID = ?";
        return jdbcTemplate.update(sql,
                r.getTimeStamp().withNano(0),
                r.getGuess(),
                r.getResult(),
                r.getRoundId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteRoundById(int id) 
    {
        final String sql = "DELETE FROM Round WHERE RoundID = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }
    
    public static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundId(rs.getInt("RoundID"));
            round.setTimeStamp(rs.getTimestamp("TimeStamp").toLocalDateTime());
            round.setGuess(rs.getString("Guess"));
            round.setResult(rs.getString("Result"));
            return round;
        }
    }
}
