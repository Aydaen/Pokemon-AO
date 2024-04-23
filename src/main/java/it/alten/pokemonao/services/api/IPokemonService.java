package it.alten.pokemonao.services.api;

import it.alten.pokemonao.dtos.PokemonDTO;

import java.util.List;

public interface IPokemonService {
    List<PokemonDTO> getAll();
    void deleteById(Integer id);
    void create(PokemonDTO pokemonDTO);
    PokemonDTO getRandomPokemon();
}
