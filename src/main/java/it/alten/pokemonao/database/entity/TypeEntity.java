package it.alten.pokemonao.database.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "type", schema = "pokemon_schema")
@Data
public class TypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "name", length = Integer.MAX_VALUE, nullable = false, updatable = false)
    private String name;

    @Column(name = "icon_url", length = Integer.MAX_VALUE)
    private String iconUrl;

    @OneToMany(mappedBy = "typeEntity")
    private Set<MoveEntity> moveEntities = new HashSet<>();

    @OneToMany(mappedBy = "typeEntity")
    private Set<PokemonEntity> pokemonEntities = new HashSet<>();

}