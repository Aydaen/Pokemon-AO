package it.alten.pokemonao.handlers;

import it.alten.pokemonao.exceptions.PokemonAOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(PokemonAOException.class)
    public ResponseEntity<Object> handlePokemonAOException(PokemonAOException ex, WebRequest request) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(ex.getMessage());
    }
}
