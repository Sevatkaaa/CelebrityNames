package com.sov.service;

import com.sov.data.Game;
import com.sov.data.GameStatus;
import com.sov.model.GameModel;
import com.sov.model.PlayerModel;
import com.sov.model.TeamModel;
import com.sov.repository.GameRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GameService {

    private static final int PLAYER_AMOUNT = 2;

    @Resource
    private GameRepository gameRepository;

    public GameModel getGame(String id) {
        return gameRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void save(GameModel game) {
        gameRepository.save(game);
    }

    public void refreshGameStatus(String id) {
        GameModel game = getGame(id);
        if (game.getStatus() == GameStatus.CREATED && game.getNames().size() == game.getNameAmount() * game.getTeams().size() * PLAYER_AMOUNT) {
            game.setStatus(GameStatus.PLAYING);
            List<Long> playerIds = getPlayers(game);
            game.setCurrentPlayerId(playerIds.get(0));
            gameRepository.save(game);
        }
        if (game.getStatus() == GameStatus.PLAYING && game.getNames().isEmpty()) {
            game.setStatus(GameStatus.FINISHED);
            gameRepository.save(game);
        }
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

    public List<Long> getPlayers(GameModel game) {
        List<Long> playerIds = new ArrayList<>();
        game.getTeams().stream().map(TeamModel::getPlayers).forEach(p -> playerIds.addAll(p.stream().map(PlayerModel::getId).collect(Collectors.toList())));
        Collections.sort(playerIds);
        return playerIds;
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
