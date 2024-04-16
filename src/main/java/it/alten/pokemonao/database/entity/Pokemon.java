package it.alten.pokemonao.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "pokemon", schema = "pokemon_schema")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    private Integer id;

    @Column(name = "name", length = Integer.MAX_VALUE, nullable = false, updatable = false)
    @Getter
    private String name;

    @Column(name = "image_url", length = Integer.MAX_VALUE)
    @Getter
    private String imageUrl;

    @Column(name = "current_hp", nullable = false)
    @Getter
    @Setter
    private Integer currentHp;

    @Column(name = "max_hp", nullable = false)
    @Getter
    @Setter
    private Integer maxHp;

    @Column(name = "trainer_name", length = Integer.MAX_VALUE, nullable = false, updatable = false)
    @Getter
    private String trainerName;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToMany
    @JoinTable(
            name = "pokemon_move",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "move_id"))
    private Set<Move> moves = new HashSet<>();

}