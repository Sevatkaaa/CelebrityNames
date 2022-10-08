package com.sov.data;

import com.sov.model.GameModel;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Game {
    private String id;

    private Integer timeInterval;

    private Integer nameAmount;

    private GameStatus status;

    private List<Team> teams;

    private List<Name> names;

    private Long currentPlayerId;

    public static Game of(GameModel gameModel) {
        Game game = new Game();
        game.id = gameModel.getId();
        game.timeInterval = gameModel.getTimeInterval();
        game.nameAmount = gameModel.getNameAmount();
        game.status = gameModel.getStatus();
        game.teams = gameModel.getTeams().stream().map(Team::of).collect(Collectors.toList());
        game.names = gameModel.getNames().stream().map(Name::of).collect(Collectors.toList());
        game.currentPlayerId = gameModel.getCurrentPlayerId();
        Collections.shuffle(game.names);
        return game;
    }
}
