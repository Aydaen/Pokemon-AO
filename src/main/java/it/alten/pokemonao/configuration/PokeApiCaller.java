package it.alten.pokemonao.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PokeApiCaller implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println(getSpriteByName("pikachu"));
    }

    public String getSpriteByName(String pokemonName) throws JsonProcessingException {
        String url = "https://pokeapi.co/api/v2/pokemon/" + pokemonName;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String data = response.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(data);

        return root.at("/sprites/front_default").asText();
    }

}