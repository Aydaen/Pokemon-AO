package it.alten.pokemonao.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class PokemonAOException extends RuntimeException {
    private HttpStatus status;
    private String message;
}
