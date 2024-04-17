package it.alten.pokemonao.models;

import lombok.*;

@Data
public class MoveModel {
    private Integer id;
    private String name;
    private TypeModel type;
    private int power;
}
