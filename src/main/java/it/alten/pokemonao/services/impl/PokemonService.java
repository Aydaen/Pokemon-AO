package it.alten.pokemonao.services.impl;

import it.alten.pokemonao.database.entity.PokemonEntity;
import it.alten.pokemonao.database.repository.PokemonRepository;
import it.alten.pokemonao.models.PokemonModel;
import it.alten.pokemonao.services.api.IPokemonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonService implements IPokemonService {
    private final PokemonRepository pokemonRepository;
    private ModelMapper modelMapper;


    @Override
    public PokemonModel getById(Integer id) {
        PokemonEntity pokemonEntity = pokemonRepository.findById(id).orElse(null);
        return modelMapper.map(pokemonEntity, PokemonModel.class);
    }

    @Override
    public List<PokemonModel> getAll() {
        List<PokemonEntity> pokemonEntityList = pokemonRepository.findAll();
        return  pokemonEntityList
                .stream()
                .map(entity -> modelMapper.map(entity, PokemonModel.class))
                .toList();
    }
}
