package it.alten.pokemonao.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "type", schema = "pokemon_schema")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    @Getter
    private Integer id;

    @Column(name = "name", length = Integer.MAX_VALUE, nullable = false, updatable = false)
    @Getter
    private String name;

    @Column(name = "icon_url", length = Integer.MAX_VALUE)
    @Getter
    @Setter
    private String iconUrl;

    @OneToMany(mappedBy = "type")
    private Set<Move> moves = new HashSet<>();

    @OneToMany(mappedBy = "type")
    private Set<Pokemon> pokemons = new HashSet<>();

}