package com.sov.data;

import com.sov.model.PlayerModel;
import lombok.Data;

@Data
public class Player {
    private String name;

    public static Player of(PlayerModel playerModel) {
        Player player = new Player();
        player.name = playerModel.getName();
        return player;
    }
}
