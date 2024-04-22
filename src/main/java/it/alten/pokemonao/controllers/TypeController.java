package it.alten.pokemonao.controllers;

import it.alten.pokemonao.dtos.TypeDTO;
import it.alten.pokemonao.services.api.ITypeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/type")
public class TypeController {
    private final ITypeService typeService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TypeDTO typeDTO) {
        typeService.create(typeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{name}")
    public ResponseEntity<TypeDTO> getByName(@PathVariable(name = "name") String name) {
        TypeDTO typeDTO = typeService.getByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(typeDTO);
    }
}
