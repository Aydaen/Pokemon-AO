package it.alten.pokemonao.services.impl;

import it.alten.pokemonao.configuration.PokeApiCaller;
import it.alten.pokemonao.database.entity.PokemonEntity;
import it.alten.pokemonao.database.repository.PokemonRepository;
import it.alten.pokemonao.dtos.PokemonDTO;
import it.alten.pokemonao.exceptions.PokemonAOException;
import it.alten.pokemonao.services.api.IPokemonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PokemonService implements IPokemonService {
    private final PokemonRepository pokemonRepository;
    private final ModelMapper modelMapper;
    private final PokeApiCaller pokeApiCaller;

    @Override
    public List<PokemonDTO> getAll() {
        return pokemonRepository.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, PokemonDTO.class))
                .toList();
    }

    @Override
    public void deleteById(Integer id) {
        if (pokemonRepository.findById(id).isEmpty()) {
            throw PokemonAOException.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("ERROR: No Pokémon found with id " + id)
                    .build();
        }
        pokemonRepository.deleteById(id);
    }

    @Override
    public void create(PokemonDTO pokemonDTO) {
        if (pokemonDTO.getCurrentHp() > pokemonDTO.getMaxHp()) {
            throw PokemonAOException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("ERROR: currentHp value cannot be higher than maxHp value")
                    .build();
        }

        pokemonDTO.setSprite(pokeApiCaller.getSpriteByName(pokemonDTO.getName().toLowerCase()));
        PokemonEntity pokemonEntity = modelMapper.map(pokemonDTO, PokemonEntity.class);
        pokemonRepository.save(pokemonEntity);
    }

    @Override
    public PokemonDTO getByName(String name) {
        if (pokemonRepository.findByName(name).isEmpty()) {
            throw PokemonAOException.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("No type found with name " + name)
                    .build();
        }
        PokemonEntity pokemonEntity = pokemonRepository.findByName(name).get();
        return modelMapper.map(pokemonEntity, PokemonDTO.class);
    }

    @Override
    public PokemonDTO getRandomPokemon() {
        List<PokemonEntity> pokemonEntityList = pokemonRepository.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(5);
        PokemonEntity randomPokemon = pokemonEntityList.get(randomIndex);
        return modelMapper.map(randomPokemon, PokemonDTO.class);
    }
}
