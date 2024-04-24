package it.alten.pokemonao.mapper;

import it.alten.pokemonao.database.entity.PokemonEntity;
import it.alten.pokemonao.dtos.MoveDTO;
import it.alten.pokemonao.dtos.PokemonDTO;

public class PokemonMapper {

    public static PokemonEntity mapToEntity(PokemonDTO pokemonDTO) {
        if (pokemonDTO == null) {
            return null;
        }

        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setId(pokemonDTO.getId());
        pokemonEntity.setName(pokemonDTO.getName());
        pokemonEntity.setSprite(pokemonDTO.getSprite());
        pokemonEntity.setCurrentHp(pokemonDTO.getCurrentHp());
        pokemonEntity.setMaxHp(pokemonDTO.getMaxHp());
        pokemonEntity.setTrainerName(pokemonDTO.getTrainerName());

        if (pokemonDTO.getType() != null) {
            pokemonEntity.setType(TypeMapper.mapToEntity(pokemonDTO.getType()));
        }

        if (pokemonDTO.getMoves() != null) {
            for (MoveDTO moveDTO : pokemonDTO.getMoves()) {
                pokemonEntity.getMoves().add(MoveMapper.mapToEntity(moveDTO));
            }
        }

        return pokemonEntity;
    }
}
