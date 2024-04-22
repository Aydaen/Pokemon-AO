package it.alten.pokemonao.services.api;

import it.alten.pokemonao.dtos.MoveDTO;

public interface IMoveService {
    void create(MoveDTO moveDTO);
    MoveDTO getByName(String name);
}
