package it.alten.pokemonao.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "move")
@Getter
@Setter
public class MoveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "name", length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "power")
    private Integer power;

    @Column(name = "move_api_id")
    private Integer movePokeApiId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "type_id")
    private TypeEntity type;

    @ManyToMany(mappedBy = "moves")
    private List<PokemonEntity> pokemon = new ArrayList<>();


}