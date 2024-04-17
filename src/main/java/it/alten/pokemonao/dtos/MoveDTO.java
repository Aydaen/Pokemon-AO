package it.alten.pokemonao.dtos;

import lombok.Data;

@Data
public class MoveDTO {
    private Integer id;
    private String name;
    private TypeDTO type;
    private int power;
}
