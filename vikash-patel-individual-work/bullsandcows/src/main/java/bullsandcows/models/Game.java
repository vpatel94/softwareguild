/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bullsandcows.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.*;

/**
 *
 * @author vpatel
 */
public class Game 
{
    private int gameId;
    @JsonIgnore
    private String answer;
    private String status;
    private List<Round> rounds = new ArrayList<>();

    public int getGameId() {
        return gameId;
    }

    public String getAnswer() {
        return answer;
    }

    public String getStatus() {
        return status;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.gameId;
        hash = 53 * hash + Objects.hashCode(this.answer);
        hash = 53 * hash + Objects.hashCode(this.status);
        hash = 53 * hash + Objects.hashCode(this.rounds);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (!Objects.equals(this.answer, other.answer)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        return true;
    }
    
    
}
