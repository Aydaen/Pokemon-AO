package it.alten.pokemonao.database.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "type", schema = "pokemon_schema")
@Data
public class TypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "name", length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "icon_url", length = Integer.MAX_VALUE)
    private String iconUrl;

    @OneToMany(mappedBy = "type")
    private List<MoveEntity> moves = new ArrayList<>();

    @OneToMany(mappedBy = "type")
    private List<PokemonEntity> pokemon = new ArrayList<>();

}