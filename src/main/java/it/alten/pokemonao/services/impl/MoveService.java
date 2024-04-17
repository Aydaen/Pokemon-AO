package it.alten.pokemonao.services.impl;

import it.alten.pokemonao.database.entity.MoveEntity;
import it.alten.pokemonao.database.repository.MoveRepository;
import it.alten.pokemonao.dtos.MoveDTO;
import it.alten.pokemonao.services.api.IMoveService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MoveService implements IMoveService {

    private final MoveRepository moveRepository;
    private final ModelMapper modelMapper;

    @Override
    public MoveDTO getById(Integer id) {
        MoveEntity moveEntity = moveRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(moveEntity, MoveDTO.class);
    }
}
