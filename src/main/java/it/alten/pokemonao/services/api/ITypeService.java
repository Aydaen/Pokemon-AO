package it.alten.pokemonao.services.api;

import it.alten.pokemonao.dtos.TypeDTO;

public interface ITypeService {
    TypeDTO getById(Integer id);

    void save(TypeDTO typeDTO);
}
