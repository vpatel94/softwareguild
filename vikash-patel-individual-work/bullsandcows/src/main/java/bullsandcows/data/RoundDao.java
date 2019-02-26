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
public interface RoundDao 
{
    public Round createRound(Round r);
    public List<Round> getAllRounds();
    public Round getRoundById(int id);
    public boolean update(Round r);
    public boolean deleteRoundById(int id);
}
