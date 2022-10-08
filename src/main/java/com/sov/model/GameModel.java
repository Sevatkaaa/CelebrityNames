package com.sov.model;

import com.sov.data.GameStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "game")
@Data
@NoArgsConstructor
public class GameModel {

    @Id
    @Column(length = 10)
    private String id;

    private Integer timeInterval;

    private Integer nameAmount;

    private GameStatus status;

    private Long currentPlayerId;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TeamModel> teams = new ArrayList<>();

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<NameModel> names = new ArrayList<>();
}
