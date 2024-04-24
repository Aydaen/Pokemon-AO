package it.alten.pokemonao.services.impl;

import it.alten.pokemonao.configuration.PokeApiCaller;
import it.alten.pokemonao.database.entity.PokemonEntity;
import it.alten.pokemonao.database.repository.PokemonRepository;
import it.alten.pokemonao.database.repository.TypeRepository;
import it.alten.pokemonao.dtos.MoveDTO;
import it.alten.pokemonao.dtos.PokemonDTO;
import it.alten.pokemonao.dtos.TypeDTO;
import it.alten.pokemonao.exceptions.PokemonAOException;
import it.alten.pokemonao.services.api.IPokemonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PokemonService implements IPokemonService, CommandLineRunner {
    private final PokemonRepository pokemonRepository;
    private final ModelMapper modelMapper;
    private final PokeApiCaller pokeApiCaller;

    @Override
    public void run(String... args) throws Exception {
        createFallbackPokemon();
    }

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
        pokemonDTO.setSprite(pokeApiCaller.getSpriteByApiId(pokemonDTO.getPokemonPokeApiId()));
        pokemonDTO.setMaxHp(pokeApiCaller.getMaxHpPokeApiId(pokemonDTO.getPokemonPokeApiId()));
        if (pokemonDTO.getCurrentHp() > pokemonDTO.getMaxHp()) {
            throw PokemonAOException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("ERROR: currentHp value cannot be higher than maxHp value")
                    .build();
        }
        PokemonEntity pokemonEntity = modelMapper.map(pokemonDTO, PokemonEntity.class);
        pokemonRepository.save(pokemonEntity);
    }

    @Override
    public PokemonDTO getRandomPokemon() {
        List<PokemonEntity> pokemonEntityList = pokemonRepository.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(5);
        PokemonEntity randomPokemon = pokemonEntityList.get(randomIndex);
        return modelMapper.map(randomPokemon, PokemonDTO.class);
    }

    @Value("${pokemon-fallback.nickname}")
    private String fallbackNickname;

    @Value("${pokemon-fallback.sprite}")
    private String fallbackSprite;

    @Value("${pokemon-fallback.currentHp}")
    private int fallbackCurrentHp;

    @Value("${pokemon-fallback.maxHp}")
    private int fallbackMaxHp;

    @Value("${pokemon-fallback.type.typePokeApiId}")
    private Integer fallbackTypePokeApiId;

    @Value("${pokemon-fallback.type.name}")
    private String fallbackTypeName;

    @Value("${pokemon-fallback.type.icon}")
    private String fallbackTypeIcon;

    @Value("${pokemon-fallback.moves[0].movePokeApiId}")
    private Integer fallbackMovePokeApiId;

    @Value("${pokemon-fallback.moves[0].name}")
    private String fallbackMoveName;

    @Value("${pokemon-fallback.moves[0].power}")
    private int fallbackMovePower;

    @Value("${pokemon-fallback.moves[0].type.typePokeApiId}")
    private Integer fallbackMoveTypePokeApiId;

    @Value("${pokemon-fallback.moves[0].type.name}")
    private String fallbackMoveTypeName;

    @Value("${pokemon-fallback.moves[0].type.icon}")
    private String fallbackMoveTypeIcon;

    @Value("${pokemon-fallback.trainerName}")
    private String fallbackTrainerName;
    private final TypeRepository typeRepository;

    private void createFallbackPokemon() {
        if (pokemonRepository.count() == 0) {
            PokemonDTO fallbackPokemon = new PokemonDTO();
            fallbackPokemon.setPokemonPokeApiId(0);
            fallbackPokemon.setNickname(fallbackNickname);
            fallbackPokemon.setSprite(fallbackSprite);
            fallbackPokemon.setCurrentHp(fallbackCurrentHp);
            fallbackPokemon.setMaxHp(fallbackMaxHp);

            TypeDTO fallbackType = new TypeDTO();
            fallbackType.setTypePokeApiId(fallbackTypePokeApiId);
            fallbackType.setName(fallbackTypeName);
            fallbackType.setIcon(fallbackTypeIcon);
            fallbackPokemon.setType(fallbackType);

            MoveDTO fallbackMove = new MoveDTO();
            fallbackMove.setMovePokeApiId(fallbackMovePokeApiId);
            fallbackMove.setName(fallbackMoveName);
            fallbackMove.setPower(fallbackMovePower);

            TypeDTO fallbackMoveType = new TypeDTO();
            fallbackMoveType.setTypePokeApiId(fallbackMoveTypePokeApiId);
            fallbackMoveType.setName(fallbackMoveTypeName);
            fallbackMoveType.setIcon(fallbackMoveTypeIcon);
            fallbackMove.setType(fallbackMoveType);

            fallbackPokemon.setMoves(List.of(fallbackMove));
            fallbackPokemon.setTrainerName(fallbackTrainerName);

            pokemonRepository.save(modelMapper.map(fallbackPokemon, PokemonEntity.class));
        }
    }
}

