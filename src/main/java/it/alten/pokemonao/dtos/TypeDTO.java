package it.alten.pokemonao.dtos;

import lombok.Data;

@Data
public class TypeDTO {
    private Integer id;
    private Integer typePokeApiId;
    private String name;
    private String icon;
}
