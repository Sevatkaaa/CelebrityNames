package com.sov.controller;

import com.sov.controller.form.AddNamesForm;
import com.sov.controller.form.RemoveNamesForm;
import com.sov.service.NameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api/games/{gameId}")
@CrossOrigin(origins = "*")
public class NameController {

    @Resource
    private NameService nameService;

    @RequestMapping(value = "/names", method = RequestMethod.POST)
    public ResponseEntity<?> addNamesForGame(@PathVariable String gameId, @RequestBody AddNamesForm addNamesForm) {
        nameService.addNamesForGame(gameId, addNamesForm.getPlayerId(), addNamesForm.getNames());
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/teams/{teamId}/names", method = RequestMethod.POST)
    public ResponseEntity<?> removeNamesForGame(@PathVariable String gameId, @PathVariable Long teamId, @RequestBody RemoveNamesForm removeNamesForm) {
        nameService.removeNamesForGame(gameId, teamId, removeNamesForm.getNameIds());
        return ResponseEntity.ok().build();
    }
}
