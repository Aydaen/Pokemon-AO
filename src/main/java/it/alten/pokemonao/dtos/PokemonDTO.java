package it.alten.pokemonao.dtos;

import lombok.Data;

@Data
public class PokemonDTO {
    private Integer id;
    private String name;
    private String sprite;
    private int currentHp;
    private int maxHp;
    private TypeDTO type;
    private MoveDTO[] moves;
    private String trainerName;
}
