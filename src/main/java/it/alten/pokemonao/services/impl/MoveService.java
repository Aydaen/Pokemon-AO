package it.alten.pokemonao.services.impl;

import it.alten.pokemonao.database.entity.MoveEntity;
import it.alten.pokemonao.database.entity.TypeEntity;
import it.alten.pokemonao.database.repository.MoveRepository;
import it.alten.pokemonao.dtos.MoveDTO;
import it.alten.pokemonao.dtos.TypeDTO;
import it.alten.pokemonao.exceptions.PokemonAOException;
import it.alten.pokemonao.services.api.IMoveService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MoveService implements IMoveService {

    private final MoveRepository moveRepository;
    private final ModelMapper modelMapper;

    @Override
    public void create(MoveDTO moveDTO) {
//        boolean isMoveAlreadyPresent = moveRepository.findAll().stream()
//                .map(MoveEntity::getName)
//                .anyMatch(repoMoveName -> repoMoveName.equalsIgnoreCase(moveDTO.getName()));

//        if (isMoveAlreadyPresent) {
//            return;
//        }
//
//        typeService.create(moveDTO.getType());
        MoveEntity moveEntity = modelMapper.map(moveDTO, MoveEntity.class);
        moveRepository.save(moveEntity);
    }

    @Override
    public MoveDTO getByName(String name) {
        if (moveRepository.findByName(name).isEmpty()) {
            throw PokemonAOException.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("No move found with name " + name)
                    .build();
        }
        MoveEntity moveEntity = moveRepository.findByName(name).get();
        return modelMapper.map(moveEntity, MoveDTO.class);
    }
}
