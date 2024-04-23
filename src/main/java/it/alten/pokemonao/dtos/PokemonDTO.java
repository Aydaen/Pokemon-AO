package it.alten.pokemonao.dtos;

import lombok.Data;

@Data
public class PokemonDTO {
    private Integer pokemonPokeApiId;
    private String nickname;
    private String sprite;
    private int currentHp;
    private int maxHp;
    private TypeDTO type;
    private MoveDTO[] moves;
    private String trainerName;
}
