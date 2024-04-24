package it.alten.pokemonao.controllers;

import it.alten.pokemonao.database.entity.PokemonEntity;
import it.alten.pokemonao.database.repository.PokemonRepository;
import it.alten.pokemonao.dtos.PokemonDTO;
import it.alten.pokemonao.mapper.PokemonMapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/trade")
public class TradeController {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private ModelMapper mapper;


    private final RestTemplate restTemplate = new RestTemplate();

    private static final Logger logger = LoggerFactory.getLogger(TradeController.class);


    @PostMapping
    public void launchTrade(@RequestBody PokemonDTO pokemonDTO) {
        logger.info("PokemonDTO before mapping: {}", pokemonDTO);


        if (pokemonDTO == null) {
            throw new IllegalArgumentException("PokemonDTO cannot be null");
        }


        PokemonEntity pokemonEntityTraded = PokemonMapper.mapToEntity(pokemonDTO);



        if (pokemonEntityTraded == null) {
            throw new IllegalStateException("Failed to map PokemonDTO to PokemonEntity");
        }


        ResponseEntity<PokemonDTO> response = restTemplate.postForEntity(
                "http://pokemondaje.com/api/trade",
                pokemonDTO,
                PokemonDTO.class
        );


        PokemonDTO receivedPokemon = response.getBody();
        pokemonRepository.delete(pokemonEntityTraded);

        if (receivedPokemon != null) {
            PokemonEntity pokemonEntityReceived = PokemonMapper.mapToEntity(receivedPokemon);
            pokemonRepository.save(pokemonEntityReceived);
        } else if (receivedPokemon == null) {
            throw new IllegalStateException("response body is null");
        }

    }

}