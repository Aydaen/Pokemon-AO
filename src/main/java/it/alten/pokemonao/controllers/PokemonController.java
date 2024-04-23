package it.alten.pokemonao.controllers;

import it.alten.pokemonao.dtos.PokemonDTO;
import it.alten.pokemonao.services.impl.PokemonService;
import jakarta.persistence.EntityNotFoundException;
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
        List<PokemonDTO> pokemonList = pokemonService.getAll();
        return ResponseEntity.ok(pokemonList);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<PokemonDTO> getById(@PathVariable(name = "id") Integer id) {
        try {
            PokemonDTO response = pokemonService.getById(id);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Integer id) {
        try {
            pokemonService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PokemonDTO pokemonDTO) {
        if (pokemonDTO.getCurrentHp() > pokemonDTO.getMaxHp()) {
            return ResponseEntity.badRequest().build();
        } else {
            pokemonService.create(pokemonDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

}
