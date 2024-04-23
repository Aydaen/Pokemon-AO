package it.alten.pokemonao.services.impl;

import it.alten.pokemonao.database.entity.TypeEntity;
import it.alten.pokemonao.database.repository.TypeRepository;
import it.alten.pokemonao.dtos.TypeDTO;
import it.alten.pokemonao.exceptions.PokemonAOException;
import it.alten.pokemonao.services.api.ITypeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TypeService implements ITypeService {
    private final TypeRepository typeRepository;
    private final ModelMapper modelMapper;

    @Override
    public void create(TypeDTO typeDTO) {
        typeRepository.save(modelMapper.map(typeDTO, TypeEntity.class));
    }
}
