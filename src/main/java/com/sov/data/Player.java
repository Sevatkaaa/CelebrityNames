package com.sov.data;

import com.sov.model.PlayerModel;
import lombok.Data;

@Data
public class Player {
    private Long id;
    private String name;

    public static Player of(PlayerModel playerModel) {
        Player player = new Player();
        player.id = playerModel.getId();
        player.name = playerModel.getName();
        return player;
    }
}
