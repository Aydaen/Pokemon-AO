package it.alten.pokemonao.mapper;

import it.alten.pokemonao.database.entity.MoveEntity;
import it.alten.pokemonao.dtos.MoveDTO;

public class MoveMapper {

    public static MoveEntity mapToEntity(MoveDTO moveDTO) {
        if (moveDTO == null) {
            return null;
        }

        MoveEntity moveEntity = new MoveEntity();
        moveEntity.setId(moveDTO.getId());
        moveEntity.setName(moveDTO.getName());
        moveEntity.setPower(moveDTO.getPower());


        if (moveDTO.getType() != null) {
            moveEntity.setType(TypeMapper.mapToEntity(moveDTO.getType()));
        }


        return moveEntity;
    }
}
