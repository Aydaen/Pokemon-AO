package it.alten.pokemonao.controllers;

import it.alten.pokemonao.dtos.PokemonDTO;
import it.alten.pokemonao.services.impl.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pokemon")
public class PokemonController {
    private final PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<List<PokemonDTO>> getAll() {
        List<PokemonDTO> pokemonDTOList = pokemonService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(pokemonDTOList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Integer id) {
        pokemonService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PokemonDTO pokemonDTO) {
        pokemonService.create(pokemonDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/random")
    public ResponseEntity<PokemonDTO> getRandom() {
        return ResponseEntity.status(HttpStatus.OK).body(pokemonService.getRandomPokemon());
    }
}
