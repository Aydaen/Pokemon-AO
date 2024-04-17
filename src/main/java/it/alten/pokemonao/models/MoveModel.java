package it.alten.pokemonao.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class MoveModel {
    private long id;
    private String name;
    private TypeModel type;
    private int power;
}
