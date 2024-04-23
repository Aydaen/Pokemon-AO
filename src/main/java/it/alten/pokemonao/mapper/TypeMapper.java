package it.alten.pokemonao.mapper;

import it.alten.pokemonao.database.entity.TypeEntity;
import it.alten.pokemonao.dtos.TypeDTO;

public class TypeMapper {
    public static TypeEntity mapToEntity(TypeDTO typeDTO) {
        if (typeDTO == null) {
            return null;
        }

        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setId(typeDTO.getId());
        typeEntity.setName(typeDTO.getName());
        typeEntity.setIcon(typeDTO.getIcon());


        return typeEntity;
    }
}
