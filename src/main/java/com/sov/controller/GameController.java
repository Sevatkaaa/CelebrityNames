package com.sov.controller;

import com.sov.data.Game;
import com.sov.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api/games")
@CrossOrigin(origins = "*")
public class GameController {

    @Resource
    private GameService gameService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Game> getGame(@PathVariable String id) {
        gameService.refreshGameStatus(id);
        return ResponseEntity.ok(Game.of(gameService.getGame(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        return ResponseEntity.ok(Game.of(gameService.createGame(game)));
    }
}
