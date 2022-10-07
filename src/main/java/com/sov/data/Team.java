package com.sov.data;

import com.sov.model.TeamModel;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class Team {
    private String name;

    private Integer score;

    private List<Player> players;

    public static Team of(TeamModel teamModel) {
        Team team = new Team();
        team.name = teamModel.getName();
        team.score = teamModel.getScore();
        team.players = teamModel.getPlayers().stream().map(Player::of).collect(Collectors.toList());
        return team;
    }
}
