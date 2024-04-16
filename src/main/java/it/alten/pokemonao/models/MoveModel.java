package it.alten.pokemonao.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MoveModel {
    private long id;
    private String name;
    private TypeModel type;
    private int power;
}
