package it.alten.pokemonao.controllers;

import it.alten.pokemonao.dtos.MoveDTO;
import it.alten.pokemonao.services.api.IMoveService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/move")
public class MoveController {
    private final IMoveService moveService;

    @GetMapping("/{id}")
    public ResponseEntity<MoveDTO> getById(@PathVariable(name = "id") Integer id) {
        try {
            MoveDTO response = moveService.getById(id);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
