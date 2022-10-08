package com.sov.service;

import com.sov.model.GameModel;
import com.sov.model.NameModel;
import com.sov.model.PlayerModel;
import com.sov.model.TeamModel;
import com.sov.repository.NameRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class NameService {

    @Resource
    private GameService gameService;

    @Resource
    private NameRepository nameRepository;

    public void addNamesForGame(String gameId, Long playerId, List<String> names) {
        GameModel game = gameService.getGame(gameId);
        if (names.size() != game.getNameAmount()) {
            throw new RuntimeException("Not all names are provided");
        }
        List<PlayerModel> players = new ArrayList<>();
        game.getTeams().stream().map(TeamModel::getPlayers).forEach(players::addAll);
        if (!players.stream().map(PlayerModel::getId).collect(Collectors.toList()).contains(playerId)) {
            throw new RuntimeException("Player is not in this game");
        }
        if (game.getNames().stream().anyMatch(name -> name.getPlayer().getId().equals(playerId))) {
            throw new RuntimeException("Player has already added names");
        }
        PlayerModel player = players.stream().filter(p -> p.getId().equals(playerId)).findFirst().orElse(null);
        List<NameModel> nameModels = names.stream()
                .map(n -> new NameModel(n, game, player))
                .collect(Collectors.toList());
        nameRepository.saveAll(nameModels);
    }

    public void removeNamesForGame(String gameId, Long teamId, List<Long> nameIds) {
        GameModel game = gameService.getGame(gameId);
        // TODO: add check for names or remove nulls
        List<NameModel> names = nameIds.stream().map(n -> nameRepository.findById(n).orElse(null)).collect(Collectors.toList());
        TeamModel team = game.getTeams().stream().filter(t -> Objects.equals(t.getId(), teamId)).findFirst().orElseThrow(RuntimeException::new);
        team.setScore(team.getScore() + names.size());
        List<Long> players = gameService.getPlayers(game);
        int playerIndex = players.indexOf(game.getCurrentPlayerId());
        int nextPlayerIndex = (playerIndex + 1) % players.size();
        game.setCurrentPlayerId(players.get(nextPlayerIndex));
        nameRepository.deleteAll(names);
        gameService.save(game);
    }
}
