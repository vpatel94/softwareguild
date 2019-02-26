/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bullsandcows.data;

import bullsandcows.models.Round;
import java.util.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
/**
 *
 * @author vpatel
 */

@Repository
@Profile("memory")
public class RoundInMemoryDao implements RoundDao
{

    private static final List<Round> rounds = new ArrayList<>();
    
    @Override
    public Round createRound(Round r) {
        
        int nextId = rounds.stream()
                .mapToInt(i -> i.getRoundId())
                .max()
                .orElse(0) + 1;

        r.setRoundId(nextId);
        rounds.add(r);
        return r;
    }

    @Override
    public List<Round> getAllRounds() {
        return this.rounds;
    }

    @Override
    public Round getRoundById(int id) {
        
        return rounds.stream()
                .filter(i -> i.getRoundId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean update(Round r) {
        
        int index = 0;
        while (index < rounds.size()
                && rounds.get(index).getRoundId() != r.getRoundId()) {
            index++;
        }

        if (index < rounds.size()) {
            rounds.set(index, r);
        }
        return index < rounds.size();
    }

    @Override
    public boolean deleteRoundById(int id) {
        return rounds.removeIf(i -> i.getRoundId() == id);
    }
    
}
