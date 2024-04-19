package it.alten.pokemonao.services.impl;

import it.alten.pokemonao.database.entity.PokemonEntity;
import it.alten.pokemonao.database.repository.PokemonRepository;
import it.alten.pokemonao.dtos.PokemonDTO;
import it.alten.pokemonao.services.api.IPokemonService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonService implements IPokemonService {
    private final PokemonRepository pokemonRepository;
    private final ModelMapper modelMapper;

    @Override
    public PokemonDTO getById(Integer id) {
        PokemonEntity pokemonEntity = pokemonRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(pokemonEntity, PokemonDTO.class);
    }

    @Override
    public List<PokemonDTO> getAll() {
        return  pokemonRepository.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, PokemonDTO.class))
                .toList();
    }

    @Override
    public void deleteById(Integer id) {
        if(!pokemonRepository.existsById(id)){
            throw new EntityNotFoundException();
        }
        pokemonRepository.deleteById(id);
    }

    @Override
    public void create(PokemonDTO pokemonDTO) {
        if(pokemonDTO.getCurrentHp() > pokemonDTO.getMaxHp()){
            //TODO: create custom exception
        }else{
            PokemonEntity pokemonEntity = modelMapper.map(pokemonDTO,PokemonEntity.class);
            pokemonRepository.save(pokemonEntity);
        }
    }
}
