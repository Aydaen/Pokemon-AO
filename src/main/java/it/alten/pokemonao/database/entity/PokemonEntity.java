package it.alten.pokemonao.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pokemon")
@Data
public class PokemonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "nickname", length = Integer.MAX_VALUE)
    private String nickname;

    @Column(name = "sprite", length = Integer.MAX_VALUE)
    private String sprite;

    @Column(name = "current_hp")
    private Integer currentHp;

    @Column(name = "max_hp")
    private Integer maxHp;

    @Column(name = "trainer_name", length = Integer.MAX_VALUE)
    private String trainerName;

    @Column(name = "pokemon_api_id")
    private Integer pokemonApiId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "type_id")
    private TypeEntity type;

    @ManyToMany
    @JoinTable(
            name = "pokemon_move",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "move_id"))
    private List<MoveEntity> moves = new ArrayList<>();

}