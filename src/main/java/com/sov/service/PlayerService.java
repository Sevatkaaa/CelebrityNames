package com.sov.service;

import com.sov.data.Player;
import com.sov.model.GameModel;
import com.sov.model.PlayerModel;
import com.sov.model.TeamModel;
import com.sov.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PlayerService {

    @Resource
    private GameService gameService;

    @Resource
    private PlayerRepository playerRepository;

    public void addPlayerToTeam(String gameId, Long teamId, Player player) {
        GameModel game = gameService.getGame(gameId);
        TeamModel team = game.getTeams()
                .stream()
                .filter(t -> t.getId().equals(teamId))
                .findFirst()
                .orElseThrow(RuntimeException::new);
        if (team.getPlayers().stream().anyMatch(p -> p.getName().equals(player.getName()))) {
            throw new RuntimeException("Player is already in this team");
        }
        if (team.getPlayers().size() == 2) {
            throw new RuntimeException("Each team can only have 2 players");
        }
        PlayerModel playerModel = new PlayerModel();
        playerModel.setName(player.getName());
        playerModel.setTeam(team);
        playerRepository.save(playerModel);
    }

}
