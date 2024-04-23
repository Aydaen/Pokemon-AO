package it.alten.pokemonao.dtos;

import lombok.Data;

import java.util.List;

@Data
public class PokemonToTradeDTO {
    private Integer pokemonPokeApiId;
    private String nickname;
    private int currentHp;
    private String originalTrainerName;
    private List<Integer> movesPokeApiId;
    private Integer typePokeApiId;
}
