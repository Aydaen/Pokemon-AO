package it.alten.pokemonao.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "type")
@Getter
@Setter
public class TypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "name", length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "icon", length = Integer.MAX_VALUE)
    private String icon;

    @Column(name = "type_api_id")
    private Integer typePokeApiId;

    @OneToMany(mappedBy = "type")
    private List<MoveEntity> moves = new ArrayList<>();

    @OneToMany(mappedBy = "type")
    private List<PokemonEntity> pokemon = new ArrayList<>();

}