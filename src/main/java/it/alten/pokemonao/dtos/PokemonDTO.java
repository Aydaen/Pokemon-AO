package it.alten.pokemonao.dtos;

import lombok.Data;

import java.util.List;

@Data
public class PokemonDTO {
    private Integer pokemonPokeApiId;
    private String nickname;
    private String sprite;
    private int currentHp;
    private int maxHp;
    private TypeDTO type;
    private List<MoveDTO> moves;
    private String trainerName;
}
