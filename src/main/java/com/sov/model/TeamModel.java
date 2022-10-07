package com.sov.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team")
@Data
@NoArgsConstructor
public class TeamModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 20)
    private String name;

    private Integer score;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private GameModel game;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PlayerModel> players = new ArrayList<>();

    public TeamModel(GameModel gameModel, String name) {
        this.name = name;
        score = 0;
        game = gameModel;
        players = new ArrayList<>();
    }
}
