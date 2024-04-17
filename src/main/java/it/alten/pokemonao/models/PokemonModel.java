package it.alten.pokemonao.models;

import lombok.Data;

@Data
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
