package com.sov.data;

import com.sov.model.GameModel;
import com.sov.model.NameModel;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class Game {
    private String id;

    private Integer timeInterval;

    private Integer nameAmount;

    private GameStatus status;

    private List<Team> teams;

    private List<String> names;

    public static Game of(GameModel gameModel) {
        Game game = new Game();
        game.id = gameModel.getId();
        game.timeInterval = gameModel.getTimeInterval();
        game.nameAmount = gameModel.getNameAmount();
        game.status = gameModel.getStatus();
        game.teams = gameModel.getTeams().stream().map(Team::of).collect(Collectors.toList());
        game.names = gameModel.getNames().stream().map(NameModel::getValue).collect(Collectors.toList());
        return game;
    }
}
