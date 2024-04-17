package it.alten.pokemonao.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "move", schema = "pokemon_schema")
@Data
public class MoveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "name", length = Integer.MAX_VALUE, nullable = false, updatable = false)
    private String name;

    @Column(name = "power", nullable = false)
    private Integer power;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "type_id")
    private TypeEntity typeEntity;

    @ManyToMany(mappedBy = "moveEntities")
    private Set<PokemonEntity> pokemonEntity = new HashSet<>();


}