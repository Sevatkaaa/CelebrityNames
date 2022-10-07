package com.sov.controller;

import com.sov.data.Player;
import com.sov.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api/games/{gameId}/teams/{teamId}/players")
@CrossOrigin(origins = "*")
public class PlayerController {

    @Resource
    private PlayerService playerService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addPlayerToTeam(@PathVariable String gameId, @PathVariable Long teamId, @RequestBody Player player) {
        playerService.addPlayerToTeam(gameId, teamId, player);
        return ResponseEntity.ok().build();
    }
}
