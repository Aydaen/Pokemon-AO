package it.alten.pokemonao.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PokemonModel {
    private long id;
    private String name;
    private String sprite;
    private int currentHp;
    private int maxHp;
    private TypeModel type;
    private MoveModel[] moves;
    private String trainerName;
}
