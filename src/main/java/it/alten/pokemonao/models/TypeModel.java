package it.alten.pokemonao.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TypeModel {
    private long id;
    private String name;
    private String icon;
}
