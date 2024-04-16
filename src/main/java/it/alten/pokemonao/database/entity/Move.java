package it.alten.pokemonao.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "move", schema = "pokemon_schema")
public class Move {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    @Getter
    private Integer id;

    @Column(name = "name", length = Integer.MAX_VALUE, nullable = false, updatable = false)
    @Getter
    private String name;

    @Column(name = "power", nullable = false)
    @Getter
    @Setter
    private Integer power;

    //TODO: Capire come creare i setter per Type e Set<PokemonMove>
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToMany(mappedBy = "moves")
    private Set<Pokemon> pokemon = new HashSet<>();

}