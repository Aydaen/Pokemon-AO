package it.alten.pokemonao.controllers;

import it.alten.pokemonao.models.TypeModel;
import it.alten.pokemonao.services.api.ITypeService;
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
@RequestMapping("/type")
public class TypeController {

    private final ITypeService typeService;

    @GetMapping("/{id}")
    public ResponseEntity<TypeModel> getById(@PathVariable(name = "id") Integer id) {
        try {
           TypeModel response = typeService.getById(id);
           return ResponseEntity.ok(response);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
