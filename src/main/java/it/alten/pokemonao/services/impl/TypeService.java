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
//        boolean isTypeAlreadyPresent = typeRepository.findAll().stream()
//                .map(TypeEntity::getName)
//                .anyMatch(repoTypeName -> repoTypeName.equalsIgnoreCase(typeDTO.getName()));

//        if (isTypeAlreadyPresent) {
//            return;
//        }

        typeRepository.save(modelMapper.map(typeDTO, TypeEntity.class));
    }

    @Override
    public TypeDTO getByName(String name) {
        if (typeRepository.findByName(name).isEmpty()) {
            throw PokemonAOException.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("No type found with name " + name)
                    .build();
        }
        TypeEntity typeEntity = typeRepository.findByName(name).get();
        return modelMapper.map(typeEntity, TypeDTO.class);
    }
}
