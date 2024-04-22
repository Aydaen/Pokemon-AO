package it.alten.pokemonao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.net.http.HttpHeaders;

public class InvalidImageFormatException extends HttpClientErrorException {
    public InvalidImageFormatException(HttpStatus statusCode) {
        super(statusCode);
    }
}
