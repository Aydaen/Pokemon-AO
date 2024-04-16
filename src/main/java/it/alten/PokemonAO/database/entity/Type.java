package it.alten.PokemonAO.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "type", schema = "pokemon_schema")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "icon_url", length = Integer.MAX_VALUE)
    private String iconUrl;

    @OneToMany(mappedBy = "type")
    private Set<Move> moves = new HashSet<>();

    @OneToMany(mappedBy = "type")
    private Set<Pokemon> pokemons = new HashSet<>();

}