/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bullsandcows.controllers;

import bullsandcows.models.*;
import bullsandcows.service.BCService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vpatel
 */

@RestController
@RequestMapping("/api/bullsandcows")
public class BCController 
{
    @Autowired
    BCService service;
    
    @PostMapping("/begin")
    public ResponseEntity<Game> create()
    {
        Game game = service.createGame();
        ResponseEntity response = new ResponseEntity(game, HttpStatus.CREATED);
        return response;
    }
    
    @PostMapping("/guess")
    public ResponseEntity<Round> guess(int gameId, String guess)
    {
        Round round = service.makeGuess(gameId, guess);
        return ResponseEntity.ok(round);
    }
    
    @GetMapping("/game")
    public List<Game> getAllGames()
    {
        return service.getAllGames();
    }
    
    @GetMapping("/game/{id}")
    public Game getGameById(@PathVariable int id)
    {
        Game game = service.getGameById(id);
        return game;
    }
    
    @GetMapping("/rounds/{id}")
    public List<Round> getRoundsByGameId(@PathVariable int id)
    {
        return service.getRoundsbyGameId(id);
    }
}
