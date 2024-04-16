package it.alten.PokemonAO.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "pokemon_move", schema = "pokemon_schema")
public class PokemonMove {
    @EmbeddedId
    private PokemonMoveId id;

    @MapsId("pokemonId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "pokemon_id", nullable = false)
    private Pokemon pokemon;

    @MapsId("moveId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "move_id", nullable = false)
    private Move move;

}