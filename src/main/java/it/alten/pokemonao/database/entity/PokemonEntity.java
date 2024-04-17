package it.alten.pokemonao.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pokemon", schema = "pokemon_schema")
@Data
public class PokemonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "name", length = Integer.MAX_VALUE, nullable = false, updatable = false)
    private String name;

    @Column(name = "image_url", length = Integer.MAX_VALUE)
    private String imageUrl;

    @Column(name = "current_hp", nullable = false)
    private Integer currentHp;

    @Column(name = "max_hp", nullable = false)
    private Integer maxHp;

    @Column(name = "trainer_name", length = Integer.MAX_VALUE, nullable = false, updatable = false)
    private String trainerName;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "type_id")
    private TypeEntity typeEntity;

    @ManyToMany
    @JoinTable(
            name = "pokemon_move",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "move_id"))
    private List<MoveEntity> moves = new ArrayList<>();

}