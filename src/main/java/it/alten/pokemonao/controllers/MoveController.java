package it.alten.pokemonao.controllers;

import it.alten.pokemonao.dtos.MoveDTO;
import it.alten.pokemonao.services.api.IMoveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/move")
public class MoveController {
    private final IMoveService moveService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody MoveDTO request) {
        moveService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
