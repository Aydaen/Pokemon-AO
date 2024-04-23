package it.alten.pokemonao.services.impl;

import it.alten.pokemonao.database.entity.MoveEntity;
import it.alten.pokemonao.database.repository.MoveRepository;
import it.alten.pokemonao.dtos.MoveDTO;
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
        MoveEntity moveEntity = modelMapper.map(moveDTO, MoveEntity.class);
        moveRepository.save(moveEntity);
    }
}
