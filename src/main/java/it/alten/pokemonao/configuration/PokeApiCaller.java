package it.alten.pokemonao.configuration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class PokeApiCaller {

    private static final String PLACEHOLDER_ICON_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/0.png";

    public String getSpriteByName(String pokemonName) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + pokemonName;
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String data = response.getBody();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(data);

            return root.at("/sprites/front_default").asText();
        } catch (HttpClientErrorException.NotFound ex) {
            return PLACEHOLDER_ICON_URL;
        } catch (Exception e) {
            e.printStackTrace();
            return PLACEHOLDER_ICON_URL;
        }
    }

}