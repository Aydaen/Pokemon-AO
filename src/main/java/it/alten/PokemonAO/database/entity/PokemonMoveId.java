package it.alten.PokemonAO.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class PokemonMoveId implements Serializable {
    @Column(name = "pokemon_id", nullable = false)
    private Integer pokemonId;

    @Column(name = "move_id", nullable = false)
    private Integer moveId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PokemonMoveId entity = (PokemonMoveId) o;
        return Objects.equals(this.pokemonId, entity.pokemonId) &&
                Objects.equals(this.moveId, entity.moveId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pokemonId, moveId);
    }

}