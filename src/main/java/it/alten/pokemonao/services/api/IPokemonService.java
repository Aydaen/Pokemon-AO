package it.alten.pokemonao.services.api;



import it.alten.pokemonao.models.PokemonModel;

import java.util.List;

public interface IPokemonService {
    PokemonModel getById(Integer id);
    List<PokemonModel> getAll();
}
