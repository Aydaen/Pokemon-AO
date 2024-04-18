package it.alten.pokemonao.services.api;



import it.alten.pokemonao.dtos.PokemonDTO;

import java.util.List;

public interface IPokemonService {
    PokemonDTO getById(Integer id);
    List<PokemonDTO> getAll();
    boolean deleteById(Integer id);
    void post(PokemonDTO pokemonDTO);
}
