package com.sov.service;

import com.sov.data.Game;
import com.sov.data.GameStatus;
import com.sov.model.GameModel;
import com.sov.model.TeamModel;
import com.sov.repository.GameRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Resource
    private GameRepository gameRepository;

    public GameModel getGame(String id) {
        return gameRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public GameModel createGame(Game game) {
        String id = getId();
        GameModel gameModel = new GameModel();
        gameModel.setId(id);
        gameModel.setStatus(GameStatus.CREATED);
        gameModel.setTimeInterval(game.getTimeInterval());
        gameModel.setNameAmount(game.getNameAmount());
        gameModel.setTeams(game.getTeams()
                .stream()
                .map(team -> new TeamModel(gameModel, team.getName()))
                .collect(Collectors.toList()));
        return gameRepository.save(gameModel);
    }

    private String getId() {
        String id = generateId();
        while (gameRepository.findById(id).isPresent()) {
            id = generateId();
        }
        return id;
    }

    private String generateId() {
        return UUID.randomUUID().toString().substring(0, 6);
    }
}
