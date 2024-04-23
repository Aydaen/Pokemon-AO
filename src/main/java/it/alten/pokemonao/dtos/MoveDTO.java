package it.alten.pokemonao.dtos;

import lombok.Data;

@Data
public class MoveDTO {
    private Integer id;
    private Integer movePokeApiId;
    private String name;
    private TypeDTO type;
    private int power;
}
