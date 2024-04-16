package it.alten.pokemonao.database.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "pokemon", schema = "pokemon_schema")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "type_id")
    private TypeEntity typeEntity;

    @ManyToMany
    @JoinTable(
            name = "pokemon_move",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "move_id"))
    private Set<MoveEntity> moveEntities = new HashSet<>();

}