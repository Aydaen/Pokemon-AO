package it.alten.pokemonao.configuration;

import it.alten.pokemonao.exception.InvalidImageFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class PokeApiCaller{
    private static final String PLACEHOLDER_ICON_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/0.png";

    public String getTypeSpriteByName(String typeName){
        String url = "https://99tvpecyz4.execute-api.eu-west-3.amazonaws.com/v1/pokemon-type-images/" + typeName + "Type.png";
        RestTemplate restTemplate = new RestTemplate();
        try{
            ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);
            HttpHeaders headers = response.getHeaders();
            String contentType = headers.getFirst(HttpHeaders.CONTENT_TYPE);

            if(contentType != null && contentType.equals("image/png")){
                return url;
            } else{
                throw new InvalidImageFormatException(HttpStatus.BAD_REQUEST);
            }
        } catch (HttpClientErrorException.NotFound | InvalidImageFormatException e){
            return PLACEHOLDER_ICON_URL;
        }
    }
}