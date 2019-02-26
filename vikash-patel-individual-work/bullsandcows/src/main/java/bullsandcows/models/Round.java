/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bullsandcows.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author vpatel
 */
public class Round 
{
    @JsonIgnore
    private int roundId;
    private LocalDateTime timeStamp;
    private String guess;
    private String result;

    public int getRoundId() {
        return roundId;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getGuess() {
        return guess;
    }

    public String getResult() {
        return result;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.roundId;
        hash = 41 * hash + Objects.hashCode(this.timeStamp);
        hash = 41 * hash + Objects.hashCode(this.guess);
        hash = 41 * hash + Objects.hashCode(this.result);
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
        final Round other = (Round) obj;
        if (this.roundId != other.roundId) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        return true;
    }
    
    
}
