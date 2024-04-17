package it.alten.pokemonao.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PokemonModel {
    private Integer id;
    private String name;
    private String sprite;
    private int currentHp;
    private int maxHp;
    private TypeModel type;
    private MoveModel[] moves;
    private String trainerName;
}
