package com.sov.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "celebrity_name")
@Data
@NoArgsConstructor
public class NameModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private GameModel game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private PlayerModel player;

    public NameModel(String value, GameModel game, PlayerModel player) {
        this.value = value;
        this.game = game;
        this.player = player;
    }
}
