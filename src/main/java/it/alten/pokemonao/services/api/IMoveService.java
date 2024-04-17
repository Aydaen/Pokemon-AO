package it.alten.pokemonao.services.api;

import it.alten.pokemonao.dtos.MoveDTO;

public interface IMoveService {
    MoveDTO getById(Integer id);

    void save(MoveDTO moveDTO);
}
