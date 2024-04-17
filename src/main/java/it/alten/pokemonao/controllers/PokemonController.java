package it.alten.pokemonao.controllers;

import it.alten.pokemonao.models.PokemonModel;
import it.alten.pokemonao.services.impl.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pokemon")
public class PokemonController {
    private final PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<List<PokemonModel>> getAll(){
        List<PokemonModel> pokemonList = pokemonService.getAll();
        return ResponseEntity.ok(pokemonList);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<PokemonModel> getById(@PathVariable(name = "id") Integer id) {
        PokemonModel pokemon = pokemonService.getById(id);
        return ResponseEntity.ok(pokemon);
    }

}
